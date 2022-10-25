package com.yedam.zippy.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.yedam.zippy.common.mapper.CommonMapper;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.common.service.ReportVO;
import com.yedam.zippy.common.service.ReviewBoardVO;

@Service
public class CommonServiceIml implements CommonService {

  private final String imageFolder = "C:/dev/image"; 
  
  
  @Autowired
  CommonMapper mapper;

  @Override
  public int addWish(BookmarkVO bookmark) {
    return mapper.addWish(bookmark);
  }

  @Override
  public int delWish(int bNo) {
    return mapper.delWish(bNo);
  }

  @Override
  public BookmarkVO getWishOne(int sId, String email, int serviceType) {
    return mapper.getWishOne(sId, email, serviceType);
  }

  @Override
  public Page<BookmarkVO> getWishAll(String email, int serviceType) {
    return mapper.getWishAll(email,serviceType);
  }

  @Override
  public int addReview( ReviewBoardVO rv) {
    return mapper.addReview(rv);
  }

  @Override
  public List<ReviewBoardVO> showReview(ReviewBoardVO rv) {
    return showReview(rv);
  }
  
  @Override
  public int addReport(ReportVO vo) {
    return mapper.addReport(vo);
  }

//  @Override
//  public List<ReviewBoardVO> showProReview(int serviceType, int serviceId) {
//    return mapper.showProReview(serviceType, serviceId);
//  }
  
  @Override
  public Page<ReviewBoardVO> showProReview(int serviceType, int serviceId) {
    return mapper.showProReview(serviceType, serviceId);
  }
  
  @Override
  public List<ReportVO> showReportList() {
    return mapper.showReportList();
  }

    
  @Override
  public String saveImage(MultipartFile image, String type) {        
    File folder = new File(imageFolder + File.separator + type);    
    if(!folder.exists()) {        
       try {
         folder.mkdir();
         
       }catch (Exception e) {
         e.getStackTrace();
      }
    }
    
    long now = System.currentTimeMillis();
    Random rand = new Random();    
    String path = now + rand.nextInt(10) + image.getOriginalFilename();     
    
    File write = new File(folder + File.separator + path);      
    System.out.println("저장할 위치 :: " + write.toString());
    try {
      image.transferTo(write);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return path;
  }
  
  @Override
  public void getImage(HttpServletResponse response, String imageName, String type) throws Exception {
    try {
      String path = imageFolder + File.separator + type + File.separator + imageName;
      // 경로에 접근할 때 역슬래시('\') 사용  
      
      File file = new File(path);
      // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를
      // 알려주는 헤더
      response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

      FileInputStream fileInputStream = new FileInputStream(path); // 파일 읽어오기
      OutputStream out = response.getOutputStream();

      int read = 0;
      byte[] buffer = new byte[1024];
      while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
        out.write(buffer, 0, read);
      }

    } catch (Exception e) {
      throw new Exception("download error");
    }
  }
}
