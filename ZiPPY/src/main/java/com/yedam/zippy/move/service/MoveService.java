package com.yedam.zippy.move.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.move.mapper.MoveMapper;

@Service
public interface MoveService {


  public int moveUntactCheck(MoveRequestVO moveRequ);
}
