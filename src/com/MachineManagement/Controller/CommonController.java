package com.MachineManagement.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
	public String mongthlycheck(){
		String result="";
		try
		{
			machineMonthlyCheck.checkByUserId();
			
			result="ok";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			result=ex.toString();
		}
 
		return result;
	}

}





 
