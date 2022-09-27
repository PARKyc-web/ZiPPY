package com.yedam.zippy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.zippy.runt.mapper.runtMapper;
import com.yedam.zippy.runt.service.runtVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class runt_test {
	
	@Autowired
	runtMapper mapper;

	@Test
	public void runt_test() {
		int data = mapper.getSumInteger();
		runtVO vo = mapper.getEmployeeData();
		System.out.println("TEST=============");
		System.out.println(data);
		System.out.println(vo);
		System.out.println("=================");
	}
	
}
