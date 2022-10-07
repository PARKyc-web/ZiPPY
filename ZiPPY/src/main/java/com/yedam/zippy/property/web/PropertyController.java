package com.yedam.zippy.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.property.service.PropertyService;
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

//  @GetMapping("/main")
//  public String propertyMain(Model model) {
//    model.addAttribute("propertyList", service.getAllPropertyList());
//    return "property/main";
//  }

  @GetMapping("/houseDetail")
  public String houseDetail() {
    return "property/houseDetail";
  }

  @GetMapping("/agentDetail")
  public String agentDetail() {
    return "property/agentDetail";
  }
}
