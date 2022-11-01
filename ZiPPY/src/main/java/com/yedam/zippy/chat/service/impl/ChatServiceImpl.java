package com.yedam.zippy.chat.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.chat.mapper.ChatMapper;
import com.yedam.zippy.chat.service.ChatMessage;
import com.yedam.zippy.chat.service.ChatRoomName;
import com.yedam.zippy.chat.service.ChatRoomVO;
import com.yedam.zippy.chat.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

  private final File folder = new File("/home/chat");

  @Autowired
  private ChatMapper mapper;

  @Override
  public List<ChatRoomName> findAllRoom(String email) {

    List<ChatRoomName> result = new ArrayList<ChatRoomName>();
    List<ChatRoomVO> roomList = mapper.findAllRoom(email);

    for (ChatRoomVO vo : roomList) {
      List<ChatRoomName> inner = mapper.findAllRoomName(vo, email);
      for (ChatRoomName room : inner) {
        result.add(room);
      }
    }

    return result;
  }
  
  @Override
  public ChatRoomName findOneRoom(int roomId) {
    ChatRoomVO vo = mapper.getRoomInfo(roomId);            
    return mapper.findOneRoomName(vo);
  }

  @Override
  public void saveChatContent(ChatMessage cm) {
    String chatFile = folder.toString() + File.separator + "roomNum" + cm.getRoomId() + ".txt";
    File file = new File(chatFile);

    String content = cm.getSender() + "-" + cm.getMessage() + "-" + cm.getTime();

    try {
      FileWriter fw = new FileWriter(file, true);
      BufferedWriter writer = new BufferedWriter(fw);
      writer.write(content + "\n");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<ChatMessage> loadChatContent(String roomId) {
    String chatFile = folder.toString() + File.separator + "roomNum" + roomId + ".txt";

    List<ChatMessage> list = new ArrayList<>();
    try {
      File file = new File(chatFile);
      FileReader fd = new FileReader(file);
      BufferedReader reader = new BufferedReader(fd);

      String str = "";
      while ((str = reader.readLine()) != null) {
        ChatMessage vo = parsingContent(str);
        vo.setRoomId(roomId);
        list.add(vo);
      }
    } catch (IOException e) {
      e.getStackTrace();
    }
    return list;
  }

  @Override
  public ChatMessage parsingContent(String str) {
    ChatMessage vo = new ChatMessage();

    str = str.trim();
    String[] data = str.split("-");

    vo.setSender(data[0]);
    vo.setMessage(data[1]);
    vo.setTime(data[2] + "(" + data[3] + ")");

    return vo;
  }

  @Override
  public ChatRoomVO createChatRoom(ChatRoomVO vo) {
    
    ChatRoomVO isRedundancy = mapper.roomRedundancy(vo);  
    if(isRedundancy != null) {    
      return isRedundancy;      
    }
    
    mapper.createChatRoom(vo);
    String txt = folder + File.separator + "roomNum" + vo.getChatRoomNo() + ".txt";
    
    File file = new File(txt);
    try {
      if (file.createNewFile()) {
        System.out.println("Chat-ROOM-CREATED");
        
      } else {
        System.out.println("Chat-ROOM-alreay-exist");        
        FileWriter fw = new FileWriter(file);       
        
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("");
        writer.close();        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return vo;
  }

  @Override
  public ChatRoomVO getRoomInfo(int roomId) {
    return mapper.getRoomInfo(roomId);
  }
  
  @Override
  public int soldout(ChatRoomVO vo) {
    return mapper.soldout(vo);
  }
  
  @Override
  public void saveLastChatLog(ChatMessage chatMessage) {
    mapper.saveLastChatLog(chatMessage);
  }
  
  @Override
  public int sellProduct(ChatRoomVO vo) {     
    return mapper.sellProduct(vo);
  }
  
}
