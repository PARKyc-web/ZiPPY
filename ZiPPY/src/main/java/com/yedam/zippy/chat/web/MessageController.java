package com.yedam.zippy.chat.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.chat.service.ChatMessage;
import com.yedam.zippy.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MessageController {

  private final SimpMessageSendingOperations sendingOperations;

  @Autowired
  private ChatService chatService;

  @MessageMapping("/chat/message")
  public void enter(ChatMessage message) {    
    System.out.println(message);
        
//  입장이 아닐때 사용되는 것
    if (ChatMessage.MessageType.TALK.equals(message.getType())) {      
      System.out.println(message.getRoomId());
      sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
      chatService.saveChatContent(message);
    }
  }
}
