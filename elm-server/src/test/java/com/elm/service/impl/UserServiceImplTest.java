package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.constant.StatusConstant;
import com.elm.context.BaseContext;
import com.elm.dto.AccountPageQueryDTO;
import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.PermissionLevel;
import com.elm.entity.User;
import com.elm.exception.AccountNotFoundException;
import com.elm.exception.BaseException;
import com.elm.exception.PasswordErrorException;
import com.elm.mapper.UserMapper;
import com.elm.result.PageResult;
import com.elm.result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser_AccountExist() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUsername("testUser");
        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setUsername("testUser");
        when(userMapper.getUserByAccount("testUser")).thenReturn(existingUser);

        Result result = userService.addUser(updateUserDTO);

        assertEquals(0, result.getCode());
        assertEquals(MessageConstant.ACCOUNT_EXIST, result.getMsg());
    }

    @Test
    void testAddUser_EmailRepeat() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUsername("testUser");
        updateUserDTO.setEmail("test@example.com");
        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setEmail("test@example.com");
        when(userMapper.getUserByAccount("testUser")).thenReturn(null);
        when(userMapper.getUserByEmail("test@example.com")).thenReturn(existingUser);

        Result result = userService.addUser(updateUserDTO);

        assertEquals(0, result.getCode());
        assertEquals(MessageConstant.EMAIL_REPEAT, result.getMsg());
    }

    @Test
    void testAddUser_Success() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUsername("testUser");
        when(userMapper.getUserByAccount("testUser")).thenReturn(null);
        when(userMapper.getUserByEmail(any())).thenReturn(null);
        when(userMapper.getUserByQqAccount(any())).thenReturn(null);
        when(userMapper.getUserByWechatAccount(any())).thenReturn(null);
        when(userMapper.createUser(any(User.class))).thenReturn(1);

        Result result = userService.addUser(updateUserDTO);

        assertEquals(1, result.getCode());
    }

    @Test
    void testLogin_AccountNotFound() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("testUser");
        when(userMapper.getUserByAccount("testUser")).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> userService.login(userLoginDTO));
    }

    @Test
    void testLogin_PasswordError() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("testUser");
        userLoginDTO.setPassword("wrongPassword");
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("correctPassword");
        when(userMapper.getUserByAccount("testUser")).thenReturn(user);

        assertThrows(PasswordErrorException.class, () -> userService.login(userLoginDTO));
    }

    @Test
    void testLogin_AccountLocked() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("1");
        userLoginDTO.setPassword("1");
        User user = new User();
        user.setUsername("1");
        user.setPassword("1");
        user.setIsLoggedOut(StatusConstant.ENABLE);
        when(userMapper.getUserByAccount("testUser")).thenReturn(user);

        assertThrows(AccountNotFoundException.class, () -> userService.login(userLoginDTO));
    }

    @Test
    void testLogin_Success() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("testUser");
        userLoginDTO.setPassword("correctPassword");
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("correctPassword");
        user.setIsLoggedOut(StatusConstant.DISABLE);
        when(userMapper.getUserByAccount("testUser")).thenReturn(user);

        User result = userService.login(userLoginDTO);

        assertNotNull(result);
    }

    @Test
    void testUpdateUser_UnknownError() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setUserId(2);
        BaseContext.setCurrentId(1);
        when(userMapper.getUserByAccount(any())).thenReturn(new User());

        assertThrows(BaseException.class, () -> userService.updateUser(updateUserDTO));
    }

    @Test
    void testPageQuery_AccountNotFound() {
        AccountPageQueryDTO accountPageQueryDTO = new AccountPageQueryDTO();
        accountPageQueryDTO.setPage(1);
        accountPageQueryDTO.setPageSize(10);
        BaseContext.setCurrentId(1);
        when(userMapper.getUserById(1)).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> userService.pageQuery(accountPageQueryDTO));
    }

    @Test
    void testPageQuery_Success() {
        AccountPageQueryDTO accountPageQueryDTO = new AccountPageQueryDTO();
        accountPageQueryDTO.setPage(1);
        accountPageQueryDTO.setPageSize(10);
        BaseContext.setCurrentId(1);
        User user = new User();
        user.setPermissionLevel(PermissionLevel.ADMIN);
        when(userMapper.getUserById(1)).thenReturn(user);
        Page<User> page = new Page<>();
        page.add(new User());
        page.setTotal(1);
        when(userMapper.pageQuery(accountPageQueryDTO)).thenReturn(page);

        PageResult result = userService.pageQuery(accountPageQueryDTO);

        assertNotNull(result);
        assertEquals(1, result.getTotal());
    }
}