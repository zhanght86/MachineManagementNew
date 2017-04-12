package MachineManagement.Controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.las.MachineManagement.Bean.Checkinfoa;

import MachineManagement.DataBaseHelper.BusinessHelper;

import org.json.*;

@Controller 
@Scope("prototype")
public class CheckInfoAController {

	
	//取得检查表A的信息
	@RequestMapping("checkinfoamain")
	public ModelAndView checkinfoamain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			int id=Integer.parseInt(request.getParameter("id"));
		  	  Calendar cal = Calendar.getInstance();
	    	  int year = cal.get(Calendar.YEAR);
	    	  
			Checkinfoa  checkinfoa=BusinessHelper.getCheckInfoA(id,year);
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (checkinfoa);
			
//			obj.put("id", checkInfoA.id);
//			obj.put("flowNumber", checkInfoA.flowNumber);
//			obj.put("serialNumber", checkInfoA.serialNumber);
//			obj.put("propertyNumber", checkInfoA.propertyNumber);
//			obj.put("responsibilityDepartment", checkInfoA.responsibilityDepartment);
//			obj.put("machineLocation", checkInfoA.machineLocation);
//			obj.put("model", checkInfoA.model);
//			obj.put("systemInfo", checkInfoA.systemInfo);
//			obj.put("ipAdd", checkInfoA.ipAdd);
//			obj.put("machineUsage", checkInfoA.machineUsage);
//			obj.put("mantainceStaff", checkInfoA.mantainceStaff);
//			obj.put("backupContent", checkInfoA.backupContent);
//			obj.put("backupContentChange1", checkInfoA.backupContentChange1);
//			obj.put("backupContentChange2", checkInfoA.backupContentChange2);
//			obj.put("fileDirectory", checkInfoA.fileDirectory);
//			obj.put("fileDirectoryChange1", checkInfoA.fileDirectoryChange1);
//			obj.put("fileDirectoryChange2", checkInfoA.fileDirectoryChange2);
//			obj.put("backupPeriod", checkInfoA.backupPeriod);
//			obj.put("backupPeriodChange1", checkInfoA.backupPeriodChange1);
//			obj.put("backupPeriodChange2", checkInfoA.backupPeriodChange2);
//			obj.put("year", checkInfoA.year);
//			obj.put("machineInfoID", checkInfoA.machineInfoID);
			
			mv.addObject("data",obj.toString());
			
			
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	//更新检查表A的信息
	@RequestMapping("updatecheckinfoamain")
	public ModelAndView updatecheckinfoamain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			mv=new ModelAndView("/common/data");
			
			Checkinfoa checkinfoa=new Checkinfoa(Integer.parseInt(request.getParameter("id")),request.getParameter("flowNumber"),
					   request.getParameter("propertyNumber"),request.getParameter("serialNumber"),
					   request.getParameter("responsibilityDepartment"),request.getParameter("machineLocation"),request.getParameter("model"),
					   request.getParameter("systemInfo"),request.getParameter("ipAdd"),request.getParameter("machineUsage"),request.getParameter("mantainceStaff"),
					   request.getParameter("backupContent"),request.getParameter("backupContentChange1"),request.getParameter("backupContentChange2"),request.getParameter("fileDirectory"),
					   request.getParameter("fileDirectoryChange1"),request.getParameter("fileDirectoryChange2"),request.getParameter("backupPeriod"),request.getParameter("backupPeriodChange1"),
					   request.getParameter("backupPeriodChange2"),Integer.parseInt(request.getParameter("year")),Integer.parseInt(request.getParameter("machineInfoID")));
			
	
       
			if(BusinessHelper.updateCheckInfoA(checkinfoa))
			{
				mv.addObject("data","检查表A基本信息更新成功");
			}
			else
			{
				mv.addObject("data","检查表A基本信息更新失败");
			}
			
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	
}
