package com.MachineManagement.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MachineManagement.Common.CommonDataHelper;
import com.MachineManagement.Component.MachineMonthlyCheck;


/**
 * @author chen zijun
 *
 */


@Controller 
@Scope("prototype")
public class CommonController {
	
	
	@Autowired(required=true) 
	private MachineMonthlyCheck machineMonthlyCheck;
	
	
	
	@RequestMapping("mongthlycheck")
	public String mongthlycheck(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)
	{
		String result="";
		try
		{
			machineMonthlyCheck.checkByUserId();
			System.out.println("完成");
			result="ok";
			response.setContentType("type=text/html;charset=UTF-8"); 
			response.getWriter().write(result); 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			result=ex.toString();
		}
 
		return result;
	}

}





 
