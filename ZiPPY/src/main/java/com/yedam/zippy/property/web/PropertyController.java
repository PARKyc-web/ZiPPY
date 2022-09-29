package com.yedam.zippy.property.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/property")
public class PropertyController {
	
	@GetMapping("/main")
	public String propertyMain() {
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
