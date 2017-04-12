package MachineManagement.Controller;
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

import MachineManagement.DataBaseHelper.BusinessHelper;
import MachineManagement.DataModel.*;

@Controller 
@Scope("prototype")
public class CheckRecordBController {
	//取得检查表A月度检查的信息
	@RequestMapping("CheckRecordB")
	public ModelAndView CheckRecordB(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
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

			CheckRecordB  checkRecordB=BusinessHelper.getCheckRecordB(id, year, month);
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject ();
			
			obj.put("id", checkRecordB.id);
			obj.put("checkInfoID", checkRecordB.checkInfoID);
			obj.put("monthNumber", checkRecordB.monthNumber);
			obj.put("networkBackup", checkRecordB.networkBackup);
			obj.put("harddriverBackup", checkRecordB.harddriverBackup);
			obj.put("logUploadAnalysis", checkRecordB.logUploadAnalysis);
			obj.put("firewallCheck", checkRecordB.firewallCheck);
			obj.put("monthlyFloatAmount", checkRecordB.monthlyFloatAmount);
			obj.put("serverStoppedInfo", checkRecordB.serverStoppedInfo);
			obj.put("signature", checkRecordB.signature);
	
	
			
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
    @RequestMapping("updatecheckrecordb")
    public ModelAndView updatecheckrecordb(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
			ModelAndView mv=new ModelAndView();
			try
			{
				if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
				{
					 return new ModelAndView(new RedirectView(""));
				}
				mv=new ModelAndView("/common/data");
				
				CheckRecordB checkRecordB=new CheckRecordB(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("checkInfoID")),
						Integer.parseInt(request.getParameter("monthNumber")),request.getParameter("networkBackup"),
						   request.getParameter("harddriverBackup"),request.getParameter("logUploadAnalysis"),request.getParameter("firewallCheck"),
						   request.getParameter("monthlyFloatAmount"),request.getParameter("serverStoppedInfo"),request.getParameter("signature"),"");
				

				if(BusinessHelper.updateCheckRecordB(checkRecordB))
				{
					mv.addObject("data","检查表B月度信息更新成功");
				}
				else
				{
					mv.addObject("data","检查表B月度信息更新失败");
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
