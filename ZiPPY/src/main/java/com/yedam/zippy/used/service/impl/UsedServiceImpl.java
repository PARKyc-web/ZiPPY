package com.yedam.zippy.used.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedKeywordVO;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@Service
public class UsedServiceImpl implements UsedService {

  private final String usedImageFoler = "C:/usedImage";

  @Autowired
  UsedMapper mapper;

  @Override
  public List<UsedProductVO> usedList(String location, String keyword, String category, String checked,
      String dropbox) {
    return mapper.usedList(location, keyword, category, checked, dropbox);
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
    return mapper.deleteUsed(kNo);
  }

  @Override
  public List<UsedKeywordVO> showKeyword(String email) {
    return mapper.showKeyword(email);
  }
  
  @Override
  public List<UsedImagesVO> getImg(int pNo) {
    return mapper.getImg(pNo);
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
    mapper.insertUsed(product);
    UsedImagesVO[] vo = storeImages(images);

    for (int i = 0; i < vo.length; i++) {
      vo[i].setProductNo(product.getProductNo());
    }
    for (int i = 0; i < vo.length; i++) {
      mapper.insertImg(vo[i]);
    }
  }

  private UsedImagesVO[] storeImages(List<MultipartFile> images) {

    UsedImagesVO[] list = new UsedImagesVO[images.size()];

    File folder = new File(usedImageFoler);
    if (!folder.exists()) {
      folder.mkdir();
    }
    for (int i = 0; i < images.size(); i++) {
      long now = System.currentTimeMillis();
      Random rand = new Random();

      // ~~~~.jpg png
      String imagePath = now + "_" + rand.nextInt(100) + images.get(i).getOriginalFilename();

      File write = new File(folder + File.separator + imagePath);

      try {
        images.get(i).transferTo(write);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      UsedImagesVO vo = new UsedImagesVO();
      vo.setImage(write.toString());

      list[i] = vo;
    }

    return list;
  }

  @Override
  public void updateUsedProduct(UsedProductVO product, List<MultipartFile> images) {

    mapper.updateUsed(product);
    UsedImagesVO[] vo = updateImages(images);
    
    for (int i = 0; i < vo.length; i++) {
      vo[i].setProductNo(product.getProductNo());
    }
    for (int i = 0; i < vo.length; i++) {
      mapper.updateImg(vo[i]);
    }
  }
  
  private UsedImagesVO[] updateImages(List<MultipartFile> images) {

    UsedImagesVO[] list = new UsedImagesVO[images.size()];

    File folder = new File(usedImageFoler);
    if (!folder.exists()) {
      folder.mkdir();
    }
    
    for (int i = 0; i < images.size(); i++) {
      long now = System.currentTimeMillis();
      Random rand = new Random();

      // ~~~~.jpg png
      String imagePath = now + "_" + rand.nextInt(100) + images.get(i).getOriginalFilename();

      File write = new File(folder + File.separator + imagePath);

      try {
        images.get(i).transferTo(write);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      UsedImagesVO vo = new UsedImagesVO();
      vo.setImage(write.toString());

      list[i] = vo;
    }

    return list;
  }

}
