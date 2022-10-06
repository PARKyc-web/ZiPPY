package com.yedam.zippy.used.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@CrossOrigin
@RestController
@RequestMapping("/used")
public class UsedContoller {
	
	@Autowired
	UsedService service;
	
	// 전체조회
	@GetMapping("/main")
	public List<UsedProductVO> search(@RequestParam String location, @RequestParam String keyword) {
		
		System.out.println(location +" , "+keyword);		
		
		return service.usedList(location,keyword);
	}
	
	// 단건조회
	@GetMapping("/getOne")
	public String getOne() {
		return "";
	}
	
	@PostMapping("/register")
	public String insert(@RequestBody Map<String, String> insertProduct) {
		return "";
	}
}
