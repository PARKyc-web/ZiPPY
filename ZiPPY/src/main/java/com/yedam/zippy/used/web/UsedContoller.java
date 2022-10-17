package com.yedam.zippy.used.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.used.service.UsedImagesVO;
import com.yedam.zippy.used.service.UsedKeywordVO;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
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
  public String insert(UsedProductVO product, List<MultipartFile> images) {
    System.out.println(product);
    System.out.println(images);
    product.setProductLocation("대구");
    service.insertUsedProduct(product, images);
    return "";
  }

  // 수정
  @PostMapping("/update")
  public int update(UsedProductVO product, List<MultipartFile> images) {
    System.out.println(product);
    System.out.println(images);
    product.setProductLocation("대구");
    service.deleteImg(product);
    service.updateUsedProduct(product, images);
    return 1;
  }

  // 삭제
  @DeleteMapping("/delete")
  public String delete(@RequestParam int pNo) {
    System.out.println(pNo);
    System.out.println(service.deleteUsed(pNo));
    return "";
  }

  // 키워드 추가
  @PostMapping("/addKeyword")
  public String addKeyword(@RequestBody UsedKeywordVO keyword) {
    System.out.println(keyword);
    System.out.println(service.addKeyword(keyword));
    return "";
  }

  // 키워드 삭제
  @DeleteMapping("/delKeyword")
  public String delKeyword(@RequestParam int kNo) {
    System.out.println(kNo);
    System.out.println(service.delKeyword(kNo));
    return "";
  }

  // 키워드 전체출력
  @GetMapping("/keyword")
  public List<UsedKeywordVO> showKeyword(@RequestParam String email) {
    System.out.println(email);
    System.out.println(service.showKeyword(email));
    return service.showKeyword(email);
  }
  
  @GetMapping("getImg")
  public List<UsedImagesVO> getImg(@RequestParam int pNo){
    System.out.println(pNo);
    System.out.println(service.getImg(pNo));
    return service.getImg(pNo);
  }
}
