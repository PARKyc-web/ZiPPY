package com.yedam.zippy.move.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.move.mapper.MoveMapper;
import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;
import com.yedam.zippy.move.service.MoveService;

@Service
public class MoveServiceImpl implements MoveService{
  
  // 저 경로에 저장하겠다.
  private final String imgFolder = "C:/moveImg";
  
  @Autowired
  MoveMapper mapper;

  @Override
  public int moveUntactCheck(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3) {
   
    File file = new File("C:\\Users\\admin\\Desktop\\moveImage\\image111.jpg");
    try {
      images1.get(0).transferTo(file);
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return mapper.moveUntactCheck(moveReq);
  }

  @Override
  public int moveContactCheck(MoveRequestVO moveReq) {

    return mapper.moveContactCheck(moveReq);
  }
  
  //사진
  
  public MoveImageVO[] storeMoveImg(List<MultipartFile> images, int imgType, int id) { //받아올 매개변수 확인

    //폴더없을때 생성
    File folder = new File(imgFolder);
    
    //db에 넣을 경로
    MoveImageVO[] dbPath = new MoveImageVO[images.size()];    
    
    if(!folder.exists()) {
      //폴더없을때 만들어라
      try {
        
        folder.mkdir();
      } catch(Exception e) {
        e.getStackTrace();
      } 
    }
    
    //이제 폴더가 있다고 가정하고 쓰는 영역
    
    Random random = new Random();
    
    for(int i=0; i<images.size(); i++) {
      long now = System.currentTimeMillis();
      String imagePath = now + "_" + random.nextInt(100) + images.get(i).getOriginalFilename(); 
      
      //이미지 저장
      //00폴더에 9999이름으로 쓰겠다.
      File write = new File(imgFolder + File.separator + imagePath);
      System.out.println(write.toString());
      
      try {
        images.get(i).transferTo(write);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      //배열에 경로를 넣어준다.    
      MoveImageVO vo = new MoveImageVO();
      
      vo.setEstimateNo(id);
      vo.setHouseImg(write.toString());
      vo.setImgType(imgType);      
      
      dbPath[i] = vo;
    } 
    
    return dbPath;
  }
  
  
  @Override
  public void insertUntactEstimate(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3) {

    //db에 넣기
    //path담을 공간 만들기    
    mapper.insertContactEstimate(moveReq);
    
    MoveImageVO[] vo0 = storeMoveImg(images1, 1, moveReq.getEstimateNo());
    MoveImageVO[] vo1 = storeMoveImg(images1, 2, moveReq.getEstimateNo());
    MoveImageVO[] vo2 = storeMoveImg(images1, 3, moveReq.getEstimateNo());
    
    
    for(int i=0; i<vo0.length; i++) {
      vo0[i].setEstimateNo(moveReq.getEstimateNo());
      mapper.insertPhoto(vo0[i]);
    }
    
    for(int i=0; i<vo1.length; i++) {
      vo1[i].setEstimateNo(moveReq.getEstimateNo());
      mapper.insertPhoto(vo1[i]);
    }
    
    for(int i=0; i<vo2.length; i++) {
      vo2[i].setEstimateNo(moveReq.getEstimateNo());
      mapper.insertPhoto(vo2[i]);
    }
    
  }
  
  @Override
  public void insertContactEstimate(MoveRequestVO moveReq) {    
    mapper.insertContactEstimate(moveReq);    
  }
  
  //전체조회 - 업체
  @Override
  public List<MoveRequestVO> getEstimateList(MoveEstimateVO vo) {
    return mapper.getEstimateList(vo);
  }
  //서비스 : 작업을 하는 곳 -> ex.조회는 딱히 작업할 필요가 없어서 바로 매퍼를 리턴해준다.
  // 다른 작업이 필요한 경우에는(2차가공) ex. db에서 정보 가져와서 서비스에서 랜덤으로 숫자 발생시키고 전체견적정보를 가져와 배열에 넣는다  
  
  //전체조회 - 유저
  @Override
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo) {
    return mapper.getEstimateResult(vo);
  }
  
  //견적서 인서트 - 업체
  @Override
  public int makeEstimate(MoveResponseVO vo) {
    return mapper.makeEstimate(vo);
  }
  
  //견전서 조회 - 업체
  @Override
  public List<MoveCompanyEstimateVO> companyEstimate(MoveCompanyEstimateVO vo) {
    return mapper.companyEstimate(vo);
  }
}
