package com.yedam.zippy.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.shop.mapper.ShopMapper;
import com.yedam.zippy.shop.service.CartVO;
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
  public ProductVO getProduct(int proNo) {
    return mapper.getProduct(proNo);
  }
  @Override
  public String[] getDetailImg(int proNo) {
    return mapper.getDetailImg(proNo);
  }
  @Override
  public List<ProductOptionVO> getDetailOpt(int proNo) {
    return mapper.getDetailOpt(proNo);
  }
  @Override
  public void insertCart(CartVO cartVO) {
    mapper.insertCart(cartVO);
  }
  @Override
  public int getMyCart(String email) {
    return mapper.getMyCart(email);
  }
  @Override
  public List<CartVO> getMyCartList(String email) {
    List<CartVO> list = mapper.getMyCartList(email);
    for(CartVO x : list) {
      x.setProductVO(mapper.getProduct(x.getCartPno()));
      x.setCartPrice(x.getProductVO().getProPrice()+x.getOptPrice());
    }
    return list;
  }

}
