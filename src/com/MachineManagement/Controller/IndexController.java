package com.MachineManagement.Controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
@Scope("prototype")
public class IndexController {
	
	@RequestMapping("index")
	public ModelAndView index(){
		//创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	
	
	
}
