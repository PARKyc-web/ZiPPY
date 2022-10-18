package com.yedam.zippy.chat.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage { 
  
  public enum MessageType {
    ENTER, TALK
  }
  
  private MessageType type;
  private String roomId;
  private String sender;
  private String message;  
  private String time;
}
