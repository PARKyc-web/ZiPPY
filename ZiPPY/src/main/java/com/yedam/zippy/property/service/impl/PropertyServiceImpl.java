package com.yedam.zippy.property.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.yedam.zippy.property.mapper.PropertyMapper;
import com.yedam.zippy.property.service.PropertyService;
import com.yedam.zippy.property.service.WishVO;
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
  public List<propertyVO> getPropertyList(String sigungu) {
    return mapper.getPropertyList(sigungu);
  }

  @Override
  public String getAgentEmail(int productId) {
    return mapper.getAgentEmail(productId);
  }

  @Override
  public List<propertyVO> gethouseDetail(int productId) {
    return mapper.gethouseDetail(productId);
  }

  public Page<propertyVO> getAgentProperties(String email) {
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
  public int insertHouseDetail(propertyVO vo) {
      return mapper.insertHouseDetail(vo);
  }

  @Override
  @Transactional
  public int updateHouseProduct(propertyVO vo) {
    return mapper.updateHouseProduct(vo);
    //return mapper.updateHouseDetail(vo);
  }

  @Override
  public int updateHouseDetail(propertyVO vo) {
    return mapper.updateHouseDetail(vo);
  }
  
  @Override
  public int updateAgentProfile(agentVO vo) {
    return mapper.updateAgentProfile(vo);
  }
  
  @Override
  public WishVO wishState(WishVO vo) {
    return mapper.wishState(vo);
  }
  
  @Override
  public List<propertyVO> currentPositionAptList(String sigungu) {
    return mapper.currentPositionAptList(sigungu);
  }
  
  @Override
  public List<propertyVO> searchPropertyList(propertyVO vo) {
    return mapper.searchPropertyList(vo);
  }
  
  @Override
  public Page<WishVO> getAllWishProperties(String email) {
    return mapper.getAllWishProperties(email);
  }
}
