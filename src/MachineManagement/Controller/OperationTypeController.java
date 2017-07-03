package MachineManagement.Controller;


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

import com.las.MachineManagement.Bean.Operationtype;
import com.springframework.orm.ManchineManagementDao;


import org.json.*;

@Controller 
@Scope("prototype")
public class OperationTypeController {

	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	//取得检查表A的信息
	@RequestMapping("OperationType")
	public ModelAndView checkinfoamain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
	    	  
	    	List<Operationtype>  operationTypeList=manchineManagementDao.find("from Operationtype");
			
			mv=new ModelAndView("/OperationType");
			mv.addObject("operationTypeList",operationTypeList);
			
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString()); 
		}
		return mv;
	}
//	
//	//更新检查表A的信息
//	@RequestMapping("updatecheckinfoamain")
//	public ModelAndView updatecheckinfoamain(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
//		ModelAndView mv=new ModelAndView();
//		try
//		{
//			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
//			{
//				 return new ModelAndView(new RedirectView(""));
//			}
//			mv=new ModelAndView("/common/data");
//			
//			CheckInfoA checkInfoA=new CheckInfoA(Integer.parseInt(request.getParameter("id")),request.getParameter("flowNumber"),
//					   request.getParameter("propertyNumber"),request.getParameter("serialNumber"),
//					   request.getParameter("responsibilityDepartment"),request.getParameter("machineLocation"),request.getParameter("model"),
//					   request.getParameter("systemInfo"),request.getParameter("ipAdd"),request.getParameter("machineUsage"),request.getParameter("mantainceStaff"),
//					   request.getParameter("backupContent"),request.getParameter("backupContentChange1"),request.getParameter("backupContentChange2"),request.getParameter("fileDirectory"),
//					   request.getParameter("fileDirectoryChange1"),request.getParameter("fileDirectoryChange2"),request.getParameter("backupPeriod"),request.getParameter("backupPeriodChange1"),
//					   request.getParameter("backupPeriodChange2"),Integer.parseInt(request.getParameter("year")),Integer.parseInt(request.getParameter("machineInfoID")));
//			
//	
//       
//			if(BusinessHelper.updateCheckInfoA(checkInfoA))
//			{
//				mv.addObject("data","检查表A基本信息更新成功");
//			}
//			else
//			{
//				mv.addObject("data","检查表A基本信息更新失败");
//			}
//			
//		}
//		catch(Exception ex)
//		{
//			mv=new ModelAndView("/common/error");
//			mv.addObject("data",ex.toString());
//		}
//		return mv;
//	}
	
	
}
