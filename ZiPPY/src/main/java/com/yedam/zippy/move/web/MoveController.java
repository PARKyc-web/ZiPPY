package com.yedam.zippy.move.web;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveImageVO;
import com.yedam.zippy.move.service.MoveMyListVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;
import com.yedam.zippy.move.service.MoveReviewVO;
import com.yedam.zippy.move.service.MoveService;

@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@RequestMapping("/move")
public class MoveController {

  @Autowired
  MoveService service;

  // 견적요청
  @PostMapping("/moveUntactCheck")
  public String moveUntactCheck(MoveRequestVO moveReq, List<MultipartFile> images1, List<MultipartFile> images2, List<MultipartFile> images3) {
    //견적타입이 '대면견적'일 때
   if(moveReq.getEstimateType().equals("대면견적") ) {
     service.insertContactEstimate(moveReq);
     System.out.println(moveReq);
   } else {
   //견적타입이 '비대면견적'일 때
     service.insertUntactEstimate(moveReq, images1, images2, images3);
     System.out.println(moveReq);     
   }
    
    
    //images1 의 0번째 파일을 image111.jpg이름으로 사용하겠다.
//    
//    File file = new File("C:\\Users\\admin\\Desktop\\moveImage\\image111.jpg");
//    images1.get(0).transferTo(file);    
    
//  System.out.println(service.moveUntactCheck(moveRequ)); 데이터 다 넘어오는거 확인하고 서비스 실행 
    return "";
  }
  
  @PostMapping("/moveContactCheck")
  public String moveContactCheck(MoveRequestVO moveReq) {
    
    System.out.println(moveReq);    
    
    service.moveContactCheck(moveReq);
   
    return "";
  }
  

  
  //전체조회 - 업체가 받은 요청
  @GetMapping("/moveEstimate")
  public List<MoveRequestVO> selectAll(MoveEstimateVO vo){       
    return service.getEstimateList(vo);
  }
  
  //비대면 사진조회
  @GetMapping("/movePhoto")
  public List<MoveImageVO> selectAllPhoto(MoveImageVO vo){       
    return service.selectAllPhoto(vo);
  }
  
  //받은 견적에서 견적전, 견적후 구분 - 업체
  @GetMapping("/moveWhether")
  public List<MoveCompanyEstimateVO> moveWhether(MoveCompanyEstimateVO vo){
    return service.moveWhether(vo);
  }
  
  //견적서 인서트- 업체(1차까지)
  @PostMapping("/moveEstimate")
  public int makeEstimate(@RequestBody MoveResponseVO vo) {   
    System.out.println(vo);    
    return service.makeEstimate(vo);
  }
  
  //견적상태 업데이트 (견적요청후, 상태 0으로 변경)
  @PostMapping("/moveStatusUpdateZero")
  public String moveStatusUpdateZero(MoveRequestVO vo) {
    System.out.println(vo);
    
    service.moveStatusUpdateZero(vo);
    return "";
  }
  
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
//  @PostMapping("/moveStatusUpdate")
//  public String moveStatusUpdate(MoveResponseVO vo) {
//    System.out.println(vo);
//    
//    service.moveStatusUpdate(vo);
//    return "";
//  }
  
  //견적상태 업데이트 (1차 견적서 발송후, 상태 1로 변경)
  @PostMapping("/moveStatusUpdate")
  public String moveStatusUpdate(@Param("estimateNo")Integer estimateNo, @Param("email")String email) { 
    service.moveStatusUpdate(estimateNo, email);
    return "";
  }
  
  //견적서 수정 - 업체(2차)
  @PostMapping("/moveEstimateUpdate")
  public String moveEstimateUpdate(MoveResponseVO vo) {
    System.out.println(vo);
    service.moveEstimateUpdate(vo);
    return "";
  }
  
  //견적상태 업데이트 (2차 견적서 발송후, 상태 2로 변경)
  @PostMapping("/moveStatusSecondUpdate")
  public String moveStatusSecondUpdate(MoveResponseVO vo) {
    System.out.println(vo);
    
    service.moveStatusSecondUpdate(vo);
    return "";
  }
  
  //견적상태 업데이트 (사용자가 예약요청 후, 상태 3으로 변경)
  @PostMapping("/moveStatusThirdUpdate")
  public int moveStatusThirdUpdate(MoveResponseVO vo) {
    System.out.println(vo);
    
    return service.moveStatusThirdUpdate(vo);
  }
  
