package MachineManagement.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class TransferRecordContorller {
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
			 List<TransferRecord> transferRecordList=new ArrayList<TransferRecord>();
			 transferRecordList=BusinessHelper.getTransferRecordByMachineInfoID(machineid);
			 mv.addObject("transferRecordList", transferRecordList);
			
		}
		catch(Exception ex)
		{
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
			
			TransferRecord transferRecord=new TransferRecord(0,Integer.parseInt(request.getParameter("MachineInfoID").toString()),"","",request.getParameter("CurOwner").toString(),request.getParameter("CurOwnerEmail"),0,request.getParameter("Department").toString(),request.getParameter("Reason").toString(),new Date());

			if(BusinessHelper.addTransferRecord(transferRecord))
			{
				mv.addObject("data","增加调转记录成功");
			}
			else
			{
				mv.addObject("data","增加调转记录失败");
			}
			
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
			
			TransferRecord transferRecord=new TransferRecord(Integer.parseInt(request.getParameter("id").toString()),Integer.parseInt(request.getParameter("MachineInfoID").toString()),request.getParameter("PreOwner").toString(),request.getParameter("PreOwnerEmail").toString(),request.getParameter("CurOwner").toString(),request.getParameter("CurOwnerEmail"),Integer.parseInt(request.getParameter("preid").toString()),request.getParameter("Department").toString(),request.getParameter("Reason").toString(),new Date());

			if(BusinessHelper.updateTransferRecord(transferRecord))
			{
				mv.addObject("data","更新调转记录成功");
			}
			else
			{
				mv.addObject("data","更新调转记录失败");
			}

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
			
			TransferRecord transferRecord=new TransferRecord();
			transferRecord.id=Integer.parseInt(request.getParameter("id").toString());
			if(BusinessHelper.deleteTransferRecord(transferRecord))
			{
				mv.addObject("data","删除调转记录成功");
			}
			else
			{
				mv.addObject("data","删除调转记录失败");
			}

		}
		catch(Exception ex)
		{
			System.out.println("updateTransferRecord : "+ex.toString());
			mv=new ModelAndView("/common/data");
			mv.addObject("data","ERROR:"+ex.toString());
		}
		return mv;
	}
}
