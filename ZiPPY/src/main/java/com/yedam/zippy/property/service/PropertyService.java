package com.yedam.zippy.property.service;

import java.util.List;

public interface PropertyService {
	public List<propertyVO> getAllPropertyList();
	
	public String getAgentEmail(int productId);
	
	public List<propertyVO> gethouseDetail(int productId);
	
	public List<propertyVO> getAgentProperties(String email);
	
	public List<agentVO> getAgentProfile(String businessEmail);
	
	public String getCompName(String businessEmail);
}
