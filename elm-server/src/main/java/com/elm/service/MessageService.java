package com.elm.service;

import com.elm.dto.GetMessageDTO;
import com.elm.entity.Message;

import java.util.Map;

public interface MessageService {
    Map<String,Object> getMessages(GetMessageDTO getMessageDTO);
    Map<String,Object> createMessage(Message message);
    Map<String,Object> updateMessage(Message message);
    Map<String,Object> getUserById(Integer id);
    Map<String,Object> Addmsg(Integer num);
}