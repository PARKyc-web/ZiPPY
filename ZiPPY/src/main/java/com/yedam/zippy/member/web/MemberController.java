package com.yedam.zippy.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	
	@GetMapping("bSignUp")
	public String businessLoginForm() {
		
		return "loginOut/businessForm";
	}
	
}
