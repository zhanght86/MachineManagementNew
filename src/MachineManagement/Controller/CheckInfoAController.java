package MachineManagement.Controller;

import java.util.Calendar;
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

import com.las.MachineManagement.Bean.Checkinfoa;
import com.las.MachineManagement.Bean.Machineinfo;
import com.springframework.orm.ManchineManagementDao;


import org.json.*;

@Controller 
@Scope("prototype")
public class CheckInfoAController {

	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
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
	    	  
	    	  Machineinfo machineinfo=new Machineinfo();
				List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{id});
				if(machineinfoList!=null&&machineinfoList.size()!=0)
				{
					machineinfo=machineinfoList.get(0);
				}
 
			
			Checkinfoa checkinfoa=new Checkinfoa();
			List<Checkinfoa> checkinfoaList= manchineManagementDao.find("from Checkinfoa where machineinfo.id=? and year=?",new Object[]{id,String.valueOf(year)});
			if(checkinfoaList!=null&&checkinfoaList.size()!=0)
			{
				checkinfoa=checkinfoaList.get(0);
			}
			else
			{
				
			}
			
			checkinfoa.setPropertyNumber(checkinfoa.getPropertyNumber());
			checkinfoa.setResponsibilityDepartment(checkinfoa.getResponsibilityDepartment());
			checkinfoa.setMachineLocation(checkinfoa.getMachineLocation());
			checkinfoa.setModel(checkinfoa.getModel());
			checkinfoa.setSystemInfo(checkinfoa.getSystemInfo());
			checkinfoa.setIpadd(checkinfoa.getIpadd());
			checkinfoa.setMachineUsage(checkinfoa.getMachineUsage());
			checkinfoa.setYear(String.valueOf(year));
			checkinfoa.setMachineinfo(machineinfo);

			manchineManagementDao.saveOrUpdate(checkinfoa);

			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (checkinfoa);
			
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
			
			
			
			Checkinfoa checkinfoa=new Checkinfoa();
			List<Checkinfoa> checkinfoaList= manchineManagementDao.find("from Checkinfoa where id=?",new Object[]{Integer.parseInt(request.getParameter("id"))});
			if(checkinfoaList!=null&&checkinfoaList.size()!=0)
			{
				checkinfoa=checkinfoaList.get(0);
			}
			else
			{
				
			}
 
			checkinfoa.setFlowNumber( request.getParameter("flowNumber"));
			checkinfoa.setPropertyNumber(request.getParameter("propertyNumber"));
			checkinfoa.setSerialNumber(request.getParameter("serialNumber"));
			checkinfoa.setResponsibilityDepartment( request.getParameter("responsibilityDepartment"));
			checkinfoa.setMachineLocation(request.getParameter("machineLocation"));
			checkinfoa.setModel( request.getParameter("model"));
			checkinfoa.setSystemInfo(request.getParameter("systemInfo"));
			checkinfoa.setIpadd(request.getParameter("ipAdd"));
			checkinfoa.setMachineUsage(request.getParameter("machineUsage"));	   
			checkinfoa.setMantainceStaff(request.getParameter("mantainceStaff"));   
			checkinfoa.setBackupContent(request.getParameter("backupContent"));	   
			checkinfoa.setBackupContentChange1(request.getParameter("backupContentChange1"));
			checkinfoa.setBackupContentChange2(request.getParameter("backupContentChange2"));
			checkinfoa.setFileDirectory(request.getParameter("fileDirectory"));
			checkinfoa.setFileDirectoryChange1(request.getParameter("fileDirectoryChange1"));
			checkinfoa.setFileDirectoryChange2(request.getParameter("fileDirectoryChange2"));
			checkinfoa.setBackupPeriod(request.getParameter("backupPeriod"));
			checkinfoa.setBackupPeriodChange1(request.getParameter("backupPeriodChange1"));
			checkinfoa.setBackupPeriodChange2(request.getParameter("backupPeriodChange2"));
			checkinfoa.setYear(request.getParameter("year"));
			List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{Integer.parseInt(request.getParameter("machineInfoID"))});
			if(machineinfoList!=null&&machineinfoList.size()!=0)
			{
				checkinfoa.setMachineinfo(machineinfoList.get(0));
			}
			else
			{
				checkinfoa.setMachineinfo(null);
			}
 
			manchineManagementDao.saveOrUpdate(checkinfoa);
			mv.addObject("data","检查表A基本信息更新成功");
 
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}
	
	
}