  //견적상태 업데이트 (업체가 예약확정 후, 상태 4으로 변경)
  @PostMapping("/moveStatusFourthUpdate")
  public String moveStatusFourthUpdate(MoveResponseVO vo) {
    System.out.println(vo);
    
    service.moveStatusFourthUpdate(vo);
    return "";
  }
  
  //견적상태 업데이트 (이사완료 후, 상태 5으로 변경)
  @PostMapping("/moveStatusFifthUpdate")
  public String moveStatusFifthUpdate(MoveResponseVO vo) {
    System.out.println(vo);  
    service.moveStatusFifthUpdate(vo);
    return "";
  }
  
  //견적상태 업데이트 (예약취소하면, 상태 9으로 변경)
  @PostMapping("/moveStatusCancleUpdate")
  public String moveStatusCancleUpdate(MoveResponseVO vo) {
    System.out.println(vo);
    
    service.moveStatusCancleUpdate(vo);
    return "";
  }
  
  //업체 견적서 내역
  @GetMapping("/moveCompanyEstimate")
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo){
    
    System.out.println(vo);
    return service.companyEstimate(vo);
  }
  
//  @GetMapping("/moveCompanyEstimate")
//  public List<MoveResponseVO> companyEstimate(Map<String, String> map){
//    
//    System.out.println(map);
//    
//    return service.companyEstimate(map);
//  }
//  

  
  //사용자가 보낸 자신의 견적 히스토리 확인
  
  @GetMapping("/moveResult")
  public List<MoveEstimateVO> selectAllResult(MoveEstimateVO vo){
    System.out.println(vo);
    return service.getEstimateResult(vo);
  }
//  @GetMapping("/moveResult")
//  public PageInfo<MoveEstimateVO> selectAllResult(MoveEstimateVO vo){
//    String order="request_date DESC";
//    System.out.println(vo);
//    
//    
//    PageHelper.startPage(vo.getPageNum(), 10, order);
//    return PageInfo.of(service.getEstimateResult(vo));
//  }
  

  //사용자가 받은 견적 리스트 확인
  @GetMapping("/moveMyList")
  public List<MoveMyListVO> getMyEstimateList(MoveMyListVO vo){
    System.out.println("MoveMyListVO : "+vo);
    return service.getMyEstimateList(vo);
  }
  
  //사용자가 받은 견적 리스트 확인 -단건
  @GetMapping("/moveMyListOne")
  public MoveMyListVO getMyEstimateListOne(@Param("movingResponseNo")Integer movingResponseNo, @Param("userEmail")String userEmail){
    System.out.println("MoveMyListVO : "+movingResponseNo+ userEmail);
    return service.getMyEstimateListOne(movingResponseNo, userEmail);
  }
  
  //사용자의 예약내역 확인 (요청:3, 예약확정:4, 이사완료:5)
  @GetMapping("/moveReserve")
  public List<MoveMyListVO> moveReserve(MoveMyListVO vo){
    System.out.println("MoveMyListVO : "+vo);
    return service.moveReserve(vo);
  }
  
  //업체조회 페이지
  @GetMapping("/moveCompanyList")
  public List<MoveMyListVO> getCompanyList(MoveMyListVO vo){
    
    System.out.println(vo);
    return service.getCompanyList(vo);
  }
  
  //업체조회 페이지 - 단건
  @GetMapping("/moveCompanyOne")
  public List<MoveMyListVO> moveCompanyOne(MoveMyListVO vo){
    
    System.out.println(vo);
    return service.moveCompanyOne(vo);
  }
  
  //해당상품 찜 출력
 @GetMapping("/wishOneList")
 public List<BookmarkVO> getWishOneList(@RequestParam int sId, @RequestParam String email, @RequestParam int serviceType) {
   return service.getWishOneList(sId, email, serviceType);
 }
 
 // 후기 출력
 @GetMapping("/moveReview")
 public List<MoveReviewVO> showReview(MoveReviewVO vo) {
   return service.showReview(vo);
 } 
 
 //후기 개수
 @GetMapping("/moveRvCount")
 public List<MoveReviewVO> moveRvCount(@Param("serviceId")String serviceId) {
   return service.moveRvCount(serviceId);
 }
  
}
