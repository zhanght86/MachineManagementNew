package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.las.MachineManagement.Bean.Functions;
import com.las.MachineManagement.Bean.UserroleFunctionsR;
import com.las.MachineManagement.Bean.UsersUserroleR;
import com.springframework.orm.ManchineManagementDao;

import MachineManagement.Common.CommonDataHelper;
 

@Controller 
@Scope("prototype")
public class FunctionController {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	@Autowired(required=true) 
	private CommonDataHelper commonDataHelper;
	
	@RequestMapping("loadFunctions")
	public ModelAndView loadFunctions(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{						
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

     
           int userid=Integer.parseInt( request.getParameter("userid"));
           List<UsersUserroleR> usersUserroleRList=manchineManagementDao.find("from UsersUserroleR where userid=?",new Object[]{userid});
           int roleid= usersUserroleRList.get(0).getRoleid();
           List<UserroleFunctionsR> userroleFunctionsRList=manchineManagementDao.find("from UserroleFunctionsR where userroleid=?",new Object[]{roleid});
           String functionidString="";
           for(UserroleFunctionsR userroleFunctionsR:userroleFunctionsRList)
           {
        	   functionidString+=userroleFunctionsR.getFunctionid()+",";
           }
           
           if(functionidString.endsWith(","))
           {
        	   functionidString=functionidString.substring(0, functionidString.length()-1);
           }
           
           List<Functions> functionsList =manchineManagementDao.find("from Functions where id in ( "+functionidString+" ) order by displayorder asc" );
           
           
			httpSession.setAttribute("isadmin", commonDataHelper.isadmin(userid));
			mv=new ModelAndView("functionlist");
			mv.addObject("FunctionList", functionsList);
			mv.addObject("isadmin", httpSession.getAttribute("isadmin"));
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
}
