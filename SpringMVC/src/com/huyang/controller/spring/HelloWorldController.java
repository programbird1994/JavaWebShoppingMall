package com.huyang.controller.spring;
import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller
public class HelloWorldController implements Controller {

	@RequestMapping("/jsp/hello")
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       //1���ռ���������֤����
       //2���󶨲������������
       //3�������������ҵ��������ҵ����
       //4��ѡ����һ��ҳ��
       ModelAndView mv = new ModelAndView();
       //���ģ������ �����������POJO����
       mv.addObject("message", "Hello World!");
       //�����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
       mv.setViewName("hello");
       System.out.println("have enter int ocontroller");
       return mv;
    }
}
