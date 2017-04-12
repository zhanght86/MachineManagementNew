package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import MachineManagement.DataBaseHelper.BusinessHelper;
import MachineManagement.DataModel.*;
import Support.HASH;

@Controller 
@Scope("prototype")
public class UserController {
	
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
			
           List<User> UserList = new ArrayList<User>();
           
           UserList= BusinessHelper.getUserList();
           
			mv=new ModelAndView("usercontentlist");
			mv.addObject("UserList", UserList);
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
			
           List<User> UserList = new ArrayList<User>();
           
           UserList= BusinessHelper.getUserList();
           
			mv=new ModelAndView("userShortListNewMachine");
			mv.addObject("UserList", UserList);
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
			
           List<User> UserList = new ArrayList<User>();
           
           UserList= BusinessHelper.getUserList();
           
			mv=new ModelAndView("userShortListMachineDetailInfo");
			mv.addObject("UserList", UserList);
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
			
           List<User> UserList = new ArrayList<User>();
           
           UserList= BusinessHelper.getUserList();
           
			mv=new ModelAndView("userShortListTransferRecord");
			mv.addObject("UserList", UserList);
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
           User user = new User();
           
           user= BusinessHelper.getUserByUserId(userid);
           
           mv=new ModelAndView("/common/data");
			
			JSONObject obj=new JSONObject ();

			obj.put("id", user.id);
			obj.put("name", user.name);
			obj.put("username", user.username);
			obj.put("password", user.password);
			obj.put("email", user.email);
			obj.put("department", user.department);
			obj.put("contactnumber", user.contactNumber);
			obj.put("userRole", user.userRole);
			
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
			
			
           User user = new User(userid,1,name,"30","男",username,password,email,"0",department,contactnumber,"1");
           user.setUserRole(roleid);
           
           returnValue= BusinessHelper.updateUser(user);
           
           mv=new ModelAndView("/common/data");

			mv.addObject("data",returnValue);

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
           result= BusinessHelper.AddUser(name,username,password,userrole,email,department,contactnumber);
           
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
           //String username =request.getParameter("username");
           //String email =request.getParameter("email");

          
           
           String result="";
           result= BusinessHelper.DeleteUser(userid,"","");
           
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
