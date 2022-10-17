package com.yedam.zippy.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.common.service.BookmarkVO;
import com.yedam.zippy.common.service.CommonService;

@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@RequestMapping("/common")
public class CommonController {

  @Autowired
  CommonService service;

  // 찜 추가
  @PostMapping("/addWish")
  public String addWish(@RequestBody BookmarkVO bookmark) {
    System.out.println(bookmark);
    service.addWish(bookmark);
    return "";
  }

  // 후기작성
  @PostMapping("/addRv")
  public String addReview() {
    return "";
  }

  // 후기 출력
  @GetMapping("/showRv")
  public String showReview() {
    return "";
  }

}
