package com.yedam.zippy.chat;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoom {
  
    private String roomId;
    private String roomName;    
    
    
    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.setRoomId(UUID.randomUUID().toString());
        room.setRoomName(name);
                
        return room;
    }
}
