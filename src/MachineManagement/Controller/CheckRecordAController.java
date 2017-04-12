package MachineManagement.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



import org.springframework.web.servlet.view.RedirectView;

import com.las.MachineManagement.Bean.Checkrecorda;

import MachineManagement.DataBaseHelper.BusinessHelper;

import org.json.*;

@Controller 
@Scope("prototype")
public class CheckRecordAController {
	
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

			Checkrecorda checkRecordA=BusinessHelper.getCheckRecordA(id, year, month);
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject(checkRecordA);
			
//			obj.put("id", checkRecordA.id);
//			obj.put("checkInfoID", checkRecordA.checkInfoID);
//			obj.put("os", checkRecordA.os);
//			obj.put("mouthNumber", checkRecordA.monthNumber);
//			obj.put("patch", checkRecordA.patch);
//			obj.put("application", checkRecordA.application);
//			obj.put("application", checkRecordA.application);
//			obj.put("dataBaseCheck", checkRecordA.dataBaseCheck);
//			obj.put("check360", checkRecordA.check360);
//			obj.put("antivirus", checkRecordA.antivirus);
//			obj.put("passwordStrength", checkRecordA.passwordStrength);
//			obj.put("accountNormal", checkRecordA.accountNormal);
//			obj.put("accountAbnormal", checkRecordA.accountAbnormal);
//			obj.put("eventLog", checkRecordA.eventLog);
//			obj.put("webLog", checkRecordA.webLog);
//			obj.put("dataBaseLog", checkRecordA.dataBaseLog);
//			obj.put("hardDriverUsage", checkRecordA.hardDriverUsage);
//			obj.put("pageException", checkRecordA.pageException);
//			obj.put("reactionaryException", checkRecordA.reactionaryException);
//			obj.put("patchScanExist", checkRecordA.patchScanExist);
//			obj.put("patchScanExist", checkRecordA.patchScanExist);
//			obj.put("patchScanHandle", checkRecordA.patchScanHandle);
//			obj.put("patchScanOperator", checkRecordA.patchScanOperator);
//			obj.put("service", checkRecordA.service);
//			obj.put("osResponsibleSingnature", checkRecordA.osResponsibleSingnature);
//			obj.put("osAdminsitratorSignature", checkRecordA.osAdminsitratorSignature);
			
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
				
//				CheckRecordA checkRecordA=new CheckRecordA(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("checkInfoID")),
//						   request.getParameter("os"),Integer.parseInt(request.getParameter("monthNumber")),
//						   request.getParameter("application"),request.getParameter("dataBaseCheck"),request.getParameter("check360"),
//						   request.getParameter("antivirus"),request.getParameter("accountNormal"),request.getParameter("accountAbnormal"),request.getParameter("eventLog"),
//						   request.getParameter("webLog"),request.getParameter("dataBaseLog"),request.getParameter("hardDriverUsage"),request.getParameter("osResponsibleSingnature"),
//						   request.getParameter("osAdminsitratorSignature"),"");
				
				CheckRecordA checkRecordA=new CheckRecordA(Integer.parseInt(request.getParameter("id")),Integer.parseInt(request.getParameter("checkInfoID")) , request.getParameter("os"), 
						Integer.parseInt(request.getParameter("monthNumber")), request.getParameter("patch"), request.getParameter("application"),
	       				request.getParameter("dataBaseCheck"), request.getParameter("check360"), request.getParameter("antivirus"), request.getParameter("passwordStrength"), request.getParameter("accountNormal"),
	       						request.getParameter( "accountAbnormal"), request.getParameter("eventLog"), request.getParameter("webLog"), request.getParameter("dataBaseLog"),request.getParameter("hardDriverUsage"),
	       								request.getParameter( "pageException"), request.getParameter( "reactionaryException"), request.getParameter("patchScanExist"), request.getParameter("patchScanHandle"),
	       										request.getParameter("patchScanOperator"), request.getParameter("service"), request.getParameter("osResponsibleSingnature"), request.getParameter("osAdminsitratorSignature"),
	       												"");
				
				if(BusinessHelper.updateCheckRecordA(checkRecordA))
				{
					mv.addObject("data","检查表A月度信息更新成功");
				}
				else
				{
					mv.addObject("data","检查表A月度信息更新失败");
				}
				
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
