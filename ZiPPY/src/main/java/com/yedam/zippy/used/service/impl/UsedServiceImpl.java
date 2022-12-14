package com.yedam.zippy.used.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedKeywordVO;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;
import com.yedam.zippy.validation.service.ValidationService;

@Service
public class UsedServiceImpl implements UsedService {

  @Autowired
  UsedMapper mapper;

  @Autowired
  CommonService commonService;
  
  @Autowired
  ValidationService validationService;
  
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
  public List<UsedProductVO> showRvAll(String email) {
    return mapper.showRvAll(email);
  }

  @Override
  public void insertUsedProduct(UsedProductVO product, List<MultipartFile> images) {
    /**
     * 0. ???????????? ???????????? ??????
     * 
     * 1. ????????? ?????? ?????????(????????? ??????) ex. ????????????, ????????? ??????, ??????
     * ?????? ???????????? ??? ?????????.
     * 
     * 2. ???????????? ?????? ????????? ???????????? ????????????.
     * 3. ????????? ???????????? ????????? VO??? ?????????
     * 
     * 4. ????????? ????????? ?????? ????????? VO??? ????????????
     * 
     * 5. VO??? ???????????? ?????????.
     */

    // ?????? ????????? ??????

    // ????????? ?????? ??????
    mapper.deleteImg(product);
    
    String mainImage = commonService.saveImage(images.get(0), "used");
    product.setMainImg(mainImage);   
    // ??? ?????? ??????
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
    mapper.deleteImg(product);
    
    String mainImage = commonService.saveImage(images.get(0), "used");
    product.setMainImg(mainImage);   
    mapper.updateUsed(product);
    
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
  public void sendKeyword(UsedProductVO product) {
    List<UsedKeywordVO> keywordList = findKeyword(product);
    
    List<UsedKeywordVO> sendingTarget = new ArrayList<UsedKeywordVO>();
    for(int i=0; i<keywordList.size(); i++) {
      System.out.println("???????????????");
      System.out.println("?????? ?????? : " + product.getProductName());
      System.out.println("????????? ?????? ::" + keywordList.get(i).getKeyword());
      
      if(product.getProductName().contains(keywordList.get(i).getKeyword())) {
        
        sendingTarget.add(keywordList.get(i));
      }
    }
    
    String mailContent = "";    
    for(int i=0; i<sendingTarget.size(); i++) {
        mailContent = "?????? [" + sendingTarget.get(i).getKeywordLocation() + "]??? ????????? Keyword : [" 
                    + sendingTarget.get(i).getKeyword() + "]??? ????????????"
                    + "\n ?????? [" + product.getProductName() + "]??? ?????????????????????";
        
        validationService.sendEmail(sendingTarget.get(i).getEmail(), "ZIPPY ????????????!", mailContent);       
    }    
  }
  
}
