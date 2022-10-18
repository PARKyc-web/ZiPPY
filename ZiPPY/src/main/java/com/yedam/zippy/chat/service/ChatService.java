package com.yedam.zippy.chat.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChatService {

  public List<ChatRoomVO> findAllRoom(String email);
  
  public void saveChatContent(ChatMessage cm);
  
  public List<ChatMessage> loadChatContent(String roomId);
  
  public ChatMessage parsingContent(String str);
}