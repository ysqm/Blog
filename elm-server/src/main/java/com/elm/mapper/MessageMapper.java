package com.elm.mapper;

import com.elm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.elm.entity.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    List<Message> getMessagesByTypeAndRange(@Param("type") Integer type, @Param("start") Integer start, @Param("end") Integer end);
    List<Message> getRepliesByMessageIdAndRange(@Param("m_Id") Integer messageId, @Param("start") Integer start, @Param("end") Integer end);
    List<Message> getMessagesByUserIdAndType(@Param("type") Integer type, @Param("userId") Integer userId);
    User getUserById(@Param("userId") Integer userId);
    Integer insertMessage(Message message);
    Integer updateMessage(Message message);
    Message getMessageById(@Param("m_id") Integer messageId);
}
