package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class ProductVO {
	private int shopProductNo;
	private String businessEmail;
	private String shopProductName;
	private String shopProductPrice;
	private String shopCategory;
	private String shopMainImg;
	private String shopProductInfo;
	private String shopDeliveryCost;
	private String shopProductStatus;
}
