package com.yedam.zippy;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
		
	@Autowired
	MailSender mailSender;	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {		
		return "main/index";
	}
	
	@GetMapping("/home")
	public String home() {
//		jsp에서 ContextPath를 얻기 위해서 사용한다.
//		jsp에서는 주석이 안되서 여기에 적어둠 > 지워도 전혀 상관없음
//		<p><%=request.getContextPath()%></p>
//		
		return "home";
	}
	
	@GetMapping("/signup")
	public String login() {
		return "loginOut/memberSignUp";
	}
	
	@GetMapping("/mail")
	public String mailSender(Model model) {			
		System.out.println("===========Mail Controller RUN");
		SimpleMailMessage msg = new SimpleMailMessage();	
		
		String key = "";
		
		for(int i=0; i<6; i++) {
			int rand = (int) (Math.random()*10); // 0 ~ 9
			key += rand;
		}
		
		System.out.println(key);
		
		msg.setTo("erty1201@naver.com");
		msg.setSubject("mail이 잘 가는지 확인해보자!");		
		msg.setText("이메일 인증 번호 : " + key);
		msg.setFrom("erty1201@naver.com");
		mailSender.send(msg);
		
		model.addAttribute("key", key);
		
		return "main/mailSender";
	}
	
}
