package com.yedam.zippy.used.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UsedService {
	// 전체조회
	public List<UsedProductVO> usedList(String location, String keyword);

	// 단건조회
	public UsedProductVO usedOne(UsedProductVO usedVO);

	// 등록
	public void insertUsed(UsedProductVO usedVO);

	// 수정
	public void updateUsed(UsedProductVO usedVO);

	// 삭제
	public void deleteUsed(UsedProductVO usedVO);
}
