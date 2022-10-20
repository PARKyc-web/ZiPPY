package com.yedam.zippy.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.common.service.ReportVO;
import com.yedam.zippy.common.service.ReviewBoardVO;


@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@RequestMapping("/common")
public class CommonController {

  @Autowired
  CommonService service;

  // 찜 추가
  @PostMapping("/addWish")
  public int addWish(@RequestBody BookmarkVO bookmark) {
    System.out.println(bookmark);
    return service.addWish(bookmark);
  }

  // 찜 삭제
  @DeleteMapping("/delWish")
  public int delWish(@RequestBody Map<String, List<Integer>> bNo) {
    System.out.println("Run");
    System.out.println("bno"+bNo);
    for(int i =0; i<bNo.get("bNo").size(); i++) {
      service.delWish(bNo.get("bNo").get(i));
    }    
    return 1;
  }

  // 해당상품 찜 출력
  @GetMapping("/wishOne")
  public BookmarkVO getWishOne(@RequestParam int sId, @RequestParam String email, @RequestParam int serviceType) {
    return service.getWishOne(sId, email, serviceType);
  }

  // 찜 전체출력
  @GetMapping("/wishAll")
  public List<BookmarkVO> getWishAll(@RequestParam String email, @RequestParam int serviceType){
    return service.getWishAll(email, serviceType);
  }

  // 후기작성
  @PostMapping("/addRv")
  public int addReview(@RequestBody ReviewBoardVO rv) {
//    service.addReview(rv);
    System.out.println(rv);
    return 1;
  }

  // 후기 출력
  @GetMapping("/showRv")
  public List<ReviewBoardVO> showReview(@RequestBody ReviewBoardVO rv) {
    return service.showReview(rv);
  }  

 
  @GetMapping("img/{image}")
  public void getImage(HttpServletResponse response, @PathVariable String image) throws Exception {
    try {
      String path = "C:/dev/image/" + image; // 경로에 접근할 때 역슬래시('\') 사용

      File file = new File(path);
      response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를
                                                                                          // 알려주는 헤더

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
  
  @PostMapping("/addReport")
  public int addReport(ReportVO vo) {
    return service.addReport(vo);
  }

}
