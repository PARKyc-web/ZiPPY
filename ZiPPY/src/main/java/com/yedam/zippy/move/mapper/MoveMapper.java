package com.yedam.zippy.move.mapper;

import com.yedam.zippy.move.service.MoveRequestVO;

public interface MoveMapper {
  
  //비대면 정보입력
  public int moveUntactCheck(MoveRequestVO moveReq);
  
  //대면 정보입력
  public int moveContactCheck(MoveRequestVO moveReq);
  
  
}
