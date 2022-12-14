package com.yedam.zippy.shop.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.common.service.ReviewBoardVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.shop.mapper.ShopMapper;
import com.yedam.zippy.shop.service.CartVO;
import com.yedam.zippy.shop.service.OrderVO;
import com.yedam.zippy.shop.service.ProductImgVO;
import com.yedam.zippy.shop.service.ProductOptionVO;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.PurchaseVO;
import com.yedam.zippy.shop.service.QnaVO;
import com.yedam.zippy.shop.service.ShopService;
import com.yedam.zippy.used.service.UsedImagesVO;

@Service
public class ShopServiceImpl implements ShopService {
  
  @Autowired
  ShopMapper mapper;
  @Autowired
  CommonService commonService;
  
  //상품 전체조회
  @Override
  public List<ProductVO> getProductList() {
    return mapper.getProductList();
  }
  //상품 카테고리 조회
  @Override
  public Page<ProductVO> getCategoryList(String category, String dropbox) {
    return mapper.getCategoryList(category, dropbox);
  }
  //상품 키워드 조회
  @Override
  public Page<ProductVO> getKeywordList(String keyword, String dropbox) {
    return mapper.getKeywordList(keyword, dropbox);
  }
  //상품 단건조회
  @Override
  public ProductVO getProduct(int proNo) {
    ProductVO vo = mapper.getProduct(proNo);
    return vo;
  }
  //디테일 이미지 조회
  @Override
  public String[] getDetailImg(int proNo) {
    return mapper.getDetailImg(proNo);
  }
  //디테일 옵션 조회
  @Override
  public List<ProductOptionVO> getDetailOpt(int proNo) {
    return mapper.getDetailOpt(proNo);
  }

  
  //장바구니 CRUD
  //장바구니 등록
  @Override
  public void insertCart(CartVO cartVO) {
    mapper.insertCart(cartVO);
  }
  //장바구니 개수 조회
  @Override
  public int getMyCart(String email) {
    return mapper.getMyCart(email);
  }
  //장바구니 목록(상품정보, 가격)
  @Override
  public List<CartVO> getMyCartList(String email) {
    List<CartVO> list = mapper.getMyCartList(email);
    for (CartVO x : list) {
      x.setProductVO(mapper.getProduct(x.getCartPno()));
      x.setCartPrice(x.getProductVO().getProPrice() + x.getOptPrice());
    }
    return list;
  }
  //장바구니 삭제
  @Override
  public void deleteCart(List<CartVO> selected) {
    mapper.deleteCart(selected);
  }
  
  
  //주문 CRUD
  //구매 게시판 등록
  @Override
  public void insertPur(List<CartVO> selected, String payCode) {
    mapper.insertPur(selected, payCode);
  }
  //로그인 정보 출력
  @Override
  public GeneralUserVO getMyInfo(String email) {
    return mapper.getMyInfo(email);
  }
  //구매 물품 조회
  @Override
  public List<PurchaseVO> getMyPurList(String payCode, Integer purNo) {
    List<PurchaseVO> list = mapper.getMyPurList(payCode, purNo);
    for (PurchaseVO x : list) {
      x.setProductVO(mapper.getProduct(x.getPurPno()));
    }
    return list;
  }
  //주문 등록
  @Override
  public void insertOrder(OrderVO orderVO) {
    mapper.insertOrder(orderVO);
  }
  //주문 단건 조회
  @Override
  public OrderVO getOneOrder(String payCode) {
    return mapper.getOneOrder(payCode);
  }
  // 상품상태 업데이트
  @Override
  public int updateRvStatus(PurchaseVO purchaseVO) {
    return mapper.updateRvStatus(purchaseVO);
  }
  
  //마이페이지 CRUD
  //My wish
  public Page<Map<ProductVO, BookmarkVO>> getMyWishList(String email) {
   return mapper.getMyWishList(email);
  }
  
  //My QNA
  public List<Map<QnaVO, ProductVO>> getMyQna(String email){
    return mapper.getMyQna(email);
  }
  
  //My review
  public List<Map<ReviewBoardVO, ProductVO>> getMyRv(String email) {
    return mapper.getMyRv(email);
  }
  
//판매자 CRUD
  //상품등록
  @Override
  public void insertProduct(ProductVO productVO, List<ProductOptionVO> options, MultipartFile image,
      List<MultipartFile> images) {
    // 메인이미지 등록
    String mainImage = commonService.saveImage(image, "shop");
    productVO.setProMainImg(mainImage);
    
    mapper.insertProduct(productVO, options, image, images);
    // 옵션 등록
    if (options.size() > 0) {
      for (ProductOptionVO x : options) {
        x.setProNo(productVO.getProNo());
      }
      mapper.insertOpt(options);
    }
    // 상세이미지 등록
    ProductImgVO vo = new ProductImgVO();
    for(int i=0; i<images.size(); i++) {
      vo = new ProductImgVO();
      vo.setProNo(productVO.getProNo());
      vo.setProImg(commonService.saveImage(images.get(i), "shop"));
      
      mapper.insertImg(vo);
    } 
  }
  //상품수정
  @Override
  public void updateProduct(ProductVO productVO, List<ProductOptionVO> options, MultipartFile image,
      List<MultipartFile> images) {
    // 메인이미지 등록
    String mainImage = commonService.saveImage(image, "shop");
    productVO.setProMainImg(mainImage);
    // 전체 수정
    mapper.updateProduct(productVO, options, image, images);
    // 옵션 삭제
    mapper.deleteOpt(productVO.getProNo());
    // 이미지 삭제
    mapper.deleteImg(productVO.getProNo());
    // 옵션 등록
    if (options.size() > 0) {
      for (ProductOptionVO x : options) {
        x.setProNo(productVO.getProNo());
      }
      mapper.insertOpt(options);
    }
    // 상세이미지 등록
    ProductImgVO vo = new ProductImgVO();
    for(int i=0; i<images.size(); i++) {
      vo = new ProductImgVO();
      vo.setProNo(productVO.getProNo());
      vo.setProImg(commonService.saveImage(images.get(i), "shop"));
      
      mapper.insertImg(vo);
    } 
  }
  
  @Override
  public void insertPurOne(ProductVO product, String payCode, String email) {
    mapper.insertPurOne(product, payCode, email);

  }

  @Override
  public List<ProductVO> getMyProList(ProductVO productVO, String keyword) {
    return mapper.getMyProList(productVO, keyword);
  }

  @Override
  public void updateStatus(ProductVO productVO) {
    mapper.updateStatus(productVO);
  }

  @Override
  public List<PurchaseVO> getSellerPurList(ProductVO productVO, String keyword) {
    return mapper.getSellerPurList(productVO, keyword);
  }

  @Override
  public void updateOrdStatus(PurchaseVO purchaseVO) {
    mapper.updateOrdStatus(purchaseVO);
  }

  @Override
  public void insertQna(QnaVO qnaVO) {
    mapper.insertQna(qnaVO);
  }

  @Override
  public Page<QnaVO> getQnaList(int proNo) {
    return mapper.getQnaList(proNo);
  }

  @Override
  public List<OrderVO> getMyOrderList(String email) {
    List<OrderVO> list = mapper.getMyOrderList(email);
    for(OrderVO x : list) {
      x.setProductVO(mapper.getOrdProInfo(x.getPayCode()));
    }
    return list;
  }
  @Override
  public List<QnaVO> getSellerQnaList(String email) {
    return mapper.getSellerQnaList(email);
  }
  @Override
  public int updateQnaAnswer(QnaVO qnaVO) {
    return mapper.updateQnaAnswer(qnaVO);
  }

 
}
