package com.MachineManagement.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Support.HASH;
import com.las.MachineManagement.Bean.Operationtype;
import com.las.MachineManagement.Bean.Userrole;
import com.las.MachineManagement.Bean.Users;
import com.las.MachineManagement.Bean.UsersUserroleR;
import com.springframework.orm.ManchineManagementDao;

@Controller 
@Scope("prototype")
public class UserController {
	

	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	@RequestMapping("User")
	public ModelAndView User(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			mv=new ModelAndView("user");
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("loadUser")
	public ModelAndView loadUser(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
	    	List<Users>  userList=manchineManagementDao.find("from Users where state=1 ");
			mv=new ModelAndView("usercontentlist");
			mv.addObject("UserList", userList);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("loadUserShortListNewMachine")
	public ModelAndView loadUserShortList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			
	    	List<Users>  userList=manchineManagementDao.find("from Users where state=1 ");
			mv=new ModelAndView("usercontentlist");
           
			mv=new ModelAndView("userShortListNewMachine");
			mv.addObject("UserList", userList);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	
	@RequestMapping("loadUserShortListMachineDetailInfo")
	public ModelAndView loadUserShortListMachineDetailInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			
	    	List<Users>  userList=manchineManagementDao.find("from Users where state=1 ");
			mv=new ModelAndView("usercontentlist");
           
			mv=new ModelAndView("userShortListMachineDetailInfo");
			mv.addObject("UserList", userList);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("loadUserShortListTransferRecord")
	public ModelAndView loadUserShortListTransferRecord(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
	    	List<Users>  userList=manchineManagementDao.find("from Users where state=1 ");
			mv=new ModelAndView("usercontentlist");
           
			mv=new ModelAndView("userShortListTransferRecord");
			mv.addObject("UserList", userList);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("getUserByUserid")
	public ModelAndView getUserByUserid(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int userid = Integer.parseInt(request.getParameter("userid"));
           Users user = new Users();
           
       		List<Users>  userList=manchineManagementDao.find("from Users where id=? and state=1 ",new Object[]{userid});
       		if(userList!=null&&userList.size()!=0)
       		{
       			user=userList.get(0);
       		}
       		
   			mv=new ModelAndView("usercontentlist");
   			mv=new ModelAndView("/common/data");
			
			JSONObject obj=new JSONObject (user);
 
			mv.addObject("data",obj.toString());

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		String returnValue="";
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int userid = Integer.parseInt(request.getParameter("userid"));
			String name=request.getParameter("name");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String department=request.getParameter("department");
			String contactnumber=request.getParameter("contactnumber");
			String updatepw=request.getParameter("updatepw");
			int roleid = Integer.parseInt(request.getParameter("roleid"));
			
			if(updatepw.equals("1"))
			{
				  password =(HASH.HashToHEX(password, "SHA-1"));
			}
			
			
	            Users user = new Users();
	       		List<Users>  userList=manchineManagementDao.find("from Users where id=? and state=1 ",new Object[]{userid});
	       		if(userList!=null&&userList.size()!=0)
	       		{
	       			user=userList.get(0);
	       		}
	       		user.setState("1");
	       		user.setUsername(username);
	       		user.setPassword(password);
	       		user.setEmail(email);
	       		user.setDepartment(department);
	       		user.setContactNumber(contactnumber);
	       		manchineManagementDao.saveOrUpdate(user);
           
	       		mv=new ModelAndView("/common/data");
	       		mv.addObject("data",returnValue);

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	
	
	@RequestMapping("addUser")
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
		  String name=request.getParameter("name");
           String username=request.getParameter("username");
           String password =request.getParameter("password");
           password =HASH.HashToHEX(password, "SHA-1");
           String email=request.getParameter("email");
           String department=request.getParameter("department");
           String contactnumber=request.getParameter("contactnumber");
           int userrole=Integer.parseInt(request.getParameter("userrole")) ;
           
           String result="";
           Users user=new Users();
           List<Users> userList=manchineManagementDao.find("from Users where email=?",new Object[]{email});
        	if(userList!=null&&userList.size()!=0)
        	{
        		result="用户("+email+")已经存在";
        	}
        	else
        	{
        		user.setName(name);
        		user.setPassword(password);
        		user.setUsername(username);
        		user.setEmail(email);
        		user.setDepartment(department);
        		user.setContactNumber(contactnumber);
        		user.setState("1");
        		manchineManagementDao.saveOrUpdate(user);
        		
        		UsersUserroleR usersUserroleR=new UsersUserroleR();
        		usersUserroleR.setUserid(user.getId());
        		usersUserroleR.setRoleid(1);
        		usersUserroleR.setUpdatetime(new Date());
        		manchineManagementDao.saveOrUpdate(usersUserroleR);
        		result="";
        	}
        		   
           
			mv=new ModelAndView("/common/data");
			mv.addObject("data",result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
           int userid= Integer.parseInt(request.getParameter("userid"));
 

          
           
           String result="";
           
           Users user=new Users();
           List<Users> userList=manchineManagementDao.find("from Users where id=?",new Object[]{userid});
        	if(userList!=null&&userList.size()!=0)
        	{
        		result="用户不存在";
        	}
        	else
        	{
        		user=userList.get(0);
        		user.setState("2");
        		manchineManagementDao.saveOrUpdate(user);
        		result="";
        	}
           
			mv=new ModelAndView("/common/data");
			mv.addObject("data",result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}

}
