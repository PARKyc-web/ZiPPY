package com.yedam.zippy.chat.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.yedam.zippy.chat.ChatMessage;
import com.yedam.zippy.chat.service.chatService;

@Service
public class chatServiceImpl implements chatService{
  
  private final File folder = new File("D:/dev/chat");
  
  @Override
  public void saveChatContent(ChatMessage cm) {    
    System.out.println("===============");
    System.out.println("ChatService RUN");
    System.out.println(cm.getRoomId());
    System.out.println(cm.getSender());
    System.out.println(cm.getMessage());
    
    String chatFile = folder.toString()+ File.separator 
                      + "roomNum" + cm.getRoomId() + ".txt";
    File file = new File(chatFile);
    
    String content = cm.getSender() + "-" + cm.getMessage() + "-" + cm.getTime();
    
    try {
      FileWriter fw = new FileWriter(file, true);      
      BufferedWriter writer = new BufferedWriter(fw);      
      writer.write(content+"\n");      
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }

}
