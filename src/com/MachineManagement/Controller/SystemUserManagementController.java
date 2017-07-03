package com.MachineManagement.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller 
@Scope("prototype")
public class SystemUserManagementController {

	

	@RequestMapping("systemUserManagement")
	public ModelAndView checkinfoamain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			mv=new ModelAndView("systemUserManagement");
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
}
