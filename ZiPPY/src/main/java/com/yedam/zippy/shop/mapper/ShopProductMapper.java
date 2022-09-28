package com.yedam.zippy.shop.mapper;

import java.util.List;

import com.yedam.zippy.shop.service.ShopProductVO;

public interface ShopProductMapper {
	
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
