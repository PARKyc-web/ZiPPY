package com.yedam.zippy.property.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.zippy.property.service.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController {
	
	@Autowired
	PropertyService service;
	
	@GetMapping("/main")
	public String propertyMain(Model model) {
		model.addAttribute("propertyList", service.getAllPropertyList());
		return "property/main";
	}
	
	@GetMapping("/houseDetail")
	public String houseDetail() {
		return "property/houseDetail";
	}
	
	@GetMapping("/agentDetail")
	public String agentDetail() {
		return "property/agentDetail";
	}
}
