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
import com.yedam.zippy.validation.service.ValidationService;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@CrossOrigin
@RestController
@RequestMapping("/validation")
public class ValidationController {
	
		
	@Autowired
	MemberService mService;
	
	@Autowired
	ValidationService validationService;
	
	
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
		String key = "";
		for(int i=0; i<6; i++) {
			int rand = (int) (Math.random()*10);
			key += rand;
		}		
		
		String mailContent = "회원가입을 위한 인증번호 입니다"
                           + "\n 아래의 인증번호를 입력하여 인증을 완료해주세요 "
                           + "\n" + key;
		validationService.sendEmail(data.get("email"), "ZIPPY 회원가입!", mailContent);
		
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
		String key = "";
		for(int i=0; i<6; i++) {
			int rand = (int) (Math.random()*10); // 0 ~ 9
			key += rand;
		}		

		String smsContent = "[ZiPPY] 인증번호는 " + key + "입니다";
		validationService.sendSMS(data.get("phone"), smsContent);
		
		return key;
	}	
	
	
	@GetMapping("/emailRedundancy")
	public int emailRedundancy(@RequestParam String email) {  	  
	  return mService.emailRedundancy(email); 	  
	}		
	
	@GetMapping("/password")
    public boolean password(@RequestParam String email) {           
        Object obj = mService.findUserByEmail(email);        
        if(obj == null) {
          return false;
        }       
        
        String key = validationService.getNewPassword();
        LoginVO login = new LoginVO();
        login.setEmail(email);
        
        login.setPassword(key);
        mService.takeNewPassword(login);
                
        String mailContent = "[ZIPPY] 임시비밀번호 발급 \n "
                           + key + " 입니다";        
        validationService.sendEmail(email, "ZIPPY 임시비밀번호!", mailContent);
        
        return true;
    }
	
	@GetMapping("/findEmail")
	public String findUserEmail(String userName, String phoneNumber) {	  
	  String userEmail = "";
	  userEmail = mService.findUserEmail(userName, phoneNumber);  
	  
	  if(userEmail == null) {
	    return "";
	  }
	
	  return userEmail;
	}
	
}
