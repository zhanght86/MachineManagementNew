package MachineManagement.Controller;
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

import com.las.MachineManagement.Bean.Checkinfob;
import com.las.MachineManagement.Bean.Checkrecordb;
import com.springframework.orm.ManchineManagementDao;



@Controller 
@Scope("prototype")
public class CheckRecordBController {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	//取得检查表B月度检查的信息
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

			Checkrecordb checkrecordb=new Checkrecordb();
			List<Checkrecordb> checkrecordbList=manchineManagementDao.find("from Checkrecordb where checkinfob.machineinfo.id=? and  checkinfob.year=? and monthNumber=?",new Object[]{id,String.valueOf(year),month},1,1);
			if(checkrecordbList!=null&&checkrecordbList.size()!=0)
			{
				checkrecordb=checkrecordbList.get(0);
			}
			else
			{
				List<Checkinfob> checkinfobList=manchineManagementDao.find("from Checkinfob where  machineinfo.id=? and  year=?  ",new Object[]{id,String.valueOf(year)},1,1);
				if(checkinfobList!=null&&checkinfobList.size()!=0)
				{
					checkrecordb.setCheckinfob(checkinfobList.get(0));
					manchineManagementDao.saveOrUpdate(checkrecordb);
				}
			}
			
			
			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (checkrecordb);
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
				
				Checkrecordb checkrecordb=new Checkrecordb();
				List<Checkrecordb> checkrecordbList= manchineManagementDao.find("from Checkrecordb where id=?",new Object[]{Integer.parseInt(request.getParameter("id"))});
				if(checkrecordbList!=null&&checkrecordbList.size()!=0)
				{
					checkrecordb=checkrecordbList.get(0);
				}
				else
				{
					
				}
						
				
				Checkinfob checkinfob=new Checkinfob();
				List<Checkinfob> checkinfobList= manchineManagementDao.find("from Checkinfob where id=?",new Object[]{Integer.parseInt(request.getParameter("checkInfoID"))});
				if(checkinfobList!=null&&checkinfobList.size()!=0)
				{
					checkinfob=checkinfobList.get(0);
					checkrecordb.setCheckinfob(checkinfob);
				}
				else
				{
					checkrecordb.setCheckinfob(null);
				}
				
				checkrecordb.setMonthNumber(Integer.parseInt(request.getParameter("monthNumber")));
				checkrecordb.setNetworkBackup(request.getParameter("networkBackup"));
				checkrecordb.setHarddriverBackup(request.getParameter("harddriverBackup"));
				checkrecordb.setLogUploadAnalysis(request.getParameter("logUploadAnalysis"));
				checkrecordb.setFirewallCheck(request.getParameter("firewallCheck"));
				checkrecordb.setMonthlyFloatAmount(request.getParameter("monthlyFloatAmount"));
				checkrecordb.setServerStoppedInfo(request.getParameter("serverStoppedInfo"));
				checkrecordb.setSignature(request.getParameter("signature"));
				manchineManagementDao.saveOrUpdate(checkrecordb);
				mv.addObject("data","检查表B月度信息更新成功");
				
 
			}
			catch(Exception ex)
			{
				mv=new ModelAndView("/common/error");
				mv.addObject("data",ex.toString());
			}
			return mv;
		}
		
		
}
