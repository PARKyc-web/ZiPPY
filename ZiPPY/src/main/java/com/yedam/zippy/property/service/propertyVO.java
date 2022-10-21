package com.yedam.zippy.property.service;

import lombok.Data;

@Data
public class propertyVO {
	private int productId;
	private String email;
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
	
	private int detailId;
//	int productId;
	private int bathCnt;
	private int parking;
	private String houseFace;
	private int constructionYear;
	private String tags;
	private String streetAddress;
	
	private String compName;
	
	//
	private int minSize;
	private int maxSize;
	private int minPrice;
	private int maxPrice;
}
