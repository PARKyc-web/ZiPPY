package com.yedam.zippy.validation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/validation")
public class ValidationController {
	
	@Autowired
	MailSender mailSender;

	@GetMapping("/email")
	public int emailValidation(@RequestParam Map<String, String> data) {
		System.out.println("===========Mail Validation RUN");				
		SimpleMailMessage msg = new SimpleMailMessage();	
		
		String key = "";
		for(int i=0; i<6; i++) {
			int rand = (int) (Math.random()*10); // 0 ~ 9
			key += rand;
		}		

		System.out.println("Key set >> " + data.keySet());
		for(String x : data.keySet()) {
			System.out.println("ajax Key {"+x+"} : " + data.get(x));
		}		
		
		msg.setTo(data.get("email"));
		msg.setSubject("mail이 잘 가는지 확인해보자!");		
		msg.setText("회원가입을 위한 인증번호 입니다"
				  + "\n 아래의 인증번호를 입력하여 인증을 완료해주세요 "
				  + "\n" + key);
		msg.setFrom("erty1201@naver.com");
		mailSender.send(msg);
		
		return Integer.parseInt(key);
	}
	
}
