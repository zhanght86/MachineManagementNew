package MachineManagement.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.las.MachineManagement.BeanOther.CheckInfo;
import com.springframework.orm.ManchineManagementDao;



@Controller 
@Scope("prototype")
public class CheckinfoHistory {

	

	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
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
		  List<CheckInfo> checkInfoList=new ArrayList<CheckInfo>();
		  
		  String  str1 ="'%' "+keyword_machineid+"'%'";
		  String str2="'%' "+keyword_year+"'%'";   

		  String sql="(select 'A' as type,machineinfo.id as machindid,`year` as checkyear "
		  		+ "from checkinfoa inner join machineinfo on machineinfo.id=checkinfoa.MachineInfoID  "
		  		+ "where checkinfoa.state=1 and CONCAT(machineinfo.id) like str1 and CONCAT(checkinfoa.`year`) like str2 ) "
		  		+ "UNION (select 'B' as type,machineinfo.id as machindid,`year` as checkyear from checkinfob inner join machineinfo on machineinfo.id=checkinfob.MachineInfoID  "
		  		+ "where checkinfob.state=1 and CONCAT(machineinfo.id) like str1 and CONCAT(checkinfob.`year`) like str2) order by machindid desc;";
		  
		  List<Object[]> result=manchineManagementDao.findBySQL(sql);
		  for(Object[] obj:result)
		  {
			  CheckInfo checkInfo=new CheckInfo();
			  checkInfo.setType((String)obj[0]);
			  checkInfo.setId((Integer)obj[1]);
			  checkInfo.setYear((Integer)obj[2]);
			  checkInfoList.add(checkInfo);
		  }
 
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
