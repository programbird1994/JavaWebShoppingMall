package com.huyang.controller.spring;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.huyang.service.spring.ProductService;

@Controller
public class ProductDetailController {
	@Autowired
	ProductService productService;
@RequestMapping(value="/jsp/product_detail")
public String show(@RequestParam(value="productID",required=false) Integer productID,Map<String, Object> model,HttpServletRequest request)
       {
	System.out.println("又进来了");
	//model.put("user",request.getSession().getAttribute("user") );
	if(productID!=null)
	{
	model.put("product", productService.getProductsByProductID(productID));
	model.put("comments", productService.getCommentsByProductID(productID));
	model.put("storeCount", productService.getStoreCount(productID));
	model.put("averank", productService.getAveRankByProductID(productID));
	}
	return "product_detail";

         }
}
