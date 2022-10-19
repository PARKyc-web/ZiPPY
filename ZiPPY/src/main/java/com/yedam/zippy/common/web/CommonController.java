package com.yedam.zippy.common.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
  public BookmarkVO getWishOne(@RequestParam int sId, @RequestParam String email) {
   return service.getWishOne(sId, email);
  }
  
  // 찜 전체출력
  @GetMapping("/wishAll")
  public List<BookmarkVO> getWishAll(@RequestParam String email){
    return service.getWishAll(email);
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
  
  @GetMapping("image/{imagename}")
  @ResponseBody
  public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
      System.out.println("test");
      InputStream imageStream = new FileInputStream("C:/dev/image/" + imagename);
//    InputStream imageStream = new FileInputStream("/home/ubuntu/images/feed/" + imagename);
      byte[] imageByteArray = IOUtils.toByteArray(imageStream);
      imageStream.close();
      return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
  }
}
