package com.yedam.zippy.used.web;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public List<UsedProductVO> search(@RequestParam String location, @RequestParam String keyword,
      @RequestParam String category, @RequestParam String checked,
      @RequestParam(value = "dropbox", required = false) String dropbox) {

    System.out.println(location + ", " + keyword);
    if (category.equals("전체")) {
      category = "";
    }
    System.out.println(dropbox);

    if (checked.equals("true")) {
      checked = "1";
    } else if (checked.equals("false")) {
      checked = "0";
    }

    if (dropbox.equals("최저가순")) {
      dropbox = "0";
    } else if (dropbox.equals("최고가순")) {
      dropbox = "1";
    } else if (dropbox.equals("최신등록일자순")) {
      dropbox = "2";
    } else if (dropbox.equals("오래된등록일자순")) {
      dropbox = "3";
    }

//    if (keyword.equals("")) {
//      keyword = "";
//    }
    return service.usedList(location, keyword, category, checked, dropbox);
  }

//  // 단건조회
  @GetMapping("/detail")
  public UsedProductVO read(@RequestParam int pNo) {
    System.out.println(pNo);
    service.viewCnt(pNo);
    return service.usedOne(pNo);
  }

  // 생성
  @PostMapping("/insert")
  public String insert(@RequestBody Map<String, String> insertProduct) {
    System.out.println(insertProduct);
    return "";
  }

  // 수정
  @PutMapping("/update")
  public String update(@RequestParam String img, @RequestParam String name, @RequestParam String price,
      @RequestParam String category, @RequestParam String content, @RequestParam int pNo) {
    System.out.println(img+","+name+","+price+","+category+","+content+","+pNo);
    System.out.print(service.updateUsed(img, name, price, category, content, pNo));
    return "";
  }

  // 삭제
  @DeleteMapping("/delete")
  public String delete(@RequestParam int pNo) {
    System.out.println(pNo);
    System.out.println(service.deleteUsed(pNo));
    return "";
  }

  // 찜 추가
  @PostMapping("/idk")
  public String addWish(@RequestParam String id) {
    return service.addWish(id);
  }

}
