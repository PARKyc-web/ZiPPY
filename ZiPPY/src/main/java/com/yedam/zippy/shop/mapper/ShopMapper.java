package com.yedam.zippy.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.zippy.shop.service.CartVO;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;

public interface ShopMapper {
  // 상품 CRUD
  //전체조회
  public List<ProductVO> getProductList();
  //전체조회(카테고리)
  public List<ProductVO> getCategoryList(String category);
  //전체조회(키워드)
  public List<ProductVO> getKeywordList(String keyword);
  //단건조회(디테일)
  public ProductVO getProduct(int proNo);
  //디테일 이미지 조회
  public String[] getDetailImg(int proNo);
  //디테일 옵션 조회
  public List<ProductOptionVO> getDetailOpt(int proNo);
  
  
  // 장바구니 CRUD
  //등록
  public void insertCart(CartVO CartVo);
  //장바구니 상품개수 조회
  public int getMyCart(String email);
  //내 장바구니 조회
  public List<CartVO> getMyCartList(String email);
  //삭제
  public void deleteCart(@Param("selected") List<CartVO> selected);
  //주문 CRUD
  public void insertPur(@Param("selected")List<CartVO> selected);
}
