package com.yedam.zippy.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.shop.mapper.ShopMapper;
import com.yedam.zippy.shop.service.ProductVO;
import com.yedam.zippy.shop.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	ShopMapper mapper;
	
	@Override
	public List<ProductVO> getProductList() {
		return mapper.getProductList();
	}

	@Override
	public ProductVO getProduct(ProductVO productVO) {
		return mapper.getProduct(productVO);
	}

	@Override
	public void insertProduct(ProductVO productVO) {
		mapper.insertProduct(productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		mapper.updateProduct(productVO);
	}

	@Override
	public void deleteProduct(ProductVO productVO) {
		mapper.deleteProduct(productVO);
	}

}
