package com.yedam.zippy.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ShopService {
	//상품 CRUD
	//전체조회
	public List<ProductVO> getProductList();
	//단건조회
	public ProductVO getProduct(ProductVO productVO);
	//등록
	public void insertProduct(ProductVO productVO);
	//수정
	public void updateProduct(ProductVO productVO);
	//삭제
	public void deleteProduct(ProductVO productVO);
}
