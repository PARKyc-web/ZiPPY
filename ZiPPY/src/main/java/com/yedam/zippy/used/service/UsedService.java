package com.yedam.zippy.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface UsedService {
  // 전체조회
  public List<UsedProductVO> usedList(String location, String keyword, String category, String checked, String dropbox);

  // 검색조회
  public List<UsedProductVO> usedSearchList(String word);

  // 단건조회
  public UsedProductVO usedOne(int pNo);

  // 등록
  public void insertUsed(Map<String, String> insertProduct);

  // 수정
  public int updateUsed(String img, String name, String price, String category, String content, int pNo);

  // 삭제
  public int deleteUsed(int pNo);

  // 조회수 증가
  public void viewCnt(int pNo);

  // 찜 추가
  public String addWish(String id);
}
