package com.MachineManagement.Controller;
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
import com.las.MachineManagement.Bean.Checkrecorda;
import com.springframework.orm.ManchineManagementDao;
import org.json.*;

@Controller 
@Scope("prototype")
public class CheckRecordAController {
	
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	//取得检查表A月度检查的信息
	@RequestMapping("CheckRecordA")
	public ModelAndView CheckRecordA(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int id=Integer.parseInt(request.getParameter("id"));
			int year=Integer.parseInt(request.getParameter("year"));
			int month=Integer.parseInt(request.getParameter("month"));
 
			Checkrecorda checkrecorda=new Checkrecorda();
			List<Checkrecorda> checkrecordaList=manchineManagementDao.find("from Checkrecorda where checkinfoa.machineinfo.id=? and  checkinfoa.year=? and monthNumber=?",new Object[]{id,String.valueOf(year),month},1,1);
			if(checkrecordaList!=null&&checkrecordaList.size()!=0)
			{
				checkrecorda=checkrecordaList.get(0);
			}
			else
			{
				List<Checkinfoa> checkinfoaList=manchineManagementDao.find("from Checkinfoa where  machineinfo.id=? and  year=?  ",new Object[]{id,String.valueOf(year)},1,1);
				if(checkinfoaList!=null&&checkinfoaList.size()!=0)
				{
					checkrecorda.setCheckinfoa(checkinfoaList.get(0));
					manchineManagementDao.saveOrUpdate(checkrecorda);
				}
			}
			
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject(checkrecorda);
			
			mv.addObject("data",obj.toString());
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		return mv;
	}


	//更新检查表A的信息
    @RequestMapping("updatecheckrecorda")
    public ModelAndView updatecheckrecorda(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
			ModelAndView mv=new ModelAndView();

			try
			{
				if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
				{
					 return new ModelAndView(new RedirectView(""));
				}
				mv=new ModelAndView("/common/data");
				
				Checkrecorda checkrecorda=new Checkrecorda();
				List<Checkrecorda> checkrecordaList= manchineManagementDao.find("from Checkrecorda where id=?",new Object[]{Integer.parseInt(request.getParameter("id"))});
				if(checkrecordaList!=null&&checkrecordaList.size()!=0)
				{
					checkrecorda=checkrecordaList.get(0);
				}
				else
				{
					
				}
						
				
				Checkinfoa checkinfoa=new Checkinfoa();
				List<Checkinfoa> checkinfoaList= manchineManagementDao.find("from Checkinfoa where id=?",new Object[]{Integer.parseInt(request.getParameter("checkInfoID"))});
				if(checkinfoaList!=null&&checkinfoaList.size()!=0)
				{
					checkinfoa=checkinfoaList.get(0);
					checkrecorda.setCheckinfoa(checkinfoa);
				}
				else
				{
					checkrecorda.setCheckinfoa(null);
				}
				
 
				checkrecorda.setOs(	request.getParameter("os"));
				checkrecorda.setMonthNumber(Integer.parseInt(request.getParameter("monthNumber")));
				checkrecorda.setPatch(request.getParameter("patch"));
				checkrecorda.setApplication(request.getParameter("application"));
				checkrecorda.setDataBaseCheck(request.getParameter("dataBaseCheck"));
				checkrecorda.setCheck360(request.getParameter("check360"));
				checkrecorda.setAntivirus(request.getParameter("antivirus"));
				checkrecorda.setPasswordStrength(request.getParameter("passwordStrength"));
				checkrecorda.setAccountNormal(request.getParameter("accountNormal"));	
				checkrecorda.setAccountAbnormal(request.getParameter("accountAbnormal"));
				checkrecorda.setEventLog(request.getParameter("eventLog"));
				checkrecorda.setWebLog(request.getParameter("webLog"));
				checkrecorda.setDataBaseLog(request.getParameter("dataBaseLog"));
				checkrecorda.setHardDriverUsage(request.getParameter("hardDriverUsage"));
				checkrecorda.setPageException(request.getParameter("pageException"));
				checkrecorda.setReactionaryException(request.getParameter("reactionaryException"));
				checkrecorda.setPatchScanExist(request.getParameter("patchScanExist"));
				checkrecorda.setPatchScanHandle(request.getParameter("patchScanHandle"));
				checkrecorda.setPatchScanOperator(request.getParameter("patchScanOperator"));
				checkrecorda.setService(request.getParameter("service"));
				checkrecorda.setOsadminsitratorSignature(request.getParameter("osResponsibleSingnature"));	
				checkrecorda.setOsadminsitratorSignature(request.getParameter("osAdminsitratorSignature"));
				manchineManagementDao.saveOrUpdate(checkrecorda);
				mv.addObject("data","检查表A月度信息更新成功");
 
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				mv=new ModelAndView("/common/error");
				mv.addObject("data",ex.toString());
			}
			return mv;
		}
		
		
}
