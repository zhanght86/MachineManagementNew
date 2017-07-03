package com.MachineManagement.Controller;

import java.util.Calendar;
import java.util.List;

//import java.util.ArrayList;

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

import com.Email.EmailHelper;
import com.Email.EmailInfo;
import com.MachineManagement.Common.CommonDataHelper;
import com.las.MachineManagement.Bean.Checkinfoa;
import com.las.MachineManagement.Bean.Checkinfob;
import com.las.MachineManagement.Bean.Checkrecorda;
import com.las.MachineManagement.Bean.Checkrecordb;
import com.las.MachineManagement.Bean.Emailconfiguration;
import com.las.MachineManagement.Bean.Machineinfo;
import com.springframework.orm.ManchineManagementDao;
 

@Controller
@Scope("prototype")
public class EmailController {

	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	@Autowired(required=true) 
	private CommonDataHelper commonDataHelper;
	
	
     //自动生成邮件内容并发送
	@RequestMapping("sendEmailById")
	public ModelAndView sendEmailById(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{

		ModelAndView mv=new ModelAndView();

		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int machineid=Integer.parseInt(request.getParameter("machineid").toString());
			int userid=Integer.parseInt(request.getParameter("userid").toString());
			//ArrayList<String> receiver=new ArrayList<String>();
			
			
			Emailconfiguration emailConfiguration=new Emailconfiguration();
			List<Emailconfiguration> emailconfigurationList= manchineManagementDao.find("from Emailconfiguration where id=?",new Object[]{userid});
			if(emailconfigurationList!=null&&emailconfigurationList.size()!=0)
			{
				emailConfiguration=emailconfigurationList.get(0);
			}
			else
			{
				
			}
			
	 		  EmailInfo emailInfo=new EmailInfo();
	 	  	  Calendar cal = Calendar.getInstance();
	     	  int year = cal.get(Calendar.YEAR);
	     	  int month=cal.get(Calendar.MONTH)+1;
	     	  int day=cal.get(Calendar.DAY_OF_MONTH);
	     	  
	     	  
	     	    Machineinfo machineInfo=new Machineinfo();
				List<Machineinfo> machineInfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{machineid});
				if(machineInfoList!=null&&machineInfoList.size()!=0)
				{
					machineInfo=machineInfoList.get(0);
				}
				else
				{
					
				}
				
				
				Checkrecorda checkrecorda=new Checkrecorda();
				List<Checkrecorda> checkrecordaList=manchineManagementDao.find("from Checkrecorda where checkinfoa.machineinfo.id=? and  checkinfoa.year=? and monthNumber=?",new Object[]{machineid,String.valueOf(year),month},1,1);
				if(checkrecordaList!=null&&checkrecordaList.size()!=0)
				{
					checkrecorda=checkrecordaList.get(0);
				}
				else
				{
					List<Checkinfoa> checkinfoaList=manchineManagementDao.find("from Checkinfoa where  machineinfo.id=? and  year=?  ",new Object[]{machineid,String.valueOf(year)},1,1);
					if(checkinfoaList!=null&&checkinfoaList.size()!=0)
					{
						checkrecorda.setCheckinfoa(checkinfoaList.get(0));
						manchineManagementDao.saveOrUpdate(checkrecorda);
					}
				}
				
				Checkrecordb checkrecordb=new Checkrecordb();
				List<Checkrecordb> checkrecordbList=manchineManagementDao.find("from Checkrecordb where checkinfob.machineinfo.id=? and  checkinfob.year=? and monthNumber=?",new Object[]{machineid,String.valueOf(year),month},1,1);
				if(checkrecordbList!=null&&checkrecordbList.size()!=0)
				{
					checkrecordb=checkrecordbList.get(0);
				}
				else
				{
					List<Checkinfob> checkinfobList=manchineManagementDao.find("from Checkinfob where  machineinfo.id=? and  year=?  ",new Object[]{machineid,String.valueOf(year)},1,1);
					if(checkinfobList!=null&&checkinfobList.size()!=0)
					{
						checkrecordb.setCheckinfob(checkinfobList.get(0));
						manchineManagementDao.saveOrUpdate(checkrecordb);
					}
				}
				
	 		  //设置收件人
	 		  emailInfo.receiver.add(machineInfo.getResponsibleEmail());
	 		  //设置邮件标题
	 		  emailInfo.title="设备月度检查提示-设备资产号："+machineInfo.getPropertyNumber();
	 		  emailInfo.body=commonDataHelper.CheckCheckRecord(machineInfo, year, month);
	 		  
	 		  
	 		  emailInfo.sender=emailConfiguration.getUserName();
			
			
			EmailHelper emailHelper=new EmailHelper(emailConfiguration,emailInfo);

			if(emailInfo.body.equals(""))
			{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","该设备本月检查表填写完整");
			}
			else
			{
				
				if(emailHelper.Send())
			    {
					mv=new ModelAndView("/common/data");
					mv.addObject("data","邮件已发送");
			    }
				else
				{
					mv=new ModelAndView("/common/data");
					mv.addObject("data","邮件发送失败");
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println("sendEmailById : "+ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		
		return mv;
	}
	
	

	
}
