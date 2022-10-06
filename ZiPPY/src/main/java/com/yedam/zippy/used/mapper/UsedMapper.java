package com.yedam.zippy.used.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.used.service.UsedProductVO;

public interface UsedMapper {
	// 전체조회
	public List<UsedProductVO> usedList(@Param("location")String location, @Param("keyword")String keyword);

	// 단건조회
	public UsedProductVO usedOne(UsedProductVO usedVO);

	// 등록
	public void insertUsed(UsedProductVO usedVO);

	// 수정
	public void updateUsed(UsedProductVO usedVO);

	// 삭제
	public void deleteUsed(UsedProductVO usedVO);
}
