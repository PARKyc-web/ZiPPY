package com.yedam.zippy.move.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MoveController {

	@GetMapping("/main")
    public String moveMain() { 
      // => url에 /move/main으로 입력하여 화면으로 이동한다.   
      return "move/main";   //move폴더/moveSelect(jsp)페이지로 간다.   
    }

	@GetMapping("/moveSelect")
	public String moveSelect() {
		
		return "move/moveSelect";
	}
	
	@GetMapping("/moveDateOld")
	public String moveDateOld() {
		return "move/moveDateOld";
	}
	
	@GetMapping("/moveInfo")
	public String moveInfo() {
		return "move/moveInfo";
	}
}
