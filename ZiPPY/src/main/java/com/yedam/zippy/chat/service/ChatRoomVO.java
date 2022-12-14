package com.yedam.zippy.chat.service;

import lombok.Data;

@Data
public class ChatRoomVO {     
    private int chatRoomNo;
    private String user1;
    private String user2;
    private int chatType;
    private String lastChat;
    private String lastTime;
    private int productNo;
    private int isSell;
}
