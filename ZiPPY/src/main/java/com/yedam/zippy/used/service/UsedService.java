package com.yedam.zippy.used.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface UsedService {
	// 전체조회
	public List<UsedProductVO> usedList(String location, String keyword);
	
	// 검색조회
	public List<UsedProductVO> usedSearchList(String word);

	// 단건조회
	public UsedProductVO usedOne(int pNo);

	// 등록
	public void insertUsed(Map<String,String> insertProduct);

	// 수정
	public void updateUsed(String value);

	// 삭제
	public void deleteUsed(String key);
}
