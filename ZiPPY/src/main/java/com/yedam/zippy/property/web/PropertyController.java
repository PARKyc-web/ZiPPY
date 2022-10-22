package com.yedam.zippy.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yedam.zippy.property.service.PropertyService;
import com.yedam.zippy.property.service.WishVO;
import com.yedam.zippy.property.service.agentVO;
import com.yedam.zippy.property.service.propertyVO;

@CrossOrigin
@RestController
@RequestMapping(value = "/property", method = {RequestMethod.GET, RequestMethod.POST})
public class PropertyController {

  @Autowired
  PropertyService service;

  @GetMapping("/main")
  public List<propertyVO> propertyMain() {
    return service.getAllPropertyList();
  }
  
  @GetMapping("/getPropertyList")
  public List<propertyVO> getPropertyList(@RequestParam("sigungu")String sigungu) {
    return service.getPropertyList(sigungu);
  }
  
  @GetMapping("/agentDetail")
public String agentDetail(@RequestParam int productId) {
  return service.getAgentEmail(productId);
}
  
  @GetMapping("/houseDetail")
  public List<propertyVO> houseDetail(@RequestParam int productId) {
    return service.gethouseDetail(productId);
  }
  
  @GetMapping("/getAgentProperties")
  public PageInfo<propertyVO> getAgentProperties(@RequestParam("email")String email, @RequestParam("pageNum") int pageNum) {
    String orderBy = "product_id";
    // pageNum: 현재 페이지, pageSize: 4(페이지당 글 수), orderBy: 정렬 조건 
    PageHelper.startPage(pageNum, 4, orderBy);
    return PageInfo.of(service.getAgentProperties(email));
  }
  
  @GetMapping("/getAgentProfile")
  public List<agentVO> getAgentProfile(@RequestParam("email")String email) {
    return service.getAgentProfile(email);
  }
  
  @GetMapping("/getCompName")
  public String getCompName(@RequestParam("email")String email) {
    return service.getCompName(email);
  }
  
  @PostMapping("/insertHouseProduct")
  public int insertHouseProduct(propertyVO vo) {
    return service.insertHouseProduct(vo);
  }
  
  @PostMapping("/insertHouseDetail")
  public int insertHouseDetail(propertyVO vo) {
    return service.insertHouseDetail(vo);
  }
  
  @PutMapping("/updateHouseProduct")
  public int updateHouseProduct(propertyVO vo) {
    return service.updateHouseProduct(vo);
  }

  @PutMapping("/updateHouseDetail")
  public int updateHouseDetail(propertyVO vo) {
    return service.updateHouseDetail(vo);
  }
  
  @PutMapping("/updateAgentProfile")
  public int updateAgentProfile(agentVO vo) {
    return service.updateAgentProfile(vo);
  }
  
  // 해당 매물의 관심 매물 여부를 반환
  @GetMapping("/wishState")
  public WishVO wishState(WishVO vo) {
    return service.wishState(vo);
  }
  
  // 사용자가 현재 위치한 지역(시군구)의 모든 아파트 매물을 select 함.
  @GetMapping("/currentPositionAptList")
  public List<propertyVO> currentPositionAptList(@RequestParam("sigungu")String sigungu) {
    return service.currentPositionAptList(sigungu);
  }
  
  @GetMapping("/searchPropertyList")
  public List<propertyVO> searchPropertyList(propertyVO vo) {
    return service.searchPropertyList(vo);
  }

//  @GetMapping("/houseDetail")
//  public String houseDetail() {
//    return "property/houseDetail";
//  }
//
//  @GetMapping("/agentDetail")
//  public String agentDetail() {
//    return "property/agentDetail";
//  }
}
