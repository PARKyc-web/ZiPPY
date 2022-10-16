package com.yedam.zippy.chat.mapper;

import java.util.List;

import com.yedam.zippy.chat.service.ChatVO;

public interface ChatMapper {
  
  public List<ChatVO> findAllRoom(String email);
}
