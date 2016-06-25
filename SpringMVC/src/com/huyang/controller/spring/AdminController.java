package com.huyang.controller.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import Utils.Page;

import com.huyang.model.spring.OrderDetail;
import com.huyang.model.spring.ProductDetail;
import com.huyang.service.spring.AdminService;
import com.huyang.service.spring.CategoryService;
import com.huyang.service.spring.OrderService;
import com.huyang.service.spring.ProductService;

@Controller
public class AdminController {
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
private	AdminService adminService;
	@Autowired
	private CategoryService categoryService;

   @RequestMapping("/jsp/admin/delete")
   public String Delete(HttpServletRequest request)
   {
	   System.out.println("into delete controller");
	 String ids=  request.getParameter("allIDCheck");

	 if(request.getParameter("productID")!=null)
	 {
		 Integer  id1=Integer.parseInt(request.getParameter("productID"));
		 int shopID=(int) request.getSession().getAttribute("shopID");
			adminService.delete(id1);
			 return "redirect:/jsp/admin/product_list";
	 }
	   String []idarray=ids.split(",");
	   for (String id : idarray) {
		   int shopID=(int) request.getSession().getAttribute("shopID");
		adminService.delete(Integer.parseInt(id));
	}
	   return "redirect:/jsp/admin/product_list";
   }

   @RequestMapping("jsp/admin/checkProductExist")
   public  @ResponseBody String checkProductExist(HttpServletRequest request,HttpServletResponse response ) throws IOException
   {
	   System.out.println("data:"+request.getParameter("brand"));
	   String brand=request.getParameter("brand");
	   String productName=request.getParameter("productName");
	  if(adminService.checkProductExist(brand,productName, (Integer)request.getSession().getAttribute("shopID")))
	   return "not exist";

	   else {
		   return "exist";
	}
	  // return "exist";
   }
   @RequestMapping("jsp/admin/upload")
   public String show()
   {
	   System.out.println("upload111");
	   return "admin/upload";
   }

   @RequestMapping("jsp/admin/uploadfile")
   public @ResponseBody String show1(@RequestParam MultipartFile multipartFile)
   {
	   System.out.println("uploadfile");
	   return "SUCCESS";
   }

   @RequestMapping("jsp/admin/saveOrUpdateProduct")
   public String save(HttpServletRequest request,@RequestParam("image") MultipartFile file,@RequestParam("filename") String filename) throws IOException
   {
	   ProductDetail productDetail;
	   System.out.println("into save");
	   Integer productID = null;
	   if(request.getParameter("pid").length()!=0)
	   {
		   System.out.println("pdi "+request.getParameter("pid").length());
	    productID=Integer.parseInt(request.getParameter("pid"));
	   }
	   if(productID!=null)
	   {
		   System.out.println("test productID:"+productID);
	    productDetail=productService.getProductsByProductID(productID);
	   }
	   else {
		 productDetail=new ProductDetail();
	}
	   productDetail.setDescription(request.getParameter("description"));
	   productDetail.setBrand(request.getParameter("brand"));
	   productDetail.setStoreCount(Integer.parseInt(request.getParameter("count")));
	   productDetail.setKeyword(request.getParameter("keyword"));
	   double price=  Double.parseDouble(request.getParameter("price"));
	   productDetail.setPrice(price);
	   productDetail.setProductName(request.getParameter("productName"));
	  // productDetail.setSellCount(Integer.parseInt(request.getParameter("count")));
	   productDetail.setCategoryID(Integer.parseInt(request.getParameter("cate")));
	 //  System.out.println("categoryID:  "+productDetail.getCategoryID());
	   int shopID=(int) request.getSession().getAttribute("shopID");
	   productDetail.setShopID( shopID);
	   String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/upload")+productDetail.getCategoryID()+"/"+productDetail.getBrand();
	   String eclipsepath="F:\\JavaCode\\SpringMVC\\WebContent\\WEB-INF\\jsp\\upload"+"/"+productDetail.getCategoryID()+"/"+productDetail.getBrand();
	   System.out.println("current user dir:"+eclipsepath);
	   File demo=new File(realPath);
	   if(!demo.exists())
	   {
		   System.out.println("´´½¨"+realPath);
		   demo.mkdirs();
	   }
	   File demo1=new File(eclipsepath);
	   if(!demo1.exists())
	   {
		   demo1.mkdirs();
	   }
	   if(file.getSize()!=0)
	   {
		   System.out.println("file is not null"+file.getSize());
	   String extensionString=file.getContentType().substring(file.getContentType().indexOf("/")+1);
	   FileCopyUtils.copy(file.getBytes(), new File(realPath+"/"+filename+"."+extensionString));
	   FileCopyUtils.copy(file.getBytes(), new File(eclipsepath+"/"+filename+"."+extensionString));
	   productDetail.setImage("upload/"+productDetail.getCategoryID()+"/"+productDetail.getBrand()+"/"+filename+"."+extensionString);
	   }
	   else {
		   System.out.println(" file is null");

	}
	   adminService.save(productDetail);
	   return "admin/index";
   }

   @RequestMapping("jsp/admin/scanPic**")
   public String scan(Map<String, Object> model,HttpServletRequest request,@RequestParam("productID") int productID)
   {
	 //  int productID=Integer.parseInt(request.getParameter("productID"));
	   model.put("pics", adminService.getImgByProductID(productID));
	   return "admin/scanPic";
   }

   @RequestMapping("/jsp/admin/product_edit")
   public String to(Map<String, Object> model,@RequestParam(value="productID",required=false) Integer productID)
   {
	   model.put("categories", categoryService.getSubcategories());
	   if(productID!=null)
	   {
	   model.put("product",productService.getProductsByProductID(productID) );
	   }
	   return "admin/product_edit";
   }

   @RequestMapping("jsp/admin/order_list")
   public String asd(Map<String, Object> model,@RequestParam(value="page",required=false) Integer page,HttpServletRequest request)
   {
	   int pagesize=5;
	   if(page==null)
		   page=1;
   List<OrderDetail> orders=orderService.getAllOrders(page,pagesize);
   Page  pageHelper=new Page();
   pageHelper.setPageNumber(page);
   pageHelper.setPageSize(pagesize);
   pageHelper.setStartRow((page-1)*pagesize);
   pageHelper.setTotalCount(orderService.getAllOrders());
   model.put("orders", orders);
   model.put("page", pageHelper);
   request.setAttribute("totalPage", pageHelper.getTotalPage());
	   return "admin/order_list";
   }

   @RequestMapping("jsp/admin/updateOrder")
   public @ResponseBody String updateOrder(@RequestParam("orderID") String orderID,@RequestParam("productID") Integer productID,@RequestParam("status") String status)
   {
	   System.out.println("l");
	 return orderService.updateOrder(orderID, productID, status);
   }
}
