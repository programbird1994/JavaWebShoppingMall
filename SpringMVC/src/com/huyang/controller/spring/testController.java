package com.huyang.controller.spring;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Utils.Page;

import com.huyang.model.spring.ProductDetail;
import com.huyang.service.spring.AdminService;
import com.huyang.service.spring.ProductService;

@Controller
public class testController {
	  @Autowired
	 ProductService productService;
		@Autowired
		private	AdminService adminService;
	private  Page  pageHelper=new Page();
   @RequestMapping("/jsp/admin/product_list")
   public String toLogin(Map<String, Object> model,HttpServletRequest request,@RequestParam(value="page",required=false) Integer page)
   {
	   System.out.println("into list controller");
	   int shopID=(Integer)request.getSession().getAttribute("shopID");
	   List<ProductDetail>  list=productService.getProducts(shopID );
	   pageHelper.setPageSize(20);
         pageHelper.setTotalCount(adminService.getProductsByshopID((Integer)request.getSession().getAttribute("shopID")).toArray().length);
         if(page==null)
         {
        	 page=1;
         }
         pageHelper.setPageNumber(page);
			pageHelper.setStartRow((page-1)*pageHelper.getPageSize());
         model.put("products",adminService.getProductsByshopID(shopID, page, 20));
         model.put("page", pageHelper);
        request.setAttribute("totalPage", pageHelper.getTotalPage());
	    
	   return "admin/product_list";
   }
  
}
