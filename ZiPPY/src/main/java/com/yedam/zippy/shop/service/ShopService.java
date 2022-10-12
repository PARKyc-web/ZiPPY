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
	public ProductVO getProduct(int proNo);
	//디테일 이미지 조회
	public String[] getDetailImg(int proNo);
	//디테일 옵션 조회
    public List<ProductOptionVO> getDetailOpt(int proNo);
    
    // 장바구니 CRUD
    //등록
    public void insertCart(CartVO cartVO);
    //장바구니 상품 조회
    public List<ProductVO> getCartList(String email);
    //장바구니 상품개수 조회
    public int getMyCart(String email);
}
