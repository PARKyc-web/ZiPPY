package com.yedam.zippy.chat.service;

import lombok.Data;

@Data
public class ChatRoomName {
  private int chatRoomNo;
  private String user1;
  private String user2;
  private int chatType;
  private String user1Name;
  private String user2Name;
  private String user1Pic;
  private String user2Pic;
  private String lastChat;
  private String lastTime;
}
