package com.huyang.controller.spring;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huyang.model.spring.shop;

@Controller
public class ShopDetail {
@RequestMapping(value="jsp/shopDetail")
public String  show(@RequestParam String id,Map<String, String> model)
{
	model.put("shop", id);
	return "shopDetail";
	}
}
