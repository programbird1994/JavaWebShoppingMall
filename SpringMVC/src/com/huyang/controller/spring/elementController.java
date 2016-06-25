package com.huyang.controller.spring;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.huyang.model.spring.shop;
import com.huyang.service.spring.getAllShopsService;
@org.springframework.stereotype.Controller
public class elementController implements Controller {
	@RequestMapping(value="/jsp/elements")
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		//@in
		ModelAndView mv=new ModelAndView();
		mv.setViewName("elements");
		return mv;
	}
}
