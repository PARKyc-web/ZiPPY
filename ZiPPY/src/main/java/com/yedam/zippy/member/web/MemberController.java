package com.yedam.zippy.member.web;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {
	
	
	@GetMapping("gSignUp")
	public String businessLoginForm(@RequestParam Map<String, String> userInfo) {
		System.out.println("General-Sign-Up RUN!");
		for(String x : userInfo.keySet()) {
			System.out.println("Key : " + x + " \\ " + "Value : " + userInfo.get(x));
		}			

		return "good";
	}

//	   @PostMapping("gSignUp")
//	    public String businessLoginPost(@RequestParam Map<String, String> userInfo, Model model) {
//	        System.out.println("General-Sign-Up RUN!");
//	        for(String x : userInfo.keySet()) {
//	            System.out.println("Key : " + x + " \\ " + "Value : " + userInfo.get(x));
//	        }           
//	        
//	        return "good";
//	    }
	   
	   @PostMapping("gSignUp")
       public String businessLoginPPost(@RequestBody Map<String, String> userInfo) {
           System.out.println("General-Sign-Up RUN!");
           for(String x : userInfo.keySet()) {
               System.out.println("Key : " + x + " \\ " + "Value : " + userInfo.get(x));
           }           
//          service 실행 => db 넣는 부분
           
           return "good";
       }
	
}
