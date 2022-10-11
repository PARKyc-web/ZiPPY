package com.yedam.zippy.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.shop.mapper.ShopMapper;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

  @Autowired
  ShopMapper mapper;

  @Override
  public List<ProductVO> getProductList() {
    return mapper.getProductList();
  }
  @Override
  public List<ProductVO> getCategoryList(String category) {
    return mapper.getCategoryList(category);
  }
  @Override
  public List<ProductVO> getKeywordList(String keyword) {
    return mapper.getKeywordList(keyword);
  }
  @Override
  public ProductVO getProduct(int shopProductNo) {
    return mapper.getProduct(shopProductNo);
  }
  @Override
  public String[] getDetailImg(int shopProductNo) {
    return mapper.getDetailImg(shopProductNo);
  }
  @Override
  public List<ProductOptionVO> getDetailOpt(int shopProductNo) {
    return mapper.getDetailOpt(shopProductNo);
  }

}
