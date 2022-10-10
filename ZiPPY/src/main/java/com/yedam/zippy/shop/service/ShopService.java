package com.yedam.zippy.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ShopService {
	//상품 CRUD
	//전체조회
	public List<ProductVO> getProductList();
	//전체조회(카테고리)
	public List<ProductVO> getCategoryList(String category);
	//전체조회(키워드)
	public List<ProductVO> getKeywordList(String keyword);
	//단건조회(디테일)
	public ProductVO getProduct(int shopProductNo);
}
