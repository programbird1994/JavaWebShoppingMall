package com.huyang.controller.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huyang.service.spring.ProductService;

@Controller
public class ProductsController {

	@Autowired
	private ProductService productService;
	@RequestMapping(value="/jsp/products**")
	public String toProducts(@RequestParam(value="category",required=false) Integer category,Map<String, Object> modelMap,HttpServletRequest request)
  {

		String keyword=request.getParameter("searchinput");
		if(category==null)
		{
			boolean pricehigher=true;
		modelMap.put("products", productService.getProductsByKeyword(keyword, pricehigher));
		modelMap.put("keyword", keyword);
		}
		else {
			modelMap.put("categoryID", category);
			modelMap.put("products", productService.getProductsByCategoryID(category));
		}
		//System.out.println("into product controller");
	  return "products";
  }
}
