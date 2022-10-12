package com.yedam.zippy.used.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@Service
public class UsedServiceImpl implements UsedService {

  @Autowired
  UsedMapper mapper;

  @Override
  public List<UsedProductVO> usedList(String location, String keyword, String category, String checked,
      String dropbox) {
    return mapper.usedList(location, keyword, category, checked, dropbox);
  }

  @Override
  public List<UsedProductVO> usedSearchList(String word) {
    return mapper.usedSearchList(word);
  }

  @Override
  public UsedProductVO usedOne(int pNo) {
    return mapper.usedOne(pNo);
  }

  @Override
  public void insertUsed(UsedProductVO vo) {
    mapper.insertUsed(vo);
  }

  @Override
  public int updateUsed(UsedProductVO vo) {
    return mapper.updateUsed(vo);
  }

  @Override
  public int deleteUsed(int pNo) {
    return mapper.deleteUsed(pNo);
  }

  @Override
  public void viewCnt(int pNo) {
    mapper.viewCnt(pNo);
  }

  @Override
  public String addWish(String id) {
    return mapper.addWish(id);
  }
  
  @Override
  public int insertImg(UsedImagesVO images) {
    return mapper.insertImg(images);
  }
  
  @Override
  public int updateImg(UsedImagesVO images) {
    return mapper.updateImg(images);
  }

}
