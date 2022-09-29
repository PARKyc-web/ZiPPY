package com.yedam.zippy.shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService service;
	
	//전체조회
	@RequestMapping("/main")
	public String productList(Model model, ProductVO vo) {
		model.addAttribute("products", service.getProductList());
		//select해서 가져온 값
		return "shop/main";
		//jsp파일 위치
	}
	//단건조회
	@RequestMapping("/shop")
	public String product(ProductVO vo, Model model) {
	model.addAttribute("shop", service.getProduct(vo));
	return "shop";
	}
	//등록
	
	//수정
	
	//삭제
}
