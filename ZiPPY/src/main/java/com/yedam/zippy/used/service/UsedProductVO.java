package com.yedam.zippy.used.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UsedProductVO {
	private int productNo;
	private String email;
	private String productName;
	private String productCategory;
	private int productPrice;
	private String productInfo;
	private String productLocation;
	private int isSell;
	private String views;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date productDate;
}
