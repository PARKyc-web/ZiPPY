package com.yedam.zippy.shop.web;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.shop.service.BasketVO;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.ShopService;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

  @Autowired
  ShopService service;

  // 전체조회
  @RequestMapping("/main")
  public List<ProductVO> productList() {
    return service.getProductList();
  }

  // 전체조회(카테고리)
  @RequestMapping("/category")
  public List<ProductVO> categoryList(@RequestParam("cate") String shopCategory) {
    return service.getCategoryList(shopCategory);
  }

  // 전체조회(키워드)
  @RequestMapping("/keyword")
  public List<ProductVO> keywordList(@RequestParam("keyw") String keyword) {
    return service.getKeywordList(keyword);
  }

  // 단건조회(디테일)
  @RequestMapping("/detail")
  public ProductVO productDetail(@RequestParam("no") int shopProductNo) {
    return service.getProduct(shopProductNo);
  }

  //조회(이미지)
  @RequestMapping("/img")
  public String[] imgDetail(@RequestParam("no") int shopProductNo) {
    return service.getDetailImg(shopProductNo);
  }
  
  //조회(옵션)
  @RequestMapping("/opt")
  public List<ProductOptionVO> optDetail(@RequestParam("no") int shopProductNo) {
    return service.getDetailOpt(shopProductNo);
  }
  
  //등록
  @PostMapping("/insertBasket")
  @ResponseBody
  public void insertBasket(BasketVO basketVO) {
    service.insertBasket(basketVO);
  }
  //전체조회
  @RequestMapping("/basket")
  public List<ProductVO> basketList(String email) {
    return service.getBasketList(email);
  }
}
