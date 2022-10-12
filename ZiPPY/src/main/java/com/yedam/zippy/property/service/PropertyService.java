package com.yedam.zippy.property.service;

import java.util.List;

public interface PropertyService {
  public List<propertyVO> getAllPropertyList();

  public String getAgentEmail(int productId);

  public List<propertyVO> gethouseDetail(int productId);

  public List<propertyVO> getAgentProperties(String email);

  public List<agentVO> getAgentProfile(String email);

  public String getCompName(String email);
  
  public int insertHouseProduct(propertyVO vo);

  public void updateHouseProduct(propertyVO vo);

  public void updateHouseDetail(propertyVO vo);
}
