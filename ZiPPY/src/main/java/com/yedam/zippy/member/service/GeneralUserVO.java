package com.yedam.zippy.member.service;

import java.util.Date;

import lombok.Data;

@Data
public class GeneralUserVO {
    private String userEmail;
    private String userName;
    private String nickName;
    private String phoneNumber;
    private Date userBirth;
    private String gender;
    private String userAddress;
    private String zipCode;
    private Integer userState;
    private String addressDetail;
    private String profileImage;
}
