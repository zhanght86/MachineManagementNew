package MachineManagement.Controller;

//import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import Email.EmailHelper;
import Email.EmailInfo;
import MachineManagement.DataBaseHelper.BusinessHelper;
import MachineManagement.DataModel.EmailConfiguration;

@Controller
@Scope("prototype")
public class EmailController {

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
			
			EmailConfiguration emailConfiguration=BusinessHelper.getEmailConfigurationByUserID(userid);
			EmailInfo emailInfo=BusinessHelper.getEmalInfo(machineid,emailConfiguration);
			
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
