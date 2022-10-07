package com.yedam.zippy.member.service;

import java.util.Date;

import lombok.Data;

@Data
public class LoginVO {
    private String email;
    private String password;
    private Integer isSocial;
    private String token;
    private Integer memberType;
    private Date memberDate;  
}
