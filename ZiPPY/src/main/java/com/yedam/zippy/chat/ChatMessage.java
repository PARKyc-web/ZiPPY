package com.yedam.zippy.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
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
}
