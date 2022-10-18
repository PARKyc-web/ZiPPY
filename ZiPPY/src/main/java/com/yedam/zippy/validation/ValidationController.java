package com.yedam.zippy.validation;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.zippy.member.service.LoginVO;
import com.yedam.zippy.member.service.MemberService;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@CrossOrigin
@RestController
@RequestMapping("/validation")
public class ValidationController {
	
	@Autowired
	MailSender mailSender;
		
	@Autowired
	MemberService mService;
	
	private final DefaultMessageService smsService = NurigoApp.INSTANCE.initialize("", "", "https://api.coolsms.co.kr");
	
	/**
	 * 이메일 인증할때 사용할 메소드
	 * 이메일 주소를 Get 방식으로 넘겨주면
	 * 인증코드(6자리)를 이메일 발송하고
	 * 요청한 곳에 인증번호(6자리)를 Return 해줌
	 *  
	 * @param data(이메일 주소) email : ""
	 * @return int(인증번호)
	 */		
	@GetMapping("/email")
	public String emailValidation(@RequestParam Map<String, String> data) {
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
		
//		msg.setTo(data.get("email"));
//		msg.setSubject("mail이 잘 가는지 확인해보자!");		
//		msg.setText("회원가입을 위한 인증번호 입니다"
//				  + "\n 아래의 인증번호를 입력하여 인증을 완료해주세요 "
//				  + "\n" + key);
//		msg.setFrom("erty1201@naver.com");
//		mailSender.send(msg);
		
		return key;
	}	
	
	/**
	 * 핸드폰 인증할때 사용하는 메소드
	 * 핸드폰 번호를 하이픈(-) 없이 전송해주면,
	 * 핸드폰으로 인증번호(6자리)를 문자로 보내고,
	 * 인증번호(6자리)를 Return 해준다. 	
	 * 
	 * @param data(핸드폰 번호) phone : ""
	 * @return int(인증번호)
	 */
	@GetMapping("/phone")
	public String phoneValidation(@RequestParam Map<String, String> data) {		
		Message sms = new Message();				
		String key = "";
		for(int i=0; i<6; i++) {
			int rand = (int) (Math.random()*10); // 0 ~ 9
			key += rand;
		}		
		
		System.out.println("Key set >> " + data.keySet());
		for(String x : data.keySet()) {
			System.out.println("ajax Key {"+x+"} : " + data.get(x));
		}				
		System.out.println("받는 번호 : " + data.get("phone"));
		System.out.println("메세지 받았다고 칩시다");
		System.out.println("Key 값 : " + key);
//		sms.setFrom("01054068096");
//		sms.setTo("01076197555");
//		sms.setText("[ZiPPY] 인증번호는 " + key + "입니다");
//		
//		SingleMessageSentResponse res = smsService.sendOne(new SingleMessageSendingRequest(sms));
//		System.out.println(res);
		
		return key;
	}	
	
	@GetMapping("/emailRedundancy")
	public int emailRedundancy(@RequestParam String email) {
	  System.out.println("emailRedundancy");
	  
	  System.out.println(email);
	  int result = mService.emailRedundancy(email);
	  System.out.println("Redundancy Check :: " + result);	  
	  
	  return result; 	  
	}
	
	
	@GetMapping("/password")
    public boolean password(@RequestParam String email) {            
        SimpleMailMessage msg = new SimpleMailMessage();        
        Random rand = new Random();
       
        Object obj = mService.findUserByEmail(email);        
        if(obj == null) {
          return false;
        }
        
        String key = "";              
        for(int i=0; i<5; i++) {
           key += (char)(rand.nextInt(26)+65);
        }
        
        key += "@";
        key += rand.nextInt(100);
        for(int i=0; i<5; i++) {
          key += (char)(rand.nextInt(26)+97);
       }
        
        LoginVO login = new LoginVO();
        login.setEmail(email);
        login.setPassword(key);
        mService.changePassword(login);
        
//      msg.setTo(data.get("email"));
//      msg.setSubject("mail이 잘 가는지 확인해보자!");       
//      msg.setText("회원가입을 위한 인증번호 입니다"
//                + "\n 아래의 인증번호를 입력하여 인증을 완료해주세요 "
//                + "\n" + key);
//      msg.setFrom("erty1201@naver.com");
//      mailSender.send(msg);
        
        return true;
    }
	
	@GetMapping("/findEmail")
	public String findUserEmail(String userName, String phoneNumber) {	  
	  
	  System.out.println(userName);
	  System.out.println(phoneNumber);
	  
	  String userEmail = "";
	  userEmail = mService.findUserEmail(userName, phoneNumber);
	  
	  System.out.println(userEmail);
	  if(userEmail == null) {
	    return "";
	  }
	
	  return userEmail;
	}
	
}
