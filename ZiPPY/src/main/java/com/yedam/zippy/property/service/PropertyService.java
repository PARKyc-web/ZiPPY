package com.yedam.zippy.property.service;

import java.util.List;

import com.github.pagehelper.Page;

public interface PropertyService {
  public List<propertyVO> getAllPropertyList(String houseType);
  
  public List<propertyVO> getPropertyList(String sigungu);

  public String getAgentEmail(int productId);

  public List<propertyVO> gethouseDetail(int productId);

  public Page<propertyVO> getAgentProperties(String email);
  
  public List<agentVO> getAgentProfile(String email);

  public String getCompName(String email);
  
  public int insertHouseProduct(propertyVO vo);
  
  public int insertHouseDetail(propertyVO vo);

  public int updateHouseProduct(propertyVO vo);

  public int updateHouseDetail(propertyVO vo);
  
  public int updateAgentProfile(agentVO vo);
  
  public WishVO wishState(WishVO vo);
  
  public List<propertyVO> currentPositionAptList(String sigungu);
  
  public List<propertyVO> searchPropertyList(propertyVO vo);
  
  public Page<WishVO> getAllWishProperties(String email);
  
  public int insertHouseImages(PropertyImagesVO vo); 
}
