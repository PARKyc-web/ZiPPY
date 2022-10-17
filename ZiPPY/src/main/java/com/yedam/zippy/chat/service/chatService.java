package com.yedam.zippy.chat.service;

import org.springframework.stereotype.Service;

import com.yedam.zippy.chat.ChatMessage;

@Service
public interface chatService {

  public void saveChatContent(ChatMessage cm);
  
}
