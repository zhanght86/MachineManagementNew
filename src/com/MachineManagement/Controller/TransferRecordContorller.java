package com.MachineManagement.Controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.las.MachineManagement.Bean.Machineinfo;
import com.las.MachineManagement.Bean.Transferrecord;
import com.springframework.orm.ManchineManagementDao;


@Controller 
@Scope("prototype")
public class TransferRecordContorller {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	
	@RequestMapping("transferrecord")
	public ModelAndView TransferRecord(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView("transferRecordContent");
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			 int machineid=Integer.parseInt(request.getParameter("machineid"));
			 
			 Machineinfo machineinfo=new Machineinfo();
			 List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{machineid});
			 if(machineinfoList!=null&&machineinfoList.size()!=0)
			 {
				 machineinfo=machineinfoList.get(0);
			 }
			 else
			 {
			 }
			
 
			 List<Transferrecord> transferRecordList= manchineManagementDao.find("from Transferrecord where machineinfo.id=?",new Object[]{machineid});
			 if(transferRecordList!=null&&transferRecordList.size()!=0)
			 {
			 
			 }
			 else
			 {
				transferRecordList=new ArrayList<Transferrecord>();
				Transferrecord transferrecord=new Transferrecord();
				transferrecord.setCurOwner(machineinfo.getResponsible());
				transferrecord.setCurOwnerEmail(machineinfo.getResponsibleEmail());
				transferrecord.setDepartment(machineinfo.getDepartment());
				transferrecord.setPreOwner(machineinfo.getResponsible());
				transferrecord.setPreOwnerEmail(machineinfo.getResponsibleEmail());
				transferrecord.setState("1");
				transferrecord.setMachineinfo(machineinfo);
				transferrecord.setHappenTime(new Date());
				manchineManagementDao.saveOrUpdate(transferrecord);
				transferRecordList.add(transferrecord);
			 }
			
			 mv.addObject("transferRecordList", transferRecordList);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		return mv;
	}
	
	
	@RequestMapping("addTransferRecord")
	public ModelAndView addTransferRecord(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			mv=new ModelAndView("/common/data");
			
			Transferrecord transferrecord=new Transferrecord();
			 Machineinfo machineinfo=new Machineinfo();
			 List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{Integer.parseInt(request.getParameter("MachineInfoID"))});
			 if(machineinfoList!=null&&machineinfoList.size()!=0)
			 {
				 machineinfo=machineinfoList.get(0);
				 transferrecord.setMachineinfo(machineinfo);
			 }
			 else
			 {
				 transferrecord.setMachineinfo(null);
			 }

			 transferrecord.setState("1");
			 transferrecord.setCurOwner(request.getParameter("CurOwner").toString());
			 transferrecord.setCurOwnerEmail(request.getParameter("CurOwnerEmail"));
			 transferrecord.setDepartment(request.getParameter("Department").toString());
			 transferrecord.setReason(request.getParameter("Reason").toString());
			 transferrecord.setHappenTime(new Date());
			manchineManagementDao.saveOrUpdate(transferrecord);
			mv.addObject("data","增加调转记录成功");
			
 
		}
		catch(Exception ex)
		{
			System.out.println("addTransferRecord : "+ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		return mv;
	}
	

	@RequestMapping("updateTransferRecord")
	public ModelAndView updateTransferRecord(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			mv=new ModelAndView("/common/data");
			

			Transferrecord transferrecord=new Transferrecord();
			 List<Transferrecord> transferRecordList= manchineManagementDao.find("from Transferrecord where  id=?",new Object[]{Integer.parseInt(request.getParameter("id").toString())});
			 if(transferRecordList!=null&&transferRecordList.size()!=0)
			 {
				 transferrecord=transferRecordList.get(0);
			 }
			 else
			 {
				 
			 }

			 Machineinfo machineinfo=new Machineinfo();
			 List<Machineinfo> machineinfoList= manchineManagementDao.find("from Machineinfo where id=?",new Object[]{Integer.parseInt(request.getParameter("MachineInfoID"))});
			 if(machineinfoList!=null&&machineinfoList.size()!=0)
			 {
				 machineinfo=machineinfoList.get(0);
				 transferrecord.setMachineinfo(machineinfo);
			 }
			 else
			 {
				 transferrecord.setMachineinfo(null);
			 }

			 transferrecord.setState("1");
			 transferrecord.setCurOwner(request.getParameter("CurOwner").toString());
			 transferrecord.setCurOwnerEmail(request.getParameter("CurOwnerEmail"));
			 transferrecord.setDepartment(request.getParameter("Department").toString());
			 transferrecord.setReason(request.getParameter("Reason").toString());
			 transferrecord.setPreid(Integer.parseInt(request.getParameter("preid").toString()));
			 transferrecord.setPreOwner(request.getParameter("PreOwner").toString());
			 transferrecord.setPreOwnerEmail(	request.getParameter("PreOwnerEmail").toString());
			 transferrecord.setHappenTime(new Date());
			manchineManagementDao.saveOrUpdate(transferrecord);
			mv.addObject("data","更新调转记录成功");
 

		}
		catch(Exception ex)
		{
			System.out.println("updateTransferRecord : "+ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		return mv;
	}
	
	
 
	@RequestMapping("deleteTransferRecord")
	public ModelAndView deleteTransferRecord(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception{
		ModelAndView mv=new ModelAndView();
		try
		{
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}
			
			mv=new ModelAndView("/common/data");
			
			Transferrecord transferrecord=new Transferrecord();
			transferrecord.setId(Integer.parseInt(request.getParameter("id").toString()));
			 List<Transferrecord> transferRecordList= manchineManagementDao.find("from Transferrecord where id=?",new Object[]{transferrecord.getId()});
			 if(transferRecordList!=null&&transferRecordList.size()!=0)
			 {
				 transferrecord=transferRecordList.get(0);
				 transferrecord.setState("2");
				 manchineManagementDao.saveOrUpdate(transferrecord);
				 mv.addObject("data","删除调转记录成功");
			 }
			 else
			 {
				 mv.addObject("data","删除调转记录失败");
			 }
 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		return mv;
	}
}
