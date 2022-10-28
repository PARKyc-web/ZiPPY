package com.yedam.zippy.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.common.service.ReportVO;
import com.yedam.zippy.common.service.ReviewBoardVO;


@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@RequestMapping("/common")
//@RequestMapping("/zippy/common")
public class CommonController {
  
  @Autowired
  CommonService service;
  
  private final String imagePath = "";
  
//  private final String propertyPath = "";
//  private final String memberPath = "";
//  private final String usedPath = "";
//  private final String movePath = "";  
//  private final String shopPath = ""; 

    

  // 찜 추가
  @PostMapping("/addWish")
  public int addWish(@RequestBody BookmarkVO bookmark) {
    System.out.println(bookmark);
    //System.out.println(service.addWish(bookmark));
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
  public PageInfo<BookmarkVO> getWishAll(@RequestParam String email, @RequestParam int serviceType, @RequestParam int pageNum){
    System.out.println(service.getWishAll(email, serviceType));
    String order = "";
    
    PageHelper.startPage(pageNum, 6, order);
    return PageInfo.of(service.getWishAll(email, serviceType));
  }

  // 후기작성
  @PostMapping("/addRv")
  public int addReview(@RequestBody ReviewBoardVO rv) {
    service.addReview(rv);
    System.out.println(rv);
    return 1;
  }

  // 후기 출력
  @GetMapping("/showRv")
  public List<ReviewBoardVO> showReview(@RequestBody ReviewBoardVO rv) {
    return service.showReview(rv);
  } 
 
  @GetMapping("img/{type}/{image}")
  public void getImage(HttpServletResponse response, @PathVariable String type, @PathVariable String image) throws Exception {
    service.getImage(response, image, type);
  }  
  
  @PostMapping("/addReport")
  public int addReport(ReportVO vo) {
    return service.addReport(vo);
  }
  

  @GetMapping("/showReportAll")
  public List<ReportVO> showReportAll(){
    return service.showReportList();
  }
  
  @GetMapping("/showProReview")
  public PageInfo<ReviewBoardVO> showProReview(int pageNum, int serviceType, int serviceId){
    String order = "";
    PageHelper.startPage(pageNum, 5, order);
    return PageInfo.of(service.showProReview(serviceType, serviceId));
  }


}
