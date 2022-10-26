package com.yedam.zippy.used.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.itextpdf.text.Image;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedKeywordVO;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@Service
public class UsedServiceImpl implements UsedService {

  @Autowired
  UsedMapper mapper;

  @Autowired
  CommonService commonService;
  
  @Override
  public Page<UsedProductVO> usedList(String location, String keyword, String category, String checked,
      String dropbox) {
    return mapper.usedList(location, keyword, category, checked, dropbox);
  }

  @Override
  public Page<UsedProductVO> userMain(String email) {
    return mapper.userMain(email);
  }

  @Override
  public UsedProductVO usedOne(int pNo) {
    return mapper.usedOne(pNo);
  }

  @Override
  public void insertUsed(UsedProductVO vo) {
    mapper.insertUsed(vo);
  }

  @Override
  public int updateUsed(UsedProductVO vo) {
    return mapper.updateUsed(vo);
  }

  @Override
  public int deleteUsed(int pNo) {
    return mapper.deleteUsed(pNo);
  }

  @Override
  public void viewCnt(int pNo) {
    mapper.viewCnt(pNo);
  }

  @Override
  public int insertImg(UsedImagesVO images) {
    return mapper.insertImg(images);
  }

  @Override
  public int deleteImg(UsedProductVO product) {
    return mapper.deleteImg(product);
  }

  @Override
  public int updateImg(UsedImagesVO images) {
    return mapper.updateImg(images);
  }

  @Override
  public int addKeyword(UsedKeywordVO keyword) {
    return mapper.addKeyword(keyword);
  }

  @Override
  public int delKeyword(int kNo) {
    return mapper.delKeyword(kNo);
  }

  @Override
  public List<UsedKeywordVO> showKeyword(String email) {
    return mapper.showKeyword(email);
  }

  @Override
  public List<UsedKeywordVO> findKeyword(UsedProductVO product) {
    return mapper.findKeyword(product);
  }

  @Override
  public List<UsedImagesVO> getImg(int pNo) {
    return mapper.getImg(pNo);
  }

  @Override
  public int dealRecord() {
    return mapper.dealRecord();
  }

  @Override
  public void insertUsedProduct(UsedProductVO product, List<MultipartFile> images) {
    /**
     * 0. 이미지를 삽입하는 방법
     * 
     * 1. 이미지 부모 테이블(텍스트 정보) ex. 중고상품, 쇼핑몰 상품, 매물
     * 먼저 데이터를 다 넣는다.
     * 
     * 2. 이미지를 우선 컴퓨터 어딘가에 저장한다.
     * 3. 저장한 이미지의 경로를 VO에 넣는다
     * 
     * 4. 경로를 제외한 모든 정보를 VO에 세팅한다
     * 
     * 5. VO를 테이블에 넣는다.
     */

    // 메인 이미지 설정

    // 이미지 전부 삭제
    mapper.deleteImg(product);
    
    String mainImage = commonService.saveImage(images.get(0), "used");
    product.setMainImg(mainImage);   
    // 글 정보 등록
    mapper.insertUsed(product);

    UsedImagesVO vo = new UsedImagesVO();
    vo.setProductNo(product.getProductNo());
    vo.setImage(mainImage);
    mapper.insertImg(vo);
    
    for(int i=1; i<images.size(); i++) {
      vo = new UsedImagesVO();
      vo.setProductNo(product.getProductNo());
      vo.setImage(commonService.saveImage(images.get(i), "used"));
      
      mapper.insertImg(vo);
    } 
  }

  @Override
  @Transactional
  public void updateUsedProduct(UsedProductVO product, List<MultipartFile> images) {
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    System.out.println("================================ RUNNNNNNNNNNN~~~~~~~~~~~~");
    mapper.deleteImg(product);
    
    String mainImage = commonService.saveImage(images.get(0), "used");
    product.setMainImg(mainImage);   
    mapper.updateUsed(product);
    
    UsedImagesVO vo = new UsedImagesVO();
    vo.setProductNo(product.getProductNo());
    vo.setImage(mainImage);
    mapper.insertImg(vo);
    System.out.println("아니 반복문 전인데 실행 됩니까?");
    for(int i=1; i<images.size(); i++) {
      System.out.println("사진이 등록되는 중입니다");
      vo = new UsedImagesVO();
      vo.setProductNo(product.getProductNo());
      vo.setImage(commonService.saveImage(images.get(i), "used"));
      
      mapper.insertImg(vo);
    } 
  }
  
}
