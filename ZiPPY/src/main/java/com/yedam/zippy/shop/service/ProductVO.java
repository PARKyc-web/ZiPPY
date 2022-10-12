package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class ProductVO {
//	private int shopProductNo;
//	private String email;
//	private String shopProductName;
//	private String shopProductPrice;
//	private String shopCategory;
//	private String shopMainImg;
//	private String shopProductInfo;
//	private String shopDeliveryCost;
//	private String shopProductStatus;
	
	private int proNo;
    private String email;
    private String proName;
    private String ProPrice;
    private String category;
    private String proMainImg;
    private String proInfo;
    private String deliveryCost;
    private String proStatus;
	
	//business
	private String compName;
}
