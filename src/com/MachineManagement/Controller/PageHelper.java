package com.MachineManagement.Controller;

import java.util.HashMap;
import java.util.Map;

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
public class PageHelper {
	@RequestMapping("main")
	public ModelAndView mainJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			Map<String, Object> map= new HashMap<String, Object>();
			map.put("userid", httpSession.getAttribute("userid"));
			map.put("loginname",  httpSession.getAttribute("loginname"));
			map.put("displayname", httpSession.getAttribute("displayname"));
			 mv=new ModelAndView("main",map);
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}
	
	
	@RequestMapping("header")
	public ModelAndView headerJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			Map<String, Object> map= new HashMap<String, Object>();
			map.put("userid", httpSession.getAttribute("userid"));
			map.put("loginname",  httpSession.getAttribute("loginname"));
			map.put("displayname", httpSession.getAttribute("displayname"));
			 mv=new ModelAndView("common/header",map);
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}

	@RequestMapping("menu")
	public ModelAndView menuJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			Map<String, Object> map= new HashMap<String, Object>();
			map.put("userid", httpSession.getAttribute("userid"));
			map.put("loginname",  httpSession.getAttribute("loginname"));
			map.put("displayname", httpSession.getAttribute("displayname"));
			 mv=new ModelAndView("common/menu",map);
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}
	
	@RequestMapping("content")
	public ModelAndView contentJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			 mv=new ModelAndView("content");
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}
	
	
	
	@RequestMapping("newmachine")
	public ModelAndView newmachineJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			Map<String, Object> map= new HashMap<String, Object>();
			map.put("userid", httpSession.getAttribute("userid"));

			 mv=new ModelAndView("newmachine",map);
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}
	
	
	@RequestMapping("machineDetails")
	public ModelAndView machineDetailsJumper(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			Map<String, String> map= new HashMap<String, String>();
			map.put("userid", httpSession.getAttribute("userid").toString());
			 mv=new ModelAndView("machineDetails",map);
		}
		catch(Exception ex)
		{
		    ex.printStackTrace();
			mv=new ModelAndView("common/error");
		}

		return mv;
	}
	


}
