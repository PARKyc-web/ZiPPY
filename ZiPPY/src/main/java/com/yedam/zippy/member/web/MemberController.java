package com.yedam.zippy.member.web;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
	
	
	@GetMapping("gSignUp")
	public String businessLoginForm(@RequestParam Map<String, String> userInfo, Model model) {
		
		for(String x : userInfo.keySet()) {
			System.out.println("Key : " + x + " \\ " + "Value : " + userInfo.get(x));
		}		
		
		System.out.println(model.getAttribute("password"));
		
		return "";		
	}
	
}
