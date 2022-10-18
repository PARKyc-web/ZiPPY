package com.yedam.zippy.chat.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.chat.service.ChatMessage;
import com.yedam.zippy.chat.service.ChatRoomVO;
import com.yedam.zippy.chat.service.ChatService;

import lombok.RequiredArgsConstructor;


@CrossOrigin
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
   
    private final ChatService service;
      
    // 모든 채팅방 목록 반환
    @GetMapping("/room")
    public List<ChatRoomVO> getRooms(String email){
      return service.findAllRoom(email);
    }
    
    @GetMapping("/room/load")
    public List<ChatMessage> loadContent(String roomId){
        return service.loadChatContent(roomId);                   
    }
    
    // 채팅방 생성
//    @PostMapping("/room")
 
    
 

}
