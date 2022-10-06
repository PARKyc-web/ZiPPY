package com.yedam.zippy.used.service;

import java.util.Date;

import lombok.Data;

@Data
public class UsedProductVO {
	private int productNo;
	private String userEmail;
	private String productName;
	private String productCategory;
	private int productPrice;
	private String productInfo;
	private String productLocation;
	private int isSell;
	private String views;
	private Date productDate;
}
