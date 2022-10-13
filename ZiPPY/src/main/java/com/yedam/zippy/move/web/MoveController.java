package com.yedam.zippy.move.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.move.service.MoveRequestVO;
import com.yedam.zippy.move.service.MoveService;

@CrossOrigin(originPatterns = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
    RequestMethod.DELETE })
@RestController
@RequestMapping("/move")
public class MoveController {

  @Autowired
  MoveService service;

  @PostMapping("/moveUntactCheck")
  public String moveUntactCheck(MoveRequestVO moveReq) {
    System.out.println(moveReq);
    
    
//  System.out.println(service.moveUntactCheck(moveRequ));
    return "";
  }

}
