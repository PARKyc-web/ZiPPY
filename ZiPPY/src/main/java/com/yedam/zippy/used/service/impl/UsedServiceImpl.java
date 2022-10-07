package com.yedam.zippy.used.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedProductVO;
import com.yedam.zippy.used.service.UsedService;

@Service
public class UsedServiceImpl implements UsedService {
	
	@Autowired
	UsedMapper mapper;

	@Override
	public List<UsedProductVO> usedList(String location, String keyword, String category, String checked) {
		return mapper.usedList(location,keyword,category,checked);
	}
	
	@Override
	public List<UsedProductVO> usedSearchList(String word){
	  return mapper.usedSearchList(word);
	}

	@Override
	public UsedProductVO usedOne(int pNo) {
		return mapper.usedOne(pNo);
	}

	@Override
	public void insertUsed(Map<String,String> insertProduct) {
		mapper.insertUsed(insertProduct);
	}

	@Override
	public void updateUsed(String value) {
		mapper.updateUsed(value);
	}

	@Override
	public void deleteUsed(String key) {
		mapper.deleteUsed(key);
	}

}
