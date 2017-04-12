package MachineManagement.Controller;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import MachineManagement.DataModel.CheckInfoA;
import MachineManagement.DataModel.CheckInfoB;
import MachineManagement.DataModel.CheckRecordA;
import MachineManagement.DataModel.CheckRecordB;
import MachineManagement.DataModel.MachineInfo;
import WordExport.WordTableCheckinfoa;
import WordExport.WordTableCheckinfob;
import WordExport.WordTableMachineInfo1;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.rtf.RtfWriter2;

@Controller
@Scope("prototype")
public class WordExportController {
	
	@RequestMapping("wordExportByMachineId")
	public Object  wordExportByMachineId(HttpServletRequest request,HttpServletResponse response,HttpSession httpSession)throws Exception{

		ModelAndView mv=new ModelAndView();
		if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
		{
			 return new ModelAndView(new RedirectView(""));
		}

		
		   Calendar cal = Calendar.getInstance();
		   int year = cal.get(Calendar.YEAR);
		   int month=cal.get(Calendar.MONTH)+1;
		   int day=cal.get(Calendar.DAY_OF_MONTH);
		    
		    
		     //得到当前web的根路径绝对地址
		    String webRootBasePath=request.getSession().getServletContext().getRealPath("") ;
		   
		    String fileName=Calendar.getInstance().getTimeInMillis()+".doc";
		    String file=webRootBasePath+"/TempDoc/"+fileName;
		    String filedownload = file;
		    String filedisplay = fileName;
		    HashMap<Integer, CheckRecordA> checkRecordAYearList=new HashMap<Integer, CheckRecordA>();
	    	 HashMap<Integer, CheckRecordB> checkRecordBYearList=new HashMap<Integer, CheckRecordB>();

		    int machineid=Integer.parseInt(request.getParameter("machineid"));
		    
		    MachineInfo machineInfo=BusinessHelper.getMachineInfoById(machineid);
		    
		    CheckInfoA checkInfoA=BusinessHelper.getCheckInfoA(machineid, year);
		    CheckInfoB checkInfoB=BusinessHelper.getCheckInfoB(machineid, year);
		    
		    checkRecordAYearList=BusinessHelper.getCheckRecordAByMachineIdAllyear(machineid, year);
		    checkRecordBYearList=BusinessHelper.getCheckRecordBByMachineIdAllyear(machineid, year);
		    

		     
		    Document document = new Document(new RectangleReadOnly(842.0F, 595.0F)); 
		    RtfWriter2.getInstance(document,new FileOutputStream(file));  
		    document.open();  
		    
		    //设置表格大标题
		    Paragraph titleA = new Paragraph("国家科学图书馆"+year+"年服务器系统检查登记表（A）", new Font(Font.TIMES_ROMAN, 14, Font.BOLD, new Color(0, 0, 0)) );  
		    titleA.setAlignment(1);
		    document.add(titleA); 
		    document.add(WordTableCheckinfoa.getTable(checkInfoA,checkRecordAYearList));  

		    document.newPage();
		    document.setPageSize(new RectangleReadOnly(842.0F, 595.0F));
		    //设置表格大标题
		    Paragraph titleB = new Paragraph("国家科学图书馆"+year+"年服务器系统检查登记表（B）", new Font(Font.TIMES_ROMAN, 14, Font.BOLD, new Color(0, 0, 0)) );  
		    titleB.setAlignment(1);
		    document.add(titleB); 
		    document.add(WordTableCheckinfob.getTable(checkInfoB,checkRecordBYearList));  
		    
		    document.close();  
		    
		    response.reset();
		    response.setContentType("application/octet-stream");
		    filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
		    response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
		    File f=new File(file);
		    response.setHeader("Content-Length",String.valueOf(f.length()));
		    
		    OutputStream out = null;
		    FileInputStream in = null;
		    try
		    {
		        out = response.getOutputStream();
		        in = new FileInputStream(filedownload);
		        
		        byte[] b = new byte[1024];
		        int i = 0;
		        while((i = in.read(b)) > 0)
		        {
		        out.write(b, 0, i);
		        }
		        out.flush();
		    }
		    catch(Exception ex)
		    {
		        System.out.println(ex.toString());
		        ex.printStackTrace();
		    }
		    finally
		    {
		        if(in != null)
		        {
		        in = null;
		        }
		        if(out != null)    
		        {
		        out.close();
		        out = null;
		        }
		    }

		    return null;
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
		@RequestMapping("wordExportMachineInfo1")
		public Object wordExportMachineInfo1(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,HttpSession httpSession)throws Exception
		{
			ModelAndView mv=new ModelAndView();
			if(httpSession.isNew()||httpSession.getAttribute("SessionID")==null)
			{
				 return new ModelAndView(new RedirectView(""));
			}

			
				String keyword=request.getParameter("keyword").toString();
				int pageamount=Integer.parseInt(request.getParameter("pageamount").toString());
				int pagecounter=Integer.parseInt(request.getParameter("pagecounter").toString());
				String showall="1";
				String searchcondition=request.getParameter("searchcondition").toString();
				String orderstring=request.getParameter("orderstring").toString();
				int userid=Integer.parseInt(request.getParameter("userid").toString());

			
			    List<Object> result=BusinessHelper.getMachineInfoList(keywordProcess(keyword),pageamount,pagecounter,showall,searchcondition,orderstring,userid);
			    List<MachineInfo> MachineInfoList=new ArrayList<MachineInfo>();
	            if(result!=null)
	            {
	        	  MachineInfoList=(List<MachineInfo>)result.get(1);
	            }

	 		    
	 		     //得到当前web的根路径绝对地址
	 		    String webRootBasePath=request.getSession().getServletContext().getRealPath("") ;
	 		   
	 		    String fileName=Calendar.getInstance().getTimeInMillis()+".doc";
	 		    String file=webRootBasePath+"/TempDoc/"+fileName;
	 		    String filedownload = file;
	 		    String filedisplay = fileName;
	 		    
	 		    Document document = new Document(new RectangleReadOnly(842.0F, 595.0F)); 
	 		    RtfWriter2.getInstance(document,new FileOutputStream(file));  
	 		    document.open();  
	 		    
	 		    //设置表格大标题
	 		    Paragraph title= new Paragraph("中国科学院文献情报中心资产统计表", new Font(Font.TIMES_ROMAN, 16, Font.BOLD, new Color(0, 0, 0)) );  
	 		    title.setAlignment(1);
	 		    document.add(title); 
	 		    document.add(WordTableMachineInfo1.getTable(MachineInfoList));  


	 		    
	 		    document.close();  
	 		    
	 		    response.reset();
	 		    response.setContentType("application/octet-stream");
	 		    filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
	 		    response.addHeader("Content-Disposition","attachment;filename=" + filedisplay);
	 		    File f=new File(file);
	 		    response.setHeader("Content-Length",String.valueOf(f.length()));
	 		    
	 		    OutputStream out = null;
	 		    FileInputStream in = null;
	 		    try
	 		    {
	 		        out = response.getOutputStream();
	 		        in = new FileInputStream(filedownload);
	 		        
	 		        byte[] b = new byte[1024];
	 		        int i = 0;
	 		        while((i = in.read(b)) > 0)
	 		        {
	 		        out.write(b, 0, i);
	 		        }
	 		        out.flush();
	 		    }
	 		    catch(Exception ex)
	 		    {
	 		        System.out.println(ex.toString());
	 		        ex.printStackTrace();
	 		    }
	 		    finally
	 		    {
	 		        if(in != null)
	 		        {
	 		        in = null;
	 		        }
	 		        if(out != null)    
	 		        {
	 		        out.close();
	 		        out = null;
	 		        }
	 		    }
	 		   return null;
	 }

}
