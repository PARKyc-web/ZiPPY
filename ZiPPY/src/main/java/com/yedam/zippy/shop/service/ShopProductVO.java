package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class ShopProductVO {
	private int shopProductNo;
	private String businessEmail;
	private String shopProductName;
	private String shopCategory;
	private String shopMainImg;
	private String shopProductOption;
	private String shopProductInfo;
	private String shopDeliveryCost;
}
