package com.huyang.controller.spring;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyang.model.spring.shop;
import com.huyang.service.spring.getAllShopsService;
@Controller
public class mainpageController {
private	getAllShopsService service;
@Autowired
	public mainpageController(getAllShopsService s)
	{
		this.service=s;
	}
  @RequestMapping(value="/jsp/mainpage")
  public String showMainpage(Map<String, Collection<shop>> model) throws ClassNotFoundException, SQLException
  {
	  model.put("shops",(Collection<shop>)service.getShopList() );
	//  System.out.println(service.getShopList().getClass());
	  return "mainpage";
  }
public getAllShopsService getService() {
	return service;
}
public void setService(getAllShopsService service) {
	this.service = service;
}
}
