package com.yedam.zippy.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.property.service.PropertyService;
import com.yedam.zippy.property.service.agentVO;
import com.yedam.zippy.property.service.propertyVO;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {

  @Autowired
  PropertyService service;

  @GetMapping("/main")
  public List<propertyVO> propertyMain() {
    return service.getAllPropertyList();
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
  
  @PutMapping("/updateHouseProduct")
  public void updateHouseProduct(@RequestParam propertyVO vo) {
    service.updateHouseProduct(vo);
//    return service.updateHouseProduct(vo);
  }

  @PutMapping("/updateHouseDetail")
  public void updateHouseDetail(@RequestParam propertyVO vo) {
    service.updateHouseDetail(vo);
//    return updateHouseDetail(vo);
  }
  
  @PostMapping("/insertHouseProduct")
  public int insertHouseProduct(propertyVO vo) {
    return service.insertHouseProduct(vo);
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
