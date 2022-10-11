package com.yedam.zippy.used.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.used.service.UsedProductVO;

public interface UsedMapper {
	// 전체조회
	public List<UsedProductVO> usedList(@Param("location")String location, @Param("keyword")String keyword, @Param("category")String category, @Param("checked")String checked, @Param("dropbox")String dropbox);
	
	// 검색조회
	public List<UsedProductVO> usedSearchList(@Param("word")String word);

	// 단건조회
	public UsedProductVO usedOne(@Param("pNo")int pNo);

	// 등록
	public void insertUsed(Map<String, String> insertProduct);

	// 수정
	public void updateUsed(String value);

	// 삭제
	public void deleteUsed(String key);
	
	// 조회수 증가
	public void viewCnt(@Param("pNo") int pNo);
	
	// 찜 추가
	public String addWish(@Param("id") String id);
}
