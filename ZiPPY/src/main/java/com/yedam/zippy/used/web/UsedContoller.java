package com.yedam.zippy.used.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/used")
public class UsedContoller {

	// 전체조회
	@RequestMapping("/main")
	public String usedList() {
		return "used/home";
	}

	// 단건조회
	@GetMapping("/detail")
	public String getUsedOne() {
		return "/used/detail";
	}
}
