package com.yedam.zippy.shop.service;

import lombok.Data;

@Data
public class ProductOptionVO {
  int shopProductOptNo;
  int shopProductNo;
  String shopProductOptColor;
  String shopProductOptSize;
  //추가가격
  String shopProductOptPrice;
}
