package com.yedam.zippy.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.zippy.shop.service.ProductVO;

public interface ShopMapper {
  // 상품 CRUD
  // 전체조회
  public List<ProductVO> getProductList();
  //전체조회(카테고리)
  public List<ProductVO> getCategoryList(String category);
  //전체조회(키워드)
  public List<ProductVO> getKeywordList(String keyword);
  // 단건조회(디테일)
  public ProductVO getProduct(int shopProductNo);
}
