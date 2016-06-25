package com.huyang.controller.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Utils.Page;

import com.huyang.model.spring.Address;
import com.huyang.model.spring.OrderProduct;
import com.huyang.service.spring.AddressService;
import com.huyang.service.spring.CartService;
import com.huyang.service.spring.OrderService;
import com.huyang.service.spring.PayService;
import com.huyang.service.spring.ProductService;
@Controller
public class CartController {
	@Autowired
	PayService payService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
	@RequestMapping(value="/jsp/cart")
	public String show(HttpServletRequest request,Map<String, Object> model,@RequestParam(value="page",required=false) Integer page)
	       {
		System.out.println("cart session: "+request.getSession().getId());
		Integer userID=(Integer) request.getSession().getAttribute("userID");
		System.out.println("userID:"+request.getSession().getAttribute("userID"));
		if(userID!=null)
		{
	     Page pageHelper=new Page();
		 pageHelper.setPageSize(5);
		 pageHelper.setTotalCount(cartService.getCartByUserID(userID).toArray().length);
		    if(page==null)
		      page=1;

            pageHelper.setPageNumber(page);
			pageHelper.setStartRow((page-1)*pageHelper.getPageSize());
			//System.out.println("into cart jsp"+page);
        // model.put("carts", cartService.getProductsByUserID(userID,page,5));
			model.put("carts", cartService.getCartByUserID(userID, page, 5));
		model.put("allcart", cartService.getCartByUserID(userID));
		model.put("pagehelper",pageHelper);
		//System.out.println("111");
		return "cart";
		}
		return "Login";



	         }



	@RequestMapping("/jsp/addcart")
	public @ResponseBody int returnSuccess(@RequestParam(value="productID",required=false)Integer productID,@RequestParam(value="userID",required=false) Integer userID,@RequestParam(value="count",required=false) Integer arrangecount)
	{
		//System.out.println("productid:"+productID+" user "+userID+"  count"+arrangecount);
		cartService.updateCart(productID, userID, arrangecount);
		return 1;
	}

	@RequestMapping("jsp/deleteCart")
	public @ResponseBody int returnSuc(@RequestParam("productIDs") String productIDs,HttpServletRequest request)
	{
		System.out.println("into delete cart");
		List<Integer> list=new ArrayList<Integer>();
		for (String productID : productIDs.split(",")) {
			list.add(Integer.parseInt(productID));
		}
		int userID=(int) request.getSession().getAttribute("userID");
		cartService.removeCart(list, userID);
		return 1;

	}

	@RequestMapping("jsp/ceshi")
	public String v(HttpServletRequest request,Map<String, Object> model,@RequestParam("ids") String ids,@RequestParam("counts") String counts)
	{
		Integer userID=(Integer) request.getSession().getAttribute("userID");
	//	System.out.println("uderID:"+userID);
		List<Address> list=addressService.getAddressListByUserID(userID);
         model.put("addresses", list);
         List<Integer> idList=new ArrayList<Integer>();
         List<OrderProduct> list1=new ArrayList<OrderProduct>();
         int a=0;
        for (String s : ids.split(",")) {

			int i=Integer.parseInt(s);
			//idList.add(i);
			OrderProduct orderProduct=new OrderProduct();
			int count=Integer.parseInt(counts.split(",")[a++]);
			orderProduct.setProductDetail(productService.getProductsByProductID(i));
			orderProduct.setCount(count);
			list1.add(orderProduct);

		}
         model.put("products", list1);
         model.put("ids", ids);
         model.put("counts", counts);
      //   System.out.println("list size"+list.size());
         for (Address address : list) {
			System.out.println("a:"+address.getAddressID());
		}
		return "ceshi";
	}

