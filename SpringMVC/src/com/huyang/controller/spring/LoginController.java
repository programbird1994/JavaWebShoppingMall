package com.huyang.controller.spring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyang.service.spring.LoginService;
import com.huyang.service.spring.RegisterService;

@Controller
public class LoginController {
	@Autowired
	private RegisterService registerService;

  private  LoginService loginService;

	@RequestMapping({"/jsp/Login"})
	public String toLoginpage()
	{
		return "Login";
	}
	@RequestMapping({"/jsp/admin/Login"})
	public String toLoginpage1(Map<String, Object> model)
	{
		System.out.println("into admin login controller");

		return "admin/Login";
	}
	@Autowired
	public LoginController(LoginService loginService)
	{
		this.setLoginService(loginService);
	}
	private LoginService getLoginService() {
		return loginService;
	}
	private void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("jsp/checkUserNameUsed")
	public @ResponseBody String checkName(@RequestParam("username") String userName)
	{
		return registerService.checkUserNameUsed(userName) ;
	}

	@RequestMapping("jsp/checkEmailUsed")
	public @ResponseBody String checkEmail(@RequestParam("email") String email)
	{
		return registerService.checkEmailUsed(email) ;
	}

	@RequestMapping("jsp/register1")
	public @ResponseBody String register(@RequestParam("email") String email,@RequestParam("username") String userName,@RequestParam("password") String password)
	{
		return registerService.register(userName,email,password) ;
	}



}
