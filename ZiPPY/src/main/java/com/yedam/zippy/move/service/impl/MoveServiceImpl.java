package com.yedam.zippy.move.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.move.mapper.MoveMapper;
import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveMyListVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;
import com.yedam.zippy.move.service.MoveReviewVO;
import com.yedam.zippy.move.service.MoveService;

@Service
public class MoveServiceImpl implements MoveService{
  
  // 저 경로에 저장하겠다.
//  private final String imgFolder = "C:/moveImg";
  private final String imgFolder = "/home";
  
  @Autowired
  MoveMapper mapper;
  
  @Autowired
  CommonService commonService;

//  @Override
//  public int moveUntactCheck(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3) {
//   
//    File file = new File("C:\\Users\\admin\\Desktop\\moveImage\\image111.jpg");
//    try {
//      images1.get(0).transferTo(file);
//    } catch (IllegalStateException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    
//    return mapper.moveUntactCheck(moveReq);
//  }

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
        
    MoveImageVO temp = new MoveImageVO();    
    
    for(int i=0; i<images1.size(); i++) {
      temp = new MoveImageVO();      
      temp.setHouseImg(commonService.saveImage(images1.get(i), "move"));
      temp.setImgType(1);
      temp.setEstimateNo(moveReq.getEstimateNo());
      
      mapper.insertPhoto(temp);
    }
    
    for(int i=0; i<images2.size(); i++) {
      temp = new MoveImageVO();
      temp.setHouseImg(commonService.saveImage(images2.get(i), "move"));
      temp.setImgType(2);
      temp.setEstimateNo(moveReq.getEstimateNo());
      
      mapper.insertPhoto(temp);
    }
    
    for(int i=0; i<images3.size(); i++) {
      temp = new MoveImageVO();
      temp.setHouseImg(commonService.saveImage(images3.get(i), "move"));
      temp.setImgType(3);
      temp.setEstimateNo(moveReq.getEstimateNo());
      
      mapper.insertPhoto(temp);
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
//  @Override
//  public Page<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo) {
//    return mapper.getEstimateResult(vo);
//  }
  @Override
  public List<MoveEstimateVO> getEstimateResult(MoveEstimateVO vo) {
    return mapper.getEstimateResult(vo);
  }
  
  //비대면 사진조회
  @Override
  public List<MoveImageVO> selectAllPhoto(MoveImageVO vo) {
    return mapper.selectAllPhoto(vo);
  }
  
  //업체가 견적 보냈는지 안보냈는지 확인 
  @Override
  public List<MoveCompanyEstimateVO> moveWhether(MoveCompanyEstimateVO vo) {
    return mapper.moveWhether(vo);
  }
  
  //견적서 인서트 - 업체(1차)
  @Override
  public int makeEstimate(MoveResponseVO vo) {
    return mapper.makeEstimate(vo);
  }

//견적상태 업데이트 (견적요청후, 상태 0으로 변경)
@Override
public int moveStatusUpdateZero(MoveRequestVO vo) {
  return mapper.moveStatusUpdateZero(vo);
} 
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
//  @Override
//  public int moveStatusUpdate(MoveResponseVO vo) {
//    return mapper.moveStatusUpdate(vo);
//  }

  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
  @Override
  public int moveStatusUpdate(Integer estimateNo, String email) {
    return mapper.moveStatusUpdate(estimateNo, email);
  }
  
  //견적서 수정 - 업체 (2차견적)
  @Override
  public int moveEstimateUpdate(MoveResponseVO vo) {
    return mapper.moveEstimateUpdate(vo);
  }
  //견적상태 업데이트 (2차 견적서 발송후, 상태 2로 변경)
  @Override
  public int moveStatusSecondUpdate(MoveResponseVO vo) {
    return mapper.moveStatusSecondUpdate(vo);
  }
  
  //3 : 예약요청
  @Override
  public int moveStatusThirdUpdate(MoveResponseVO vo) {
    return mapper.moveStatusThirdUpdate(vo);
  }
  
  //4 : 예약확정
  @Override
  public int moveStatusFourthUpdate(MoveResponseVO vo) {
    return mapper.moveStatusFourthUpdate(vo);
  }
  
  //5 : 이사완료
  @Override
  public int moveStatusFifthUpdate(MoveResponseVO vo) {
    return mapper.moveStatusFifthUpdate(vo);
  }
  
  //9 : 취소
  @Override
  public int moveStatusCancleUpdate(MoveResponseVO vo) {
    // TODO Auto-generated method stub
    return mapper.moveStatusCancleUpdate(vo);
  }
  
  
  
  //견전서 조회 - 업체
  @Override
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo) {
    return mapper.companyEstimate(vo);
  }
  
  //받은 견적 조회 - 사용자
  @Override
  public List<MoveMyListVO> getMyEstimateList(MoveMyListVO vo) {
    return mapper.getMyEstimateList(vo);
  }
  
  //받은견적 조회 - 단건
  @Override
  public MoveMyListVO getMyEstimateListOne(@Param("movingResponseNo")Integer movingResponseNo, @Param("userEmail")String userEmail) {
    return mapper.getMyEstimateListOne(movingResponseNo,userEmail);
  }
  
  //사용자의 예약내역 확인 (요청:3, 예약확정:4, 이사완료:5)
  @Override
  public List<MoveMyListVO> moveReserve(MoveMyListVO vo) {
    return mapper.moveReserve(vo);
  }
  
  //업체조회 페이지
  @Override
  public List<MoveMyListVO> getCompanyList(MoveMyListVO vo) {
    return mapper.getCompanyList(vo);
  }
  
  //업체조회 - 단건
  @Override
  public List<MoveMyListVO> moveCompanyOne(MoveMyListVO vo) {
    return mapper.moveCompanyOne(vo);
  }
  
  //해당찜조회
  @Override
  public List<BookmarkVO> getWishOneList(int sId, String email, int serviceType) {
    return mapper.getWishOneList(sId, email, serviceType);
  }
  
  //리뷰출력
  @Override
  public List<MoveReviewVO> showReview(MoveReviewVO vo) {
    return mapper.showReview(vo);
  }
  
  //리뷰개수
  @Override
  public List<MoveReviewVO> moveRvCount(String serviceId) {
    return mapper.moveRvCount(serviceId);
  }
}
