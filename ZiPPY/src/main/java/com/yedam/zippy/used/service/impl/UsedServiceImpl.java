package com.yedam.zippy.used.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.used.mapper.UsedMapper;
import com.yedam.zippy.used.service.UsedService;
import com.yedam.zippy.used.service.UsedProductVO;

@Service
public class UsedServiceImpl implements UsedService {
	
	@Autowired
	UsedMapper mapper;

	@Override
	public List<UsedProductVO> usedList(String location, String keyword) {
		return mapper.usedList(location,keyword);
	}

	@Override
	public UsedProductVO usedOne(UsedProductVO usedVO) {
		return mapper.usedOne(usedVO);
	}

	@Override
	public void insertUsed(UsedProductVO usedVO) {
		mapper.insertUsed(usedVO);
	}

	@Override
	public void updateUsed(UsedProductVO usedVO) {
		mapper.updateUsed(usedVO);
	}

	@Override
	public void deleteUsed(UsedProductVO usedVO) {
		mapper.deleteUsed(usedVO);
	}

}
