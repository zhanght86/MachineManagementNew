package MachineManagement.Controller;
import java.util.Calendar;

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

import com.las.MachineManagement.Bean.Checkinfob;

import MachineManagement.DataBaseHelper.BusinessHelper;

@Controller 
@Scope("prototype")
public class CheckInfoBController {
	
	//取得检查表B的信息
	@RequestMapping("checkinfobmain")
	public ModelAndView checkinfobmain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			 int id=Integer.parseInt(request.getParameter("id").toString().trim());
		  	  Calendar cal = Calendar.getInstance();
	    	  int year = cal.get(Calendar.YEAR);
	    	  
			Checkinfob  checkInfoB=BusinessHelper.getCheckInfoB(id,year);
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject ();
			
			obj.put("id", checkInfoB.id);
			obj.put("flowNumber", checkInfoB.flowNumber);
			obj.put("propertyNumber", checkInfoB.propertyNumber);
			obj.put("serialNumber", checkInfoB.serialNumber);
			obj.put("responsibilityDepartment", checkInfoB.responsibilityDepartment);
			obj.put("machineLocation", checkInfoB.machineLocation);
			obj.put("model", checkInfoB.model);
			obj.put("systemInfo", checkInfoB.systemInfo);
			obj.put("ipAdd", checkInfoB.ipAdd);
			obj.put("machineUsage", checkInfoB.machineUsage);
			obj.put("mantainceStaff", checkInfoB.mantainceStaff);
			obj.put("year", checkInfoB.year);
			obj.put("machineInfoID", checkInfoB.machineInfoID);
			obj.put("comments", checkInfoB.comments);
			
			mv.addObject("data",obj.toString());
			
			
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	//更新检查表B的信息
	@RequestMapping("updatecheckinfobmain")
	public ModelAndView updatecheckinfobmain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			mv=new ModelAndView("/common/data");
			
			Checkinfob checkInfoB=new Checkinfob(Integer.parseInt(request.getParameter("id")),request.getParameter("flowNumber"),
					   request.getParameter("propertyNumber"),request.getParameter("serialNumber"),
					   request.getParameter("responsibilityDepartment"),request.getParameter("machineLocation"),request.getParameter("model"),
					   request.getParameter("systemInfo"),request.getParameter("ipAdd"),request.getParameter("machineUsage"),request.getParameter("mantainceStaff"),
					   Integer.parseInt(request.getParameter("year")),Integer.parseInt(request.getParameter("machineInfoID")),request.getParameter("comments"));
			

			if(BusinessHelper.updateCheckInfoB(checkInfoB))
			{
				mv.addObject("data","检查表B基本信息更新成功");
			}
			else
			{
				mv.addObject("data","检查表B基本信息更新失败");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	
}
