package MachineManagement.Controller;
import java.util.Calendar;
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

import com.las.MachineManagement.Bean.Checkinfoa;
import com.las.MachineManagement.Bean.Checkinfob;
import com.las.MachineManagement.Bean.Machineinfo;
import com.springframework.orm.ManchineManagementDao;


@Controller 
@Scope("prototype")
public class CheckInfoBController {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	
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
	    	  
			
	    	  Machineinfo machineinfo=new Machineinfo();
					List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{id});
					if(machineinfoList!=null&&machineinfoList.size()!=0)
					{
						machineinfo=machineinfoList.get(0);
					}
	 
				
				Checkinfob checkinfob=new Checkinfob();
				List<Checkinfob> checkinfobList= manchineManagementDao.find("from Checkinfob where machineinfo.id=? and year=?",new Object[]{id,String.valueOf(year)});
				if(checkinfobList!=null&&checkinfobList.size()!=0)
				{
					checkinfob=checkinfobList.get(0);
				}
				else
				{
					
				}
				
				checkinfob.setPropertyNumber(machineinfo.getPropertyNumber());
				checkinfob.setResponsibilityDepartment(machineinfo.getDepartment());
				checkinfob.setMachineLocation(machineinfo.getMachineLocation());
				checkinfob.setModel(machineinfo.getModel());
				checkinfob.setSystemInfo(machineinfo.getSystemInfo());
				checkinfob.setIpadd(machineinfo.getIpadd());
				checkinfob.setMachineUsage(machineinfo.getMachineUsage());
				checkinfob.setYear(String.valueOf(year));
				checkinfob.setMachineinfo(machineinfo);
				
				manchineManagementDao.saveOrUpdate(checkinfob);
				
				
				
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (checkinfob);
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
			
			
			
			Checkinfob checkInfob=new Checkinfob();
			List<Checkinfob> CheckinfobList= manchineManagementDao.find("from Checkinfob where id=?",new Object[]{Integer.parseInt(request.getParameter("id"))});
			if(CheckinfobList!=null&&CheckinfobList.size()!=0)
			{
				checkInfob=CheckinfobList.get(0);
			}
			else
			{
				
			}
			
			checkInfob.setFlowNumber(request.getParameter("flowNumber"));
			checkInfob.setPropertyNumber(request.getParameter("propertyNumber"));
			checkInfob.setSerialNumber(request.getParameter("serialNumber"));
			checkInfob.setResponsibilityDepartment(request.getParameter("responsibilityDepartment"));
			checkInfob.setMachineLocation(request.getParameter("machineLocation"));
			checkInfob.setModel(request.getParameter("model"));
			checkInfob.setSystemInfo(request.getParameter("systemInfo"));
			checkInfob.setIpadd(request.getParameter("ipAdd"));
			checkInfob.setMachineUsage(request.getParameter("machineUsage"));
			checkInfob.setMantainceStaff(request.getParameter("mantainceStaff"));
			checkInfob.setYear(request.getParameter("year"));
			List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{Integer.parseInt(request.getParameter("machineInfoID"))});
			if(machineinfoList!=null&&machineinfoList.size()!=0)
			{
				checkInfob.setMachineinfo(machineinfoList.get(0));
			}
			else
			{
				checkInfob.setMachineinfo(null);
			}
			checkInfob.setComments(request.getParameter("comments"));
			manchineManagementDao.saveOrUpdate(checkInfob);
			mv.addObject("data","检查表B基本信息更新成功");

			
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
