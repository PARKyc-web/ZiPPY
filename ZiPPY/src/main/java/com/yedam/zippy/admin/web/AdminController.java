package com.yedam.zippy.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yedam.zippy.admin.service.AdminService;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
  
  @Autowired
  AdminService service;
  
  @GetMapping("/general")
  public PageInfo<GeneralUserVO> getAllGeneralUser(@RequestParam int pageNum){    
    String order ="";
    PageHelper.startPage(pageNum, 10, order);
    return PageInfo.of(service.getAllGeneral());
  }
  
  @GetMapping("/business")
  public PageInfo<BusinessVO> getAllBusinessUser(@RequestParam int pageNum){    
    String order ="";
    PageHelper.startPage(pageNum, 10, order);
    return PageInfo.of(service.getAllBusiness());
  }  
 
  
}
