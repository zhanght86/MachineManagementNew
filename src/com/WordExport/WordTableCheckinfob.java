package com.WordExport;

import java.awt.Color;
import java.util.HashMap;

import com.las.MachineManagement.Bean.Checkinfob;
import com.las.MachineManagement.Bean.Checkrecordb;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

public class WordTableCheckinfob {
	
	public static Table getTable(Checkinfob checkInfoB, HashMap<Integer, Checkrecordb> checkRecordBYearList)
	{
		Table table;
		  try
	{
	    /*创建表格*/ 
	    table = new Table(14);   
	    table.setBorderWidth(1);  
	    table.setBorderColor(Color.black);  
	    table.setPadding(0);  
	    table.setSpacing(0);  
	    table.setAlignment(Element.ALIGN_CENTER);
	    table.setWidth(100);
	    table.setWidths(new float[]{2,14,7,7,7,7,7,7,7,7,7,7,7,7});
	    
	    //表格单元格
	    Cell cell;
	    
	 
	    
	    //流水号
	    cell= new Cell(new Paragraph("流水号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHeader(true);  
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setColspan(2); 
	    table.addCell(cell);  


	    //流水号 内容
	    cell= new Cell(new Paragraph(checkInfoB.getFlowNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    table.addCell(cell); 
	    
	    //责任部门
	    cell= new Cell(new Paragraph("责任部门", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHeader(true);  
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell);  
	    
	    //责任部门 内容
	    cell= new Cell(new Paragraph(checkInfoB.getResponsibilityDepartment(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(2); 
	    table.addCell(cell);  
	    
	    //机器位置
	    cell= new Cell(new Paragraph("机器位置", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    table.addCell(cell);  
	    
	    //机器位置 内容
	    cell= new Cell(new Paragraph(checkInfoB.getMachineLocation(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  


	    //IP地址
	    cell= new Cell(new Paragraph("IP地址", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    table.addCell(cell); 
	    
	    //IP地址 内容
	    cell= new Cell(new Paragraph(checkInfoB.getIpadd(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  

	   
	    /*第二行*/

	    //资产号
	    cell= new Cell(new Paragraph("资产号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    cell.setColspan(2); 
	    table.addCell(cell);  

	    //资产号 内容
	    cell= new Cell(new Paragraph(checkInfoB.getPropertyNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(4); 
	    table.addCell(cell); 
	    
	    //机器型号
	    cell= new Cell(new Paragraph("机器型号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    table.addCell(cell);  
	    
	    //机器型号 内容
	    cell= new Cell(new Paragraph(checkInfoB.getModel(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  
	    
	    //用途
	    cell= new Cell(new Paragraph("用途", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    table.addCell(cell);  
	    
	    //用途  内容
	    cell= new Cell(new Paragraph(checkInfoB.getMachineUsage(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  

	    /*第三行*/
	    //序列号
	    cell= new Cell(new Paragraph("序列号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    cell.setColspan(2); 
	    table.addCell(cell);  

	    //序列号 内容
	    cell= new Cell(new Paragraph(checkInfoB.getSerialNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(4); 
	    table.addCell(cell); 
	    
	    //系统信息
	    cell= new Cell(new Paragraph("系统信息", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    cell.setColspan(1); 
	    table.addCell(cell);  
	    
	    //系统信息 内容
	    cell= new Cell(new Paragraph(checkInfoB.getSystemInfo(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  
	    
	    //维护人员
	    cell= new Cell(new Paragraph("维护人员", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    table.addCell(cell);  
	    
	    //维护人员  内容
	    cell= new Cell(new Paragraph(checkInfoB.getMantainceStaff(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setColspan(3); 
	    table.addCell(cell);  

	    
	    /*第四行*/
	    //系统检查
	    cell= new Cell(new Paragraph("系统检查", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setHeader(true);  
	    cell.setColspan(2); 
	    table.addCell(cell);  


	    //1月 
	    cell= new Cell(new Paragraph("1月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //2月 
	    cell= new Cell(new Paragraph("2月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell);
	    //3月 
	    cell= new Cell(new Paragraph("3月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER); 
	    table.addCell(cell); 
	    //4月 
	    cell= new Cell(new Paragraph("4月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //5月 
	    cell= new Cell(new Paragraph("5月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //6月 
	    cell= new Cell(new Paragraph("6月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //7月 
	    cell= new Cell(new Paragraph("7月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //8月 
	    cell= new Cell(new Paragraph("8月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //9月 
	    cell= new Cell(new Paragraph("9月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //10月 
	    cell= new Cell(new Paragraph("10月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //11月 
	    cell= new Cell(new Paragraph("11月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    //12月 
	    cell= new Cell(new Paragraph("12月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell); 
	    
	    
	    /*第5行*/
	    //系 统 管 理 人 填 写
	    cell= new Cell(new Paragraph("系 统 管 理 人 填 写 ", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setVerticalAlignment(1);
	    cell.setHeader(true);
	    cell.setRowspan(7); 
	    table.addCell(cell);  


	    
	    //补丁更新
	    cell= new Cell(new Paragraph("网络备份（系统数据）", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    
	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getNetworkBackup(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }

	  
	    /*第6行*/

	    //光盘备份 （系统数据）
	    cell= new Cell(new Paragraph("光盘备份（系统数据）", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getHarddriverBackup(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	    
	    
	    /*第7行*/

	    //日志上载分析
	    cell= new Cell(new Paragraph("日志上载分析", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getLogUploadAnalysis(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	    
	    /*第8行*/

	    //防火墙检测
	    cell= new Cell(new Paragraph("防火墙检测", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    
	    
	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getFirewallCheck(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	    
	    /*第9行*/

	    //本月流量
	    cell= new Cell(new Paragraph("本月流量", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getMonthlyFloatAmount(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	    /*第10行*/

	    //停止服务情况
	    cell= new Cell(new Paragraph("停止服务情况", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getServerStoppedInfo(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	    /*第11行*/

	    //签字
	    cell= new Cell(new Paragraph("签字", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	    cell.setHeader(true);  
	    table.addCell(cell);  

	    for(int i=0;i<12;i++)
	    {
	        // 每月信息
	        cell= new Cell(new Paragraph(checkRecordBYearList.get(i+1)==null?"":checkRecordBYearList.get(i+1).getSignature(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	        table.addCell(cell); 
	    }
	   
	    
	    /*第26行*/

	    //备注
	    cell= new Cell(new Paragraph("备注", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
	    cell.setHorizontalAlignment(1);
	    cell.setVerticalAlignment(1);
	    cell.setHeader(true);  
	    cell.setRowspan(14);
	    cell.setColspan(2); 
	    table.addCell(cell);  

	    
        // 备注内容
        cell= new Cell(new Paragraph(checkInfoB.getComments(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(14);
	    cell.setColspan(12); 
        table.addCell(cell); 
	    

	    
		  }
		  catch(Exception ex)
		  {
			  System.out.println(ex.toString());
			  table=null;
		  }
	    return table;
	}


}
