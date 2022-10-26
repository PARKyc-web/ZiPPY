package com.yedam.zippy.member.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.zippy.common.service.CommonService;
import com.yedam.zippy.member.service.BusinessVO;
import com.yedam.zippy.member.service.GeneralUserVO;
import com.yedam.zippy.member.service.LoginVO;
import com.yedam.zippy.member.service.MemberService;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService service;
  
    @PostMapping("/login")
    public Object login(@RequestBody LoginVO login) {
      System.out.println(login);
      return service.login(login);
    }
  
    @PostMapping("/gSignUp")
    public String generalSignUp(LoginVO login, GeneralUserVO gVO) {
      service.signGeneralMember(login, gVO);
      
      return "Congratulations";
    }

    @PostMapping("/bSignUp")
    public String businessSignUp(LoginVO login, BusinessVO bVO, List<MultipartFile> images) {  
      System.out.println(login);
      System.out.println(bVO);
      System.out.println(images);
      
      service.signBusinessMember(login, bVO, images);      
  
      return "Congratulations";
    }
    
    @GetMapping("/mypage")
    public Object getUserInfo(String email, int memberType) {
      return service.getMember(email, memberType);
    }
    
    /**
     * 비밀번호를 변경하는 메소드
     * @param login
     * @return 
     */
    @PutMapping("/password")
    public boolean changePassword(@RequestBody Map<String, String> data) {
      System.out.println(data);

      LoginVO login = new LoginVO();
      login.setEmail(data.get("email"));
      login.setPassword(data.get("password"));
      
      return service.changePassword(login, data.get("newPassword"));
    }
    
    @PostMapping("userInfo")
    public GeneralUserVO updateUserInfo(GeneralUserVO vo) {
      System.out.println(vo);      
      service.updateUserInfo(vo);
      return (GeneralUserVO) service.getMember(vo.getEmail(), 0);
    }
    
    @PostMapping("businessInfo")
    public BusinessVO updateBusinessInfo(BusinessVO vo) {
      System.out.println(vo);
      service.updateBusinessInfo(vo);
      return (BusinessVO) service.getMember(vo.getEmail(), 1);
    }
    
    @PostMapping("image")
    public String changeProfileImage(MultipartFile image, String email) {          
      return service.changeProfileImage(image, email);             
    }

}
