package com.yedam.zippy.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ShopService {
	//상품 CRUD
	//전체조회
	public List<ShopProductVO> getProductList();
	//단건조회
	public ShopProductVO getProduct();
	//상품등록
	public int insertShopProduct();
	//상품수정
	public int updateShopProduct();
	//상품삭제
	public int deletShopProduct();
}
