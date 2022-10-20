package com.yedam.zippy.member.service;

import lombok.Data;

@Data
public class GeneralUserVO {
    private String email;
    private String userName;
    private String nickName;
    private String phoneNumber;    
    private String userBirth;
    private String userGender;
    private String userAddress;
    private String zipCode; 
    private Integer userState;
    private String profileImage;
    private String addressDetail;
}
