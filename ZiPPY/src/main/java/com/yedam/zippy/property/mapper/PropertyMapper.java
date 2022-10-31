package com.yedam.zippy.property.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yedam.zippy.property.service.WishVO;
import com.yedam.zippy.property.service.agentVO;
import com.yedam.zippy.property.service.propertyVO;

public interface PropertyMapper {

	public List<propertyVO> getAllPropertyList(@Param("houseType")String houseType);
	
	public List<propertyVO> getPropertyList(@Param("sigungu")String sigungu);
	
	// 매물번호로 공인중개사 email 
	public String getAgentEmail(@Param("productId")int productId);
	
	// product_id를 이용해 해당 매물의 상세페이지 내용을 반환합니다.
	public List<propertyVO> gethouseDetail(@Param("productId")int productId);
	
	// 공인중개사의 email을 이용해 해당 업체가 가진 매물 목록을 나타냅니다.
	public Page<propertyVO> getAgentProperties(@Param("email")String email);
	
	// 공인중개사의 email을 이용해 해당 업체의 정보를 나타냅니다.
	public List<agentVO> getAgentProfile(@Param("email")String email);
	
	
	// 공인중개사의 email을 이용해 해당 업체명을 나타냄
	public String getCompName(@Param("email")String email);
	
	public int insertHouseProduct(propertyVO vo);
	
	public int insertHouseDetail(propertyVO vo);

	public int updateHouseProduct(propertyVO vo);
	
	public int updateHouseDetail(propertyVO vo);
	
	public int updateAgentProfile(agentVO vo);
	
	public WishVO wishState(WishVO vo);
	
	public List<propertyVO> currentPositionAptList(String sigungu);
	
	public List<propertyVO> searchPropertyList(propertyVO vo);
	
	public Page<WishVO> getAllWishProperties(@Param("email")String email);
		
}
