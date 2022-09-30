package com.yedam.zippy.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.zippy.property.mapper.PropertyMapper;
import com.yedam.zippy.property.service.PropertyService;
import com.yedam.zippy.property.service.propertyVO;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	PropertyMapper mapper;
	
	@Override
	public List<propertyVO> getAllPropertyList() {
		return mapper.getAllPropertyList();
	}
	
}
