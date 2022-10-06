package com.yedam.zippy.used.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@CrossOrigin
@RestController
@RequestMapping("/used")
public class UsedContoller {

  @Autowired
  UsedService service;

  // 전체조회
  @GetMapping("/main")
  public List<UsedProductVO> search(@RequestParam String location, @RequestParam String keyword) {

    System.out.println(location + " , " + keyword);

    return service.usedList(location, keyword);
  }

  // 검색조회
  @GetMapping("/search")
  public String usedSearch(@RequestParam String word) {
    System.out.println(word);
    System.out.println(service.usedSearchList(word));
    return "";
  }

  // 단건조회
  @GetMapping("/get")
  public String read(@RequestParam int pNo) {
    System.out.println(pNo);
    return "";
  }

  // 생성
  @PostMapping("/register")
  public String insert(@RequestBody Map<String, String> insertProduct) {
    System.out.println(insertProduct);
    return "";
  }

  // 수정
  @PutMapping("/update")
  public String update(@RequestParam String value) {
    System.out.println(value);
    return "";
  }

  // 삭제
  @DeleteMapping("/delete")
  public String delete(@RequestParam String key) {
    System.out.println(key);
    return "";
  }
}
