package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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

import com.las.MachineManagement.Bean.Machineinfo;

import MachineManagement.DataBaseHelper.BusinessHelper;
import Support.*;

@Controller
@Scope("prototype")
public class MachineInfoController {
	
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
			
		    List<Object> result=BusinessHelper.getMachineInfoList(keywordProcess(keyword),pageamount,pagecounter,showall,searchcondition,orderstring,userid);
		    
		    List<Machineinfo> machineInfoList=new ArrayList<Machineinfo>();

            if(result!=null)
            {
        	  pagestr=(String)result.get(0);
        	  machineInfoList=(List<Machineinfo>)result.get(1);
            }
//            System.out.println(machineInfoList.size());
			httpSession.setAttribute("isadmin", BusinessHelper.isadmin(userid));
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
		    MachineInfo mi=new MachineInfo();
		    mi=BusinessHelper.getMachineInfoById(id);

			mv=new ModelAndView("/common/data");
			JSONObject obj=new JSONObject (mi);
			
//			obj.put("propertyName", mi.getPropertyName());
//			obj.put("responsibleid", mi.responsibleUserId);
//			obj.put("id", mi.id);
//			obj.put("propertyNumber", mi.propertyNumber);
//			obj.put("machineLocation", mi.machineLocation);
//			obj.put("model", mi.model);
//			obj.put("ipAdd", mi.ipAdd);
//			obj.put("machineUsage", mi.machineUsage);
//			obj.put("department", mi.department);
//			obj.put("responsible", mi.responsible);
//			obj.put("responsibleEmail", mi.responsibleEmail);
//			obj.put("responsibleContactNumber", mi.responsibleContactNumber);
//			obj.put("systemInfo", mi.systemInfo);
//			obj.put("purchaseTime", mi.purchaseTime);
//			obj.put("price", mi.price);
//			obj.put("project", mi.project);
//			obj.put("comments", mi.comments);
//			obj.put("registrant", mi.registrant);
//			obj.put("moveInTime", mi.moveInTime);
//			obj.put("updateTime", mi.updateTime);
//			obj.put("purchaser", mi.purchaser);
//			obj.put("purchaseMethod", mi.purchaseMethod);
//			obj.put("supplier", mi.supplier);
//			obj.put("supplierContact", mi.supplierContact);
//			obj.put("supplierContactNumber", mi.supplierContactNumber);
//			obj.put("machineType", mi.machineType);
			
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
			if(BusinessHelper.AddMachine(propertyNumber, machineLocation, model, ipAdd, machineUsage, department, responsible,responsibleEmail,responsibleContactNumber, systemInfo, purchaseTime,price,project,comments,moveInTime,purchaser,purchaseMethod,supplier,supplierContact,supplierContactNumber,machineType,userid,responsibleid,propertyName))
			{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","新增成功");
			}
			else{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","新增失败");
			}
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
			 
			if(BusinessHelper.UpdateMachine(id,propertyNumber, machineLocation, model, ipAdd, machineUsage, department, responsible,responsibleEmail,responsibleContactNumber,systemInfo, purchaseTime,price,project,comments,moveInTime,purchaser,purchaseMethod,supplier,supplierContact,supplierContactNumber,machineType,userid,propertyName))
			{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","更新成功");
			}
			else{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","更新失败");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("updateMachine :"+ex.toString());
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
			if(BusinessHelper.DeleteMachine(id))
			{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","删除成功");
			}
			else{
				mv=new ModelAndView("/common/data");
				mv.addObject("data","删除失败");
			}
		}
		catch(Exception ex)
		{
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		
		return mv;
	}
}
