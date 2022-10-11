package com.yedam.zippy.common.service;

import lombok.Data;

@Data
public class BookmarkVO {
  private int bookmark_no;
  private String user_email;
  private int service_type;
  private String service_id;
}
