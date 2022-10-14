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

import com.yedam.zippy.property.service.PropertyService;
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
  public List<propertyVO> getAgentProperties(@RequestParam("email")String email) {
    return service.getAgentProperties(email);
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
