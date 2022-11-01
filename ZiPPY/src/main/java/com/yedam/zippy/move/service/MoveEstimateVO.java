package com.yedam.zippy.move.service;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MoveEstimateVO {
  private String businessEmail;
  private String dropbox;
  private String dropbox2;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private String requestDate;
  private String departaddress;
  private String arriveAddress;
  private String compAddress;
  private String email;
  private String reservStatus;
  private int pageNum;
}
