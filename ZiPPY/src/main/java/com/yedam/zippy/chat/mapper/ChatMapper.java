package com.yedam.zippy.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.chat.service.ChatMessage;
import com.yedam.zippy.chat.service.ChatRoomName;
import com.yedam.zippy.chat.service.ChatRoomVO;

public interface ChatMapper {
  
  public List<ChatRoomVO> findAllRoom(String email);  
  
  public List<ChatRoomName> findAllRoomName(@Param("room") ChatRoomVO room, @Param("email") String email);
  
  public ChatRoomName findOneRoomName(@Param("room") ChatRoomVO room);
  
  public void createChatRoom(@Param("vo") ChatRoomVO vo);
  
  public ChatRoomVO getRoomInfo(int roomId);
  
  // 판매여부
  public int soldout(ChatRoomVO vo);
  
  public void saveLastChatLog(ChatMessage chatMessage);
}
