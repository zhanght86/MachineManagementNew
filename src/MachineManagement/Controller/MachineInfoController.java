package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
import com.las.MachineManagement.Bean.Transferrecord;
import com.las.MachineManagement.Bean.UsersMachineinfoR;
import com.las.MachineManagement.Bean.UsersMachineinforesponsibleR;
import com.springframework.orm.ManchineManagementDao;

import MachineManagement.Common.CommonDataHelper;
import Support.*;

@Controller
@Scope("prototype")
public class MachineInfoController {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	@Autowired(required=true) 
	private CommonDataHelper commonDataHelper;
	
	
	
	//设备列表全体信息及搜索
	@RequestMapping("GetMachineInfoList")
	public ModelAndView GetMachineInfoList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
		{
			 return new ModelAndView(new RedirectView(""));
		}
     
		ModelAndView mv=new ModelAndView("GetMachineInfoList");
		return mv;
	}
	
	public String keywordProcess(String keyword)
	{
		String result="";
		try
		{
			result=keyword.trim().replaceAll(" +", "|");
			result="("+result+")";
//			System.out.println(result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			result="";
		}
		return result;
	}
	//设备列表全体信息及搜索
	@RequestMapping("loadMachineInfoList")
	public ModelAndView loadMachineInfoList(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		
		ModelAndView mv=new ModelAndView();

		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			String keyword=request.getParameter("keyword").toString();
			int pageamount=Integer.parseInt(request.getParameter("pageamount").toString());
			int pagecounter=Integer.parseInt(request.getParameter("pagecounter").toString());
			String showall=request.getParameter("showall").toString();
			String searchcondition=request.getParameter("searchcondition").toString();
			String orderstring=request.getParameter("orderstring").toString();
			int userid=Integer.parseInt(request.getParameter("userid").toString());
			String pagestr="";
			
//			keyword=keyword.trim();
//			System.out.println(keyword);
//			keyword=keyword.replaceAll(" +", "|");
//			System.out.println(keyword);
//			System.out.println(searchcondition);
			
		    List<Object> result=commonDataHelper.getMachineInfoList(keyword, pageamount, pagecounter, showall, searchcondition, orderstring, userid);
		    
		    List<Machineinfo> machineInfoList=new ArrayList<Machineinfo>();

            if(result!=null)
            {
        	  pagestr=(String)result.get(0);
        	  machineInfoList=(List<Machineinfo>)result.get(1);
            }
//            System.out.println(machineInfoList.size());
			httpSession.setAttribute("isadmin", commonDataHelper.isadmin(userid));
            JSONObject json=new JSONObject(pagestr);
		    mv=new ModelAndView("machineinfolist");
		    mv.addObject("isadmin", httpSession.getAttribute("isadmin"));
		    mv.addObject("MachineInfoList", machineInfoList);
		    mv.addObject("pagecounter",json.get("pagecounter"));
		    mv.addObject("totalpages", json.get("totalpages"));
		    mv.addObject("pageamount",json.get("pageamount"));
		    mv.addObject("totalamount",json.get("totalamount"));
		    
            mv.addObject("keyword",keyword);
            int firstnumber=((pagecounter-1)*pageamount)+1;
            mv.addObject("firstnumber",firstnumber);
          
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
		}
		
		return mv;
	}
	
	//通过ID查找设备信息
	@RequestMapping("GetMachineInfoById")
	public ModelAndView getMachineInfoById(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		
		ModelAndView mv=new ModelAndView();

		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int id=Integer.parseInt(request.getParameter("id").toString());
		    Machineinfo machineinfo=new Machineinfo();
		    machineinfo=commonDataHelper.getMachineInfoById(id);

			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (machineinfo);
 
			mv.addObject("data",obj.toString());
          
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			System.out.println(ex.toString());
			//mv=new ModelAndView("error");
		}
		
		return mv;
	}
	
	
	
	//新曾机器
	@RequestMapping("addmachine")
	public ModelAndView addMachine(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		ModelAndView mv=new ModelAndView();

		try
		{    
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			 String propertyNumber=request.getParameter("propertyNumber");
			 String machineLocation=request.getParameter("machineLocation");
			 String model=request.getParameter("model");
			 String ipAdd=request.getParameter("ipAdd");
			 String machineUsage=request.getParameter("machineUsage");
			 String department=request.getParameter("department");
			 String responsible=request.getParameter("responsible");
			 String responsibleEmail=request.getParameter("responsibleEmail");
			 String responsibleContactNumber=request.getParameter("responsibleContactNumber");
			 String systemInfo=request.getParameter("systemInfo");
			 Date purchaseTime=Formatter.DateTimeFormatter(request.getParameter("purchaseTime").toString());
			 String price=request.getParameter("price");
			 String project=request.getParameter("project");
			 String comments=java.net.URLDecoder.decode(request.getParameter("comments"), "UTF-8");
			 Date moveInTime=Formatter.DateTimeFormatter(request.getParameter("moveInTime").toString());
             int userid=Integer.parseInt(request.getParameter("userid"));
             int responsibleid= Integer.parseInt( request.getParameter("responsibleid"));
             
			 String purchaser=request.getParameter("purchaser");
			 String purchaseMethod=request.getParameter("purchaseMethod");
			 String supplier=request.getParameter("supplier");
			 String supplierContact=request.getParameter("supplierContact");
			 String supplierContactNumber=request.getParameter("supplierContactNumber");
			 String machineType=request.getParameter("machineType");
			 String propertyName=request.getParameter("propertyName");
			 
			 Machineinfo  machineinfo=new Machineinfo();
			 machineinfo.setPropertyNumber(propertyNumber);
			 machineinfo.setMachineLocation(machineLocation);
			 machineinfo.setModel(model);
			 machineinfo.setIpadd(ipAdd);
			 machineinfo.setMachineUsage(machineUsage);
			 machineinfo.setDepartment(department);
			 machineinfo.setResponsible(responsible);
			 machineinfo.setResponsibleEmail(responsibleEmail);
			 machineinfo.setResponsibleContactNumber(responsibleContactNumber);
			 machineinfo.setSystemInfo(systemInfo);
			 machineinfo.setPurchaseTime(purchaseTime);
			 machineinfo.setPrice(price);
			 machineinfo.setProject(project);
			 machineinfo.setComments(comments);
			 machineinfo.setMoveInTime(moveInTime);
			 machineinfo.setPurchaser(purchaser);
			 machineinfo.setPurchaseMethod(purchaseMethod);
			 machineinfo.setSupplier(supplierContactNumber);
			 machineinfo.setSupplierContact(supplierContact);
			 machineinfo.setSupplierContactNumber(supplierContactNumber);
			 machineinfo.setMachineType(machineType);
			 machineinfo.setResponsibleUserId(responsibleid);
			 machineinfo.setPropertyName(propertyName);
			 machineinfo.setState("1");
			 manchineManagementDao.saveOrUpdate(machineinfo);
 
			 UsersMachineinfoR usersMachineinfoR=new UsersMachineinfoR();
			 usersMachineinfoR.setMachineinfoid(String.valueOf(machineinfo.getId()));
			 usersMachineinfoR.setUserid(String.valueOf(userid));
			 usersMachineinfoR.setState("1");
			 manchineManagementDao.saveOrUpdate(usersMachineinfoR);
			 
			 UsersMachineinforesponsibleR usersMachineinforesponsibleR=new UsersMachineinforesponsibleR();
			 usersMachineinforesponsibleR.setMachineinfoid(String.valueOf(machineinfo.getId()));
			 usersMachineinforesponsibleR.setUserid(String.valueOf(userid));
			 usersMachineinforesponsibleR.setState("1");
			 manchineManagementDao.saveOrUpdate(usersMachineinforesponsibleR);
			 
			 Transferrecord transferrecord=new Transferrecord();
			 transferrecord.setMachineinfo(machineinfo);
			 transferrecord.setCurOwner(machineinfo.getResponsible());
			 transferrecord.setCurOwnerEmail(machineinfo.getResponsibleEmail());
			 transferrecord.setDepartment(machineinfo.getDepartment());
			 transferrecord.setPreOwner(machineinfo.getResponsible());
			 transferrecord.setPreOwnerEmail(machineinfo.getResponsibleEmail());
			 transferrecord.setState("1");
			 manchineManagementDao.saveOrUpdate(transferrecord);
			 
			 mv.addObject("data","新增成功");
 
		}
		catch(Exception ex)
		{
			System.out.println("addmachine : "+ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		
		return mv;
	}
	
	
    //更新机器
	@RequestMapping("updatemachine")
	public ModelAndView updateMachine(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		ModelAndView mv=new ModelAndView();

		try
		{    
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			 int userid=Integer.parseInt(request.getParameter("userid"));
			 int id=Integer.parseInt(request.getParameter("id"));
			 
			 
			 
			 String propertyNumber=request.getParameter("propertyNumber");
			 String machineLocation=request.getParameter("machineLocation");
			 String model=request.getParameter("model");
			 String ipAdd=request.getParameter("ipAdd");
			 String machineUsage=request.getParameter("machineUsage");
			 String department=request.getParameter("department");
			 String responsible=request.getParameter("responsible");
			 String responsibleEmail=request.getParameter("responsibleEmail");
			 String responsibleContactNumber=request.getParameter("responsibleContactNumber");
			 String systemInfo=request.getParameter("systemInfo");
			 Date purchaseTime=Formatter.DateTimeFormatter(request.getParameter("purchaseTime").toString());
			 String price=request.getParameter("price");
			 String project=request.getParameter("project");
			 String comments=java.net.URLDecoder.decode(request.getParameter("comments"), "utf-8");
			 Date moveInTime=Formatter.DateTimeFormatter(request.getParameter("moveInTime").toString());

			 String purchaser=request.getParameter("purchaser");
			 String purchaseMethod=request.getParameter("purchaseMethod");
			 String supplier=request.getParameter("supplier");
			 String supplierContact=request.getParameter("supplierContact");
			 String supplierContactNumber=request.getParameter("supplierContactNumber");
			 String machineType=request.getParameter("machineType");
			 String propertyName=request.getParameter("propertyName");
			 
			 
			 Machineinfo machineinfo=(Machineinfo) manchineManagementDao.find(" from Machineinfo where id=?  and state=1 ",new Object[]{id}).get(0);
			 machineinfo.setPropertyNumber(propertyNumber);
			 machineinfo.setMachineLocation(machineLocation);
			 machineinfo.setModel(model);
			 machineinfo.setIpadd(ipAdd);
			 machineinfo.setMachineUsage(machineUsage);
			 machineinfo.setDepartment(department);
			 machineinfo.setResponsible(responsible);
			 machineinfo.setResponsibleEmail(responsibleEmail);
			 machineinfo.setResponsibleContactNumber(responsibleContactNumber);
			 machineinfo.setSystemInfo(systemInfo);
			 machineinfo.setPurchaseTime(purchaseTime);
			 machineinfo.setPrice(price);
			 machineinfo.setProject(project);
			 machineinfo.setComments(comments);
			 machineinfo.setMoveInTime(moveInTime);
			 machineinfo.setPurchaser(purchaser);
			 machineinfo.setPurchaseMethod(purchaseMethod);
			 machineinfo.setSupplier(supplierContactNumber);
			 machineinfo.setSupplierContact(supplierContact);
			 machineinfo.setSupplierContactNumber(supplierContactNumber);
			 machineinfo.setMachineType(machineType);
			 machineinfo.setPropertyName(propertyName);
			 machineinfo.setState("1");
			 manchineManagementDao.saveOrUpdate(machineinfo);
			 
			 List<Checkinfoa> checkinfoaList= manchineManagementDao.find(" from Checkinfoa where machineinfo.id=?  and state=1 ",new Object[]{id});
			 for(Checkinfoa checkinfoa:checkinfoaList)
			 {
 
				 checkinfoa.setPropertyNumber(machineinfo.getPropertyNumber());
				 checkinfoa.setMachineLocation(machineinfo.getMachineLocation());
				 checkinfoa.setModel(machineinfo.getModel());
				 checkinfoa.setIpadd(machineinfo.getIpadd());
				 checkinfoa.setMachineUsage(machineinfo.getMachineUsage());
				 checkinfoa.setResponsibilityDepartment(machineinfo.getDepartment());
				 checkinfoa.setSystemInfo(machineinfo.getSystemInfo());
				 manchineManagementDao.saveOrUpdate(checkinfoa);
				 
			 }
			 
			 List<Checkinfob> checkinfobList= manchineManagementDao.find(" from Checkinfob where machineinfo.id=? and state=1 ",new Object[]{id});
			 for(Checkinfob checkinfob:checkinfobList)
			 {
  
				 checkinfob.setPropertyNumber(machineinfo.getPropertyNumber());
				 checkinfob.setMachineLocation(machineinfo.getMachineLocation());
				 checkinfob.setModel(machineinfo.getModel());
				 checkinfob.setIpadd(machineinfo.getIpadd());
				 checkinfob.setMachineUsage(machineinfo.getMachineUsage());
				 checkinfob.setResponsibilityDepartment(machineinfo.getDepartment());
				 checkinfob.setSystemInfo(machineinfo.getSystemInfo());
				 manchineManagementDao.saveOrUpdate(checkinfob);
				 
			 }
			 
			 List<UsersMachineinforesponsibleR> usersMachineinforesponsibleRList= manchineManagementDao.find(" from UsersMachineinforesponsibleR where machineinfoid.id=? and userid=? ",new Object[]{id,userid});
			 if(usersMachineinforesponsibleRList!=null&&usersMachineinforesponsibleRList.size()!=0)
			 {
				 UsersMachineinforesponsibleR usersMachineinforesponsibleR=usersMachineinforesponsibleRList.get(0);
				 usersMachineinforesponsibleR.setMachineinfoid(String.valueOf(id));
				 usersMachineinforesponsibleR.setUserid(String.valueOf(userid));
				 manchineManagementDao.saveOrUpdate(usersMachineinforesponsibleR);
			 }
			mv.addObject("data","更新成功");
			 
		 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		
		return mv;
	}
	
	//删除机器
	@RequestMapping("deletemachine")
	public ModelAndView deleteMachine(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
	{
		ModelAndView mv=new ModelAndView();

		try
		{    
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			
		    Machineinfo machineInfo=(Machineinfo) manchineManagementDao.find(" from Machineinfo where id=?",new Object[]{id}).get(0);
		    machineInfo.setState("2");
		    manchineManagementDao.saveOrUpdate(machineInfo);
			mv.addObject("data","删除成功");
 
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		
		return mv;
	}
}
