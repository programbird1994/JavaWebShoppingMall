package com.huyang.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
@org.springframework.stereotype.Controller
public class genericController implements Controller{

	@Override
	@RequestMapping(value="/jsp/generic")
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("generic");
		return mv;
	}
	
}
