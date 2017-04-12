package MachineManagement.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import MachineManagement.DataBaseHelper.BusinessHelper;


@Controller 
@Scope("prototype")
public class CheckinfoHistory {

	//得到检查信息列表
	@RequestMapping("GetCheckInfoHistoryList")
	public ModelAndView checkinfohistory(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		
		ModelAndView mv=new ModelAndView("checkinfohistory");
		if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
		{
			 return new ModelAndView(new RedirectView(""));
		}
		int pagecounter=Integer.parseInt(request.getParameter("pagecounter").toString());
		int amount=10;
		int totalamount=0;
		String keyword_machineid="";
		String keyword_year="";
		
		
		try
		{
		
			keyword_machineid=request.getParameter("keyword_machineid").toString();
			keyword_year=request.getParameter("keyword_year").toString();

		}
		catch(Exception ex)
		{
			
			keyword_machineid="";
			keyword_year="";
		}
		
		try
		{
		  List<CheckinfoHistory> checkInfoList=new ArrayList<CheckinfoHistory>();
		  checkInfoList=BusinessHelper.getCheckInfoHistory("",keyword_machineid,keyword_year);
		  totalamount=checkInfoList.size();
		  
		  if(totalamount%amount!=0)
		  {
			  totalamount=totalamount/amount+1;
		  }
		  else
		  {
			  totalamount=totalamount/amount;
		  }
		  checkInfoList=checkInfoList.subList(amount*(pagecounter-1), amount*pagecounter>checkInfoList.size()?checkInfoList.size():amount*pagecounter);
		  mv=new ModelAndView("checkinfohistory");
		  mv.addObject("checkInfoList", checkInfoList);
		  mv.addObject("totalamount",totalamount);
		  mv.addObject("pagecounter",pagecounter);
          mv.addObject("keyword_machineid",keyword_machineid);
          mv.addObject("keyword_year",keyword_year);
          
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/error");
			mv.addObject("data",ex.toString());
		}
		
		return mv;
	}
	
}
