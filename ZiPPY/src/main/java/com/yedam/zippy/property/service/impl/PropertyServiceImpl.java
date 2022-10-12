package com.yedam.zippy.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.property.mapper.PropertyMapper;
import com.yedam.zippy.property.service.PropertyService;
import com.yedam.zippy.property.service.agentVO;
import com.yedam.zippy.property.service.propertyVO;

@Service
public class PropertyServiceImpl implements PropertyService {

  @Autowired
  PropertyMapper mapper;

  @Override
  public List<propertyVO> getAllPropertyList() {
    return mapper.getAllPropertyList();
  }

  @Override
  public String getAgentEmail(int productId) {
    return mapper.getAgentEmail(productId);
  }

  @Override
  public List<propertyVO> gethouseDetail(int productId) {
    return mapper.gethouseDetail(productId);
  }

  @Override
  public List<propertyVO> getAgentProperties(String email) {
    return mapper.getAgentProperties(email);
  }

  @Override
  public List<agentVO> getAgentProfile(String email) {
    return mapper.getAgentProfile(email);
  }

  @Override
  public String getCompName(String email) {
    return mapper.getCompName(email);
  }
  
  @Override
  public int insertHouseProduct(propertyVO vo) {
    return mapper.insertHouseProduct(vo);
  }

  @Override
  public void updateHouseProduct(propertyVO vo) {
    mapper.updateHouseProduct(vo);
  }

  @Override
  public void updateHouseDetail(propertyVO vo) {
    mapper.updateHouseDetail(vo);
  }
  

}
