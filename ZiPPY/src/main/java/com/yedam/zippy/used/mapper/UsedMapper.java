package com.yedam.zippy.used.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedKeywordVO;
import com.yedam.zippy.used.service.UsedProductVO;

public interface UsedMapper {
	// 전체조회
	public List<UsedProductVO> usedList(@Param("location")String location, @Param("keyword")String keyword, @Param("category")String category, @Param("checked")String checked, @Param("dropbox")String dropbox);
	
	// 검색조회
	public List<UsedProductVO> usedSearchList(@Param("word")String word);

	// 단건조회
	public UsedProductVO usedOne(@Param("pNo")int pNo);

	// 등록
	public void insertUsed(UsedProductVO vo);

	// 수정
	public int updateUsed(UsedProductVO vo);

	// 삭제
	public int deleteUsed(@Param("pNo") int pNo);
	
	// 조회수 증가
	public void viewCnt(@Param("pNo") int pNo);

	// 이미지 등록
	public int insertImg(UsedImagesVO images);
	
	// 이미지 수정
	public int updateImg(UsedImagesVO images);
	
	// 키워드, 키워드 지역 입력
	public int addKeyword(UsedKeywordVO keyword);
	
	// 키워드, 키워드 지역 삭제
	public int delKeyword(@Param("kNo")int kNo);
	
	// 키워드 출력
	public List<UsedKeywordVO> showKeyword(@Param("email") String email);
	  
}