	@RequestMapping("jsp/saveorder")
	public @ResponseBody String save(@RequestParam("ids") String ids,@RequestParam("counts") String counts,HttpServletRequest request,@RequestParam("consignee") String consignee,@RequestParam("street") String street,@RequestParam("tel") String tel,@RequestParam("region") String region)
	{
		System.out.println("consignee:"+consignee);
		System.out.println("street:"+street);
		System.out.println("tel:"+tel);
		System.out.println("region:"+region);
		Address address=orderService.makeAddress(consignee, tel, street, region);
		int addressID=addressService.saveAddress(address,(Integer)request.getSession().getAttribute("userID"));
		int userID=(int) request.getSession().getAttribute("userID");
		//System.out.println("saveorder userID");
                String orderID= orderService.saveOrder(userID, ids, counts,addressID);

                	 List<Integer> list=new ArrayList<Integer>();
             		for (String productID : ids.split(",")) {
             			list.add(Integer.parseInt(productID));
             		}
             		cartService.removeCart(list, userID);
             		productService.updateStoreCount( orderID);


                 return orderID;
	}
	@RequestMapping("jsp/pay")
	public String a(@RequestParam("orderID") String orderID,Map<String, Object>model,@RequestParam("consignee") String consignee,@RequestParam("street") String street,@RequestParam("tel") String tel,@RequestParam("region") String region,@RequestParam("totalprice") double totalprice)
	{
		model.put("orderID", orderID);
		model.put("tel", tel);
		model.put("region", region);
		model.put("consignee", consignee);
		model.put("street", street);
		model.put("price", totalprice);
		 return "pay";
	}

	@RequestMapping("jsp/pay1")
	public String aa(@RequestParam("orderID") String orderID,Map<String, Object>model,@RequestParam(value="productID",required=false) Integer productID)
  {
		model.put("orderID", orderID);
		if(productID!=null)
		{
			System.out.println("sas"+productID);
		model.put("productID", productID);
		}
		 return "pay1";
	}


	@RequestMapping("jsp/topay")
	public @ResponseBody String aa1(@RequestParam("orderID") String orderID,HttpServletRequest request,@RequestParam(value="productID",required=false) Integer productID)
	{
	//	System.out.println("service orderID:"+orderID);
		int userID=(int) request.getSession().getAttribute("userID");
		if(productID==null)
		{
//System.out.println("kkk");
         return payService.userPay(userID, orderID);
		}
		else {
			System.out.println("test");
			return payService.userPay(userID, orderID, productID);
		}
	}

	@RequestMapping("jsp/myorder")
	public  String aa11(HttpServletRequest request,Map<String, Object> model,@RequestParam(value="page",required=false) Integer page)
	{

        if(request.getSession().getAttribute("userID")==null)
        {
        	 return "Login";
        }

		if(page==null)
		{

			page=1;


		}
		int userID=(int) request.getSession().getAttribute("userID");
		 Page pageHelper=new Page();
		 pageHelper.setPageSize(5);
		 pageHelper.setTotalCount(orderService.getAllOrdersByUserIDCount(userID));
		//page=1;
		pageHelper.setPageNumber(page);
		pageHelper.setStartRow((page-1)*pageHelper.getPageSize());

		model.put("orders",orderService.getAllOrdersByUserID(userID,page,5) );
		model.put("pagehelper", pageHelper);

		 return "myorder";
	}

	@RequestMapping("jsp/confirmRec")
	public @ResponseBody String asd(@RequestParam("orderID") String orderID,@RequestParam("productID") Integer productID,@RequestParam("status") String status)
	{
		System.out.println("lll");
		productService.updateSellCount(productID,orderID);
		 return orderService.updateOrder(orderID, productID, status);
	}

	@RequestMapping("jsp/goComment")
	public  String asd1(@RequestParam("orderID") String orderID,@RequestParam("productID") Integer productID,Map<String, Object> model)
	{
		 model.put("orderID", orderID);
	     model.put("productID",productID);
		 return "comment";
	}

	@RequestMapping("jsp/doComment")
	public  @ResponseBody String asd121(@RequestParam("commentcontent") String commentcontent,@RequestParam("orderID") String orderID,@RequestParam("productID") Integer productID,Map<String, Object> model,HttpServletRequest request,@RequestParam("rank") double rank)
	{
		int userID=(int) request.getSession().getAttribute("userID");
		orderService.updateOrder(orderID, productID, "ÒÑÆÀÂÛ");
  return orderService.addComment(productID,commentcontent,userID,rank);
	}

	@RequestMapping("jsp/checkPassword")

	public @ResponseBody String sfdsd(@RequestParam("password") String password,HttpServletRequest re)
	{
		int userID=(int) re.getSession().getAttribute("userID");
		return payService.checkpassword(userID, password);
	}

}
