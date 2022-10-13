package com.yedam.zippy.member.service;

import lombok.Data;

@Data
public class BusinessVO {
    private String email;
    private String compName;
    private String compIntro;
    private String ceoName;
    private String businessId;
    private String compAddress;
    private String profilePic;
    private String phone;
    private String brokerId;
    private String businessImg;
    private String brokerImg;
    private Integer memberType;
    private String impUid;
    private String zipCode;
    private Integer businessState;
    private String addressDetail;
}
