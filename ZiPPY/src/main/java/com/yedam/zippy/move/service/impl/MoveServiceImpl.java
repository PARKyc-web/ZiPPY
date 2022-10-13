package com.yedam.zippy.move.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.move.mapper.MoveMapper;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveService;

@Service
public class MoveServiceImpl implements MoveService{
  
  @Autowired
  MoveMapper mapper;

  @Override
  public int moveUntactCheck(MoveRequestVO moveRequ) {
   return mapper.moveUntactCheck(moveRequ);
  }
}
