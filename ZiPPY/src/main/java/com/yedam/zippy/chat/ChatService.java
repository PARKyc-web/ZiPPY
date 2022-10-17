package com.yedam.zippy.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;
  
    @PostConstruct
    private void init() {
      chatRooms = new LinkedHashMap<>();
    }
    
    public List<ChatRoom> findAllRoom(){
      List<ChatRoom> result = new ArrayList<>(chatRooms.values());      
      Collections.reverse(result);
      
      return result;
    }
    
    public ChatRoom findById(String roomId) {
      return chatRooms.get(roomId);
    }
    
    public ChatRoom createRoom(String name) {
       ChatRoom chatRoom = ChatRoom.create(name);
       chatRooms.put(chatRoom.getRoomId(), chatRoom);
       return chatRoom;
    }
    
}
