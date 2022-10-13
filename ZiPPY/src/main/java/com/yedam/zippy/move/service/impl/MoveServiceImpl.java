package com.yedam.zippy.move.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.move.mapper.MoveMapper;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveService;

@Service
public class MoveServiceImpl implements MoveService{
  
  @Autowired
  MoveMapper mapper;

  @Override
  public int moveUntactCheck(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3) {
   
    File file = new File("C:\\Users\\admin\\Desktop\\moveImage\\image111.jpg");
    try {
      images1.get(0).transferTo(file);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return mapper.moveUntactCheck(moveReq);
  }

  @Override
  public int moveContactCheck(MoveRequestVO moveReq) {

    return mapper.moveContactCheck(moveReq);
  }
}
