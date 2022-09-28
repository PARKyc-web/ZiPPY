package com.yedam.zippy.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.zippy.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	ShopService service;
	
	//전체조회 페이지
	@GetMapping("/list")
	public String boardList(Model model) {
		model.addAttribute("shopList", service.getProductList());
		return "shop/list";
	}
}
