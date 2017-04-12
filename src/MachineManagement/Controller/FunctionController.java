package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import MachineManagement.DataBaseHelper.BusinessHelper;
import MachineManagement.DataModel.*;

@Controller 
@Scope("prototype")
public class FunctionController {
	
	@RequestMapping("loadFunctions")
	public ModelAndView loadFunctions(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{						
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

           List<Function> FunctionList = new ArrayList<Function>();
           int userid=Integer.parseInt( request.getParameter("userid"));
           FunctionList= BusinessHelper.getFunctionList(userid);
			httpSession.setAttribute("isadmin", BusinessHelper.isadmin(userid));
			mv=new ModelAndView("functionlist");
			mv.addObject("FunctionList", FunctionList);
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
