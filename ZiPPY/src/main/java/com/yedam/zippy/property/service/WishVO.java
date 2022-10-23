package com.yedam.zippy.property.service;

import lombok.Data;

@Data
public class WishVO {
  private int bookmarkNo;
  private String email;
  private int serviceType;
  private int serviceId;
  
  private int productId;
  // 공인중개사 e-mail
//  private String email;
  private String houseName;
  private int roomCnt;
  private String saleType;
  private String price;
  private float areaExclusive;
  private String floor;
  private String mainImg;
  private int productState;
  private String detailContents;
  private String sigungu;
  private String houseType;
}
