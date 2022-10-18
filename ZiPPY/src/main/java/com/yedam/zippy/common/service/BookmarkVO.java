package com.yedam.zippy.common.service;

import lombok.Data;

@Data
public class BookmarkVO {
  private String productName;
  private int productPrice;
  private String productLocation;
  private int bookmarkNo;
  private String email;
  private int serviceType;
  private String serviceId;
}
