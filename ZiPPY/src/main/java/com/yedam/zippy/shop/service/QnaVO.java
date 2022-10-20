package com.yedam.zippy.shop.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class QnaVO {
  private int qnaNo;
  private String email;
  private int proNo;
  private String question;
  private String answer;
  private int answerStatus;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  private Date questionDate;
  @JsonFormat(pattern = "yy-MM-dd")
  @DateTimeFormat(pattern="yy-MM-dd")
  private Date answerDate;
  private String questionCate;
}
