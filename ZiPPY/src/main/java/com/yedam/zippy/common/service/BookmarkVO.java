package com.yedam.zippy.common.service;

import lombok.Data;

@Data
public class BookmarkVO {
  private int bookmarkNo;
  private String email;
  private int serviceType;
  private String serviceId;
}
