package com.yedam.zippy.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.chat.service.chatService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MessageController {
    
    private final SimpMessageSendingOperations sendingOperations;
    
    @Autowired
    private chatService chatService;
    
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
//      System.out.println(message);
      System.out.println(message.getSender());
      System.out.println(message.getMessage());
      if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
        message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }      
      sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
      
      if (ChatMessage.MessageType.TALK.equals(message.getType())) {
        chatService.saveChatContent(message);
      }
    }
}
