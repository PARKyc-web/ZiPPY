package com.yedam.zippy.common.service;

import java.sql.Date;

import lombok.Data;

@Data
public class ReportVO {
  private int reportNo;
  private String email;
  private String reportType;
  private String reportContents;
  private Date reportDate;
  private int serviceType;
  private String serviceId;
  private int reportState;
}