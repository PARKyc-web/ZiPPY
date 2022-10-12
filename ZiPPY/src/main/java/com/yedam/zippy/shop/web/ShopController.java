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

import com.yedam.zippy.shop.service.CartVO;
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
  public List<ProductVO> categoryList(@RequestParam("cate") String category) {
    return service.getCategoryList(category);
  }

  // 전체조회(키워드)
  @RequestMapping("/keyword")
  public List<ProductVO> keywordList(@RequestParam("keyw") String keyword) {
    return service.getKeywordList(keyword);
  }

  // 단건조회(디테일)
  @RequestMapping("/detail")
  public ProductVO productDetail(@RequestParam("pno") int proNo) {
    return service.getProduct(proNo);
  }

  //조회(이미지)
  @RequestMapping("/img")
  public String[] imgDetail(@RequestParam("pno") int proNo) {
    return service.getDetailImg(proNo);
  }
  
  //조회(옵션)
  @RequestMapping("/opt")
  public List<ProductOptionVO> optDetail(@RequestParam("pno") int proNo) {
    return service.getDetailOpt(proNo);
  }
  
  //장바구니
  //장바구니 등록
  @PostMapping("/insertCart")
  @ResponseBody
  public void insertCart(CartVO cartVO) {
    service.insertCart(cartVO);
  }
  //장바구니 전체조회
  @RequestMapping("/cart")
  public List<ProductVO> cartList(String email) {
    return service.getCartList(email);
  }
  //장바구니 개수 조회
  @RequestMapping("/myCart")
  public int countMyCart(String email) {
    return service.getMyCart(email);
  }
}
