package com.yedam.zippy.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.shop.mapper.ShopProductMapper;
import com.yedam.zippy.shop.service.ShopProductVO;
import com.yedam.zippy.shop.service.ShopService;

@Service
public class ShopProductServiceImpl implements ShopService{
	
	@Autowired
	ShopProductMapper mapper;
	
	@Override
	public List<ShopProductVO> getProductList() {
		return mapper.getProductList();
	}

	@Override
	public ShopProductVO getProduct() {
		return mapper.getProduct();
	}

	@Override
	public int insertShopProduct() {
		return mapper.insertShopProduct();
	}

	@Override
	public int updateShopProduct() {
		return mapper.updateShopProduct();
	}

	@Override
	public int deletShopProduct() {
		return mapper.deletShopProduct();
	}
}
