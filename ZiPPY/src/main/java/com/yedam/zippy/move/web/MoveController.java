package com.yedam.zippy.move.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.move.service.MoveEstimateVO;
import com.yedam.zippy.move.service.MoveRequestVO;
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
    
    System.out.println(moveReq.getEstimateType());
    
    
   if(moveReq.getEstimateType().equals("대면견적") ) {
     service.insertContactEstimate(moveReq);
   } else {
     service.insertUntactEstimate(moveReq, images1, images2, images3);
     
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
    
    return "";
  }
  
  
  //전체조회
  
//  @GetMapping("/moveEstimate")
//  public List<MoveRequestVO> selectAll(MoveRequestVO vo){
//    
//    System.out.println(vo);
//    return service.getEstimateList(vo);
//  }
//  
  @GetMapping("/moveEstimate")
  public List<MoveEstimateVO> selectAll(MoveEstimateVO vo    ){
    
    System.out.println(vo);
    String dropbox = vo.getDropbox();
//    
//    if (dropbox.equals("전체조회")) {
//      dropbox = "전체조회";
//    } else if (dropbox.equals("대면견적")) {
//      dropbox = "대면견적";
//    } else if (dropbox.equals("비대면견적")) {
//      dropbox = "비대면견적";
//    } else if (dropbox.equals("최신요청일자순")) {
//      dropbox = "최신요청일자순";
//    } else if (dropbox.equals("오래된요청일자순")) {
//      dropbox = "오래된요청일자순";
//    } else if (dropbox.equals("현재날짜요청")) {
//      dropbox = "현재날짜요청";
//    } else if (dropbox.equals("출발지순")) {
//      dropbox = "출발지순";
//    } else if (dropbox.equals("도착지순")) {
//      dropbox = "도착지순";
//    }
    System.out.println("===========");
    System.out.println("dropbox : "+dropbox);
    System.out.println("vo : "+vo);
    
    return service.getEstimateList(vo);
  }
  

}
