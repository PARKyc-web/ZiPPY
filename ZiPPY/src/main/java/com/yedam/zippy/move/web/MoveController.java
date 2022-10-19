package com.yedam.zippy.move.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.move.service.MoveCompanyEstimateVO;
import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveResponseVO;
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
    System.out.println(moveReq);        
    System.out.println(images1);
    System.out.println(images2);
    System.out.println(images3);
    
    
    
   if(moveReq.getEstimateType().equals("대면견적") ) {
     service.insertContactEstimate(moveReq);
     System.out.println(moveReq);
   } else {
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
  
  //견적서 인서트- 업체
  @PostMapping("/moveEstimate")
  public String makeEstimate(MoveResponseVO vo) {    
    service.makeEstimate(vo);
    return "";
  }
  
  
  //업체 견적서 내역
  @GetMapping("/moveCompanyEstimate")
  public List<MoveResponseVO> companyEstimate(MoveCompanyEstimateVO vo){
    
    System.out.println(vo);
    return service.companyEstimate(vo);
  }
  
  
  //단건조회 - 업체
  
  
  //사용자가 보낸 자신의 견적 히스토리 확인
  @GetMapping("/moveResult")
  public List<MoveEstimateVO> selectAllResult(MoveEstimateVO vo){
    
    System.out.println(vo);
    return service.getEstimateResult(vo);
  }

  
}
