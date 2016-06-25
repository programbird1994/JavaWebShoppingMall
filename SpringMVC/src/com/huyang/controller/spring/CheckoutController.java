package com.huyang.controller.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {
@RequestMapping(value="/jsp/checkout")
     public String tocheckout()
    {
	       return "checkout";
	}
}
