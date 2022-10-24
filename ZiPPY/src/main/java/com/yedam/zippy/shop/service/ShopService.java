package com.yedam.zippy.shop.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.used.service.UsedImagesVO;

@Service
public interface ShopService {
  // 상품 CRUD
  // 전체조회
  public List<ProductVO> getProductList();
  // 전체조회(카테고리)
  public Page<ProductVO> getCategoryList(String category, String dropbox);
  // 전체조회(키워드)
  public Page<ProductVO> getKeywordList(String keyword, String dropbox);
  // 단건조회(디테일)
  public ProductVO getProduct(int proNo);
  // 디테일 이미지 조회
  public String[] getDetailImg(int proNo);
  // 디테일 옵션 조회
  public List<ProductOptionVO> getDetailOpt(int proNo);
  // 주문서 전체조회
  public List<OrderVO> getMyOrderList(String email);
//  //구매내역 정보 조회 
//  public ProductVO getOrdProInfo(String payCode);

  
  // 장바구니 CRUD
  // 등록
  public void insertCart(CartVO cartVO);
  // 장바구니 상품개수 조회
  public int getMyCart(String email);
  // 내 장바구니 조회
  public List<CartVO> getMyCartList(String email);
  // 삭제
  public void deleteCart(List<CartVO> selected);
  
  
  // QNA CRUD
  // qna 등록
  public void insertQna(QnaVO qnaVO);
  // qna 조회
  public Page<QnaVO> getQnaList(int proNo);

  
  // 주문 CRUD
  // 등록(from 장바구니)
  public void insertPur(List<CartVO> selected, String payCode);
  // 등록(from 디테일)
  public void insertPurOne(ProductVO product, String payCode, String email);
  // 내 정보 조회
  public GeneralUserVO getMyInfo(String email);
  // 상품정보 조회
  public List<PurchaseVO> getMyPurList(String payCode, Integer purNo);
  // 주문등록
  public void insertOrder(OrderVO orderVO);
  // 주문 단건 조회
  public OrderVO getOneOrder(String payCode);
  // 상품상태 업데이트
  public int updateRvStatus(PurchaseVO purchaseVO);
  
  // 마이페이지 CRUD
  public Page<Map<ProductVO, BookmarkVO>>getMyWishList(String email);
  
  //***
  // 판매자 CRUD
  //상품등록
  public void insertProduct(ProductVO productVO, List<ProductOptionVO> options, MultipartFile image, List<MultipartFile> images);
  // 메인이미지 등록
  public String proMainImg(MultipartFile image);
  // 상세이미지 등록
  public ProductImgVO[] proImgs(List<MultipartFile> images);
  //등록 상품 조회(전체조회)
  public List<ProductVO> getMyProList(ProductVO productVO, String keyword);
  //등록 상품상태 수정
  public void updateStatus(ProductVO productVO);
  // 상품정보 수정
  public void updateProduct(ProductVO productVO, List<ProductOptionVO> options, MultipartFile image,
  List<MultipartFile> images);
  // 판매내역 조회(전체조회)
  public List<PurchaseVO> getSellerPurList(ProductVO productVO, String keyword);
  // 주문상태 수정(배송)
  public void updateOrdStatus(PurchaseVO purchaseVO);

};
