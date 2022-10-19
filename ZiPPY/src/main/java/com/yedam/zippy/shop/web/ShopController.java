package com.yedam.zippy.shop.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.shop.service.CartVO;
import com.yedam.zippy.shop.service.OrderVO;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.PurchaseVO;
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

  // 조회(이미지)
  @RequestMapping("/img")
  public String[] imgDetail(@RequestParam("pno") int proNo) {
    return service.getDetailImg(proNo);
  }

  // 조회(옵션)
  @RequestMapping("/opt")
  public List<ProductOptionVO> optDetail(@RequestParam("pno") int proNo) {
    return service.getDetailOpt(proNo);
  }

  // 조회(북마크)
  @RequestMapping("/heart")
  public void getHeart(String email) {
    service.getHeart(email);
  }

  // 장바구니
  // 장바구니 등록
  @PostMapping("/insertCart")
  public void insertCart(CartVO cartVO) {
    service.insertCart(cartVO);
  }

  // 장바구니 개수 조회
  @RequestMapping("/myCart")
  public int countMyCart(String email) {
    return service.getMyCart(email);
  }

  // 내 장바구니 조회
  @PostMapping("/myCartList")
  public List<CartVO> getMyCartList(String email) {
    return service.getMyCartList(email);
  }

  // 삭제
  @PostMapping("/delCart")
  public void deleteCart(@RequestBody List<CartVO> selected) {
    service.deleteCart(selected);
  }

  // 주문
  // 주문할 상품 등록(from 장바구니)
  @PostMapping("/insertPur")
  public void insertPur(@RequestBody List<CartVO> selected, String payCode) {
    service.insertPur(selected, payCode);
  }

  // 주문할 상품 등록(from 디테일)
  @PostMapping("/insertPurOne")
  public void insertPurOne(@RequestBody ProductVO product, String payCode, String email) {
    System.out.println(product.getEmail());
    service.insertPurOne(product, payCode, email);
  }

  // 내 정보 조회
  @PostMapping("/myInfo")
  public GeneralUserVO getMyInfo(String email) {
    return service.getMyInfo(email);
  }

  // 상품정보 조회
  @PostMapping("/myPurPro")
  public List<PurchaseVO> getMyPurList(String payCode) {
    return service.getMyPurList(payCode);
  }

  // 주문등록
  @PostMapping("/insertOrder")
  public void insertOrder(@RequestBody OrderVO orderVO) {
    service.insertOrder(orderVO);
  }

  // 상품등록
  @PostMapping("/insertPro")
  public void insertProduct(ProductVO productVO, String option, MultipartFile image, List<MultipartFile> images) {
    Gson gs = new Gson();
    List<ProductOptionVO> options = gs.fromJson(option, new TypeToken<ArrayList<ProductOptionVO>>() {
    }.getType());
    service.insertProduct(productVO, options, image, images);
  }

  // 상품조회
  @ResponseBody
  @PostMapping("/myProList")
  public List<ProductVO> getMyProList(@RequestBody ProductVO productVO,
      @RequestParam(required = false) String keyword) {
    return service.getMyProList(productVO, keyword);
  }

  // 등록 상품상태 수정
  @PostMapping("/updateStatus")
  public void updateStatus(@RequestBody ProductVO productVO) {
    service.updateStatus(productVO);
  }

  // 상품정보 수정
  @PostMapping("/updatePro")
  public void updateProduct(ProductVO productVO, String option, MultipartFile image, List<MultipartFile> images) {
    Gson gs = new Gson();
    List<ProductOptionVO> options = gs.fromJson(option, new TypeToken<ArrayList<ProductOptionVO>>() {
    }.getType());
    service.updateProduct(productVO, options, image, images);
  }

  // 상품조회
  @ResponseBody
  @PostMapping("/myOrdList")
  public List<OrderVO> getMyOrdList(@RequestBody ProductVO productVO,
      @RequestParam(required = false) String keyword) {
    return service.getMyOrdList(productVO, keyword);
  }
  
  //등록 상품상태 수정
   @PostMapping("/updateOrdStatus")
   public void updateOrdStatus(@RequestBody OrderVO orderVO) {
     service.updateOrdStatus(orderVO);
   }
}