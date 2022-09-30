package com.yedam.zippy.property.service;

import lombok.Data;

@Data
public class propertyVO {
	private int productId;
	private int roomCnt;
	private String saleType;
	private String price;
	private float areaExclusive;
	private String floor;
	private String mainImg;
	private String email;
	private int productState;
	private String detailContents;
	private String sigungu;
	private String houseName;
	
	private int detailId;
//	int productId;
	private int bathCnt;
	private int parking;
	private String houseFace;
	private int constructionYear;
	private String tags;
	
	
}
