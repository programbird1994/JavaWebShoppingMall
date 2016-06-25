package com.huyang.controller.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.huyang.service.spring.RegisterService;
import com.huyang.service.spring.getAllShopsService;
@Controller
public class RegisterController {
	@Autowired
private RegisterService registerService;
	@Autowired
	private getAllShopsService getallShopsService;

//	public String resister(@RequestParam("userName") String name,@RequestParam("userPassword") String password) throws ClassNotFoundException, SQLException
//	{
//		User user=new User();
//		user.setUserPassword(password);
//		user.setUserName(name);
//		if(  registerService.register(user).equals("su"))
//		{
//
//			//return "redirect:/jsp/mainpage";
//		}
//		else if(registerService.register(user).equals("exist"))
//		{
//		//System.out.println("already exists");
//		return "/Login";
//		}
//		else {
//		//	System.out.println("failed");
//			return "/Login";
//		}
//		return "";
//	}
}
