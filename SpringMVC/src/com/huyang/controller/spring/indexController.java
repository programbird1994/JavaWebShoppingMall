package com.huyang.controller.spring;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huyang.model.spring.Category;
import com.huyang.model.spring.ProductDetail;
import com.huyang.service.spring.CartService;
import com.huyang.service.spring.CategoryService;
import com.huyang.service.spring.LoginService;
import com.huyang.service.spring.ProductService;
import com.huyang.service.spring.getAllShopsService;

@Controller
public class indexController  {
	//@Autowired
	//private users
	@Autowired(required=true)
	private CategoryService categoryService;
    @Autowired(required=true)
	private getAllShopsService getallshopService;
    @Autowired
    private CartService cartService;
    @Autowired
	LoginService loginService;
    @Autowired
    ProductService productService;
	@RequestMapping(value="/jsp/index")
	public String handleRequest(HttpServletRequest req,
			HttpServletResponse res,@RequestParam(required=false,value="username") String username,@RequestParam(value="Userpassword",required=false) String password,Map<String, Object> model,RedirectAttributes reAttr) throws Exception {

		if(username!=null&&password!=null)
		{
			System.out.println("index session id:"+req.getSession().getId());
			//model.put("userName", username);

			if(loginService.validate(username, password,req))
			{
				req.getSession().setAttribute("userName", username);
	              model.put("best_sell_active", productService.getTenBestSellProducts().subList(0, 4));
		          model.put("best_sell", productService.getTenBestSellProducts().subList(4, 10));
		          model.put("last_sell", productService.getTenLastSellProducts().subList(4, 10));
		          model.put("last_sell_active", productService.getTenLastSellProducts().subList(0, 4));
			            return "index";
			}
			else
			{
			//	System.out.println("failed");
				return "Login";
			}



		}


		 model.put("best_sell_active", productService.getTenBestSellProducts().subList(0, 4));
         model.put("best_sell", productService.getTenBestSellProducts().subList(4, 10));
         model.put("last_sell", productService.getTenLastSellProducts().subList(4, 10));
         model.put("last_sell_active", productService.getTenLastSellProducts().subList(0, 4));
		return "index";
	}

	@RequestMapping("jsp/validate")
	public @ResponseBody String validate(@RequestParam(required=false,value="username") String username,@RequestParam(value="Userpassword",required=false) String password,HttpServletRequest req)
	{
		if(loginService.validate(username, password, req)==true)
		{
			return "exist";
		}
		return "not exist";
	}

	@RequestMapping("jsp/admin/index")
	public String show(Map<String, Object>model, @RequestParam(value="adminName") String name,@RequestParam(value="adminPassword") String password,HttpServletRequest request)
	{
		System.out.println("into admin index controller");
		System.out.println("validate:  "+loginService.validateAdmin(name, password));
		if(!loginService.validateAdmin(name, password))
		{
			return "admin/Login";
		}
		int shopID=loginService.getShopIDByAdminname(name);
		System.out.println("id:   --"+shopID);
		List<ProductDetail>  list=productService.getProducts(shopID);
//		for (ProductDetail productDetail : list) {
//			System.out.println(productDetail.getProductName());
//		}
		model.put("adminName", name);
		model.put("products",list);
		model.put("shopID", shopID);
		request.getSession().setAttribute("shopID", shopID);
		return "admin/index";
	}

	@RequestMapping("jsp/top")
	public String show21(Map<String, Object> model,HttpServletRequest request,@RequestParam(value="username",required=false) String userName)
	{
		System.out.println("top session id"+request.getSession().getId());
		//System.out.println("2");
		//System.out.println(request.getSession());
		List<Category> categories=categoryService.getAllCategories();
		for (Category category : categories) {
			category.setSubCategories(categoryService.getcategoriedByID(category.getCategoryID()));
		}

		model.put("categories", categories);
        model.put("userName", request.getSession().getAttribute("userName"));
		return "top";
		}
}
