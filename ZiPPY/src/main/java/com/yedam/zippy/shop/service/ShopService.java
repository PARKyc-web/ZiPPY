package com.yedam.zippy.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.used.service.UsedImagesVO;

@Service
public interface ShopService {
  // 상품 CRUD
  // 전체조회
  public List<ProductVO> getProductList();

  // 전체조회(카테고리)
  public List<ProductVO> getCategoryList(String category);

  // 전체조회(키워드)
  public List<ProductVO> getKeywordList(String keyword);

  // 단건조회(디테일)
  public ProductVO getProduct(int proNo);

  // 디테일 이미지 조회
  public String[] getDetailImg(int proNo);

  // 디테일 옵션 조회
  public List<ProductOptionVO> getDetailOpt(int proNo);

  // 찜 여부 조회
  public void getHeart(String email);

  // 장바구니 CRUD
  // 등록
  public void insertCart(CartVO cartVO);

  // 장바구니 상품개수 조회
  public int getMyCart(String email);

  // 내 장바구니 조회
  public List<CartVO> getMyCartList(String email);

  // 삭제
  public void deleteCart(List<CartVO> selected);

  // 주문 CRUD
  // 등록(from 장바구니)
  public void insertPur(List<CartVO> selected, String payCode);

  // 등록(from 디테일)
  public void insertPurOne(ProductVO product, String payCode, String email);

  // 내 정보 조회
  public GeneralUserVO getMyInfo(String email);

  // 상품정보 조회
  public List<PurchaseVO> getMyPurList(String payCode);

  // 주문등록
  public void insertOrder(OrderVO orderVO);

  // 판매자 CRUD
  //상품등록
  public void insertProduct(ProductVO productVO, List<ProductOptionVO> options, MultipartFile image, List<MultipartFile> images);
  // 메인이미지 등록
  public String proMainImg(MultipartFile image);
  // 상세이미지 등록
  public ProductImgVO[] proImgs(List<MultipartFile> images);
  //등록 상품 조회
  public List<ProductVO> getMyProList(ProductVO productVO);
  //등록 상품상태 수정
  public void updateStatus(ProductVO productVO);
};
