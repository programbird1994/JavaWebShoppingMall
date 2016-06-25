package com.huyang.controller.spring;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huyang.model.spring.Comment;
import com.huyang.service.spring.CartService;
import com.huyang.service.spring.CategoryService;
import com.huyang.service.spring.CommentService;
import com.huyang.service.spring.ProductService;

@Controller
public class ajaxController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CommentService commentService;

	@Autowired
	CartService cartService;
@RequestMapping(value="/jsp/111")

public String show(HttpServletRequest request) throws IOException
{
	//String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
	//FileCopyUtils.copy(file.getBytes(), new File(realPath+"/"+"test1"));
	return "111";
	}


@RequestMapping(value="/jsp/upload")

public String show1(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException
{
	String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/upload");

	FileCopyUtils.copy(file.getBytes(), new File(realPath+"/"+file.getOriginalFilename()));
	System.out.println("upload ok: "+realPath+""+file.getOriginalFilename());
	return "111";
	}

@RequestMapping("jsp/admin/introduce")
public String show2()
{
	return "admin/introduce";
	}



@RequestMapping("jsp/pagination")
public String show211()
{
	return "pagination";
	}

@RequestMapping("jsp/ajax")
public String show21111()
{
	return "ajax";
	}

@RequestMapping("jsp/productList")
public String show211112()
{
	return "productList";
	}

@RequestMapping("/jsp/infoBuy/pageCount")
public  @ResponseBody int pageCount(@RequestParam(value="productID",required=false)Integer productID){
	return commentService.getCommentCountByProductID(productID);
}

@RequestMapping("/jsp/infoBuy/pageCount1")
public  @ResponseBody int pageCount1(@RequestParam(value="categoryID",required=false)Integer categoryID,@RequestParam(value="keyword",required=false)String keyword){
	if(keyword=="")
	return categoryService.getCountBycategoryID(categoryID);
	else {
		return productService.getproductsByKeywordcount(keyword, " ");
	}
}


@RequestMapping(value="jsp/infoBuy/listPage",method = RequestMethod.GET)
public String listPage(Map<String, Object> model,@RequestParam(value="productID",required=false)Integer productID,@RequestParam(value="pageIndex",required=false)Integer index,@RequestParam(value="pageSize",required=false) Integer pageSize){
	System.out.println("into list controller");
	List<Comment> comments=commentService.getCommentsByproductID(index, pageSize, productID);
	System.out.println("index,size,productID:  "+index+","+pageSize+","+productID);
	for (Comment comment : comments) {
		System.out.println(comment.getCommentContent());
	}
	model.put("comments", commentService.getCommentsByproductID(index, pageSize, productID));
	 return "list";


}


@RequestMapping(value="jsp/infoBuy/listPage1",method = RequestMethod.GET)
public String listPage1(Map<String, Object> model,@RequestParam(value="categoryID",required=false)Integer categoryID,@RequestParam(value="pageIndex",required=false)Integer index,@RequestParam(value="pageSize",required=false) Integer pageSize,@RequestParam(value="keyword",required=false)String keyword)
{
	//System.out.println("listpage1");
      if(keyword=="")
      {
    	 // System.out.println("keyword is null");
	model.put("products", productService.getProductsByCategoryID(index, pageSize, categoryID));
      }
      else {
    	  System.out.println("key:"+keyword);
		model.put("products", productService.getProductsByKeyword(keyword, true,index, pageSize));
		System.out.println("size:"+productService.getProductsByKeyword(keyword, true,index, pageSize).size());
	}

	 return "productList";


}

}
