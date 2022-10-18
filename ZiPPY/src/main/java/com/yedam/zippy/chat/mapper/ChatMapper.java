package com.yedam.zippy.chat.mapper;

import java.util.List;

import com.yedam.zippy.chat.service.ChatRoomVO;

public interface ChatMapper {
  
  public List<ChatRoomVO> findAllRoom(String email);
}
