package com.WordExport;

import java.awt.Color;
import java.util.HashMap;

import com.las.MachineManagement.Bean.Checkinfoa;
import com.las.MachineManagement.Bean.Checkrecorda;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

public class WordTableCheckinfoa {
	
	
public static Table getTable(Checkinfoa checkInfoA, HashMap<Integer, Checkrecorda> checkRecordAYearList )
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
    
    //new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))
    
    //流水号
    cell= new Cell(new Paragraph("流水号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(2); 
    table.addCell(cell);  
    //table.endHeaders();

    //流水号 内容
    table.addCell(new Paragraph(checkInfoA.getFlowNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0))));
    
    //责任部门
    cell= new Cell(new Paragraph("责任部门", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //责任部门 内容
    cell= new Cell(new Paragraph(checkInfoA.getResponsibilityDepartment(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    //cell.setHeader(true);  
    
    cell.setColspan(2); 
    table.addCell(cell);  
    
    //机器位置
    cell= new Cell(new Paragraph("机器位置", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(1); 
    table.addCell(cell);  
    //table.endHeaders();
    
    //机器位置 内容
    cell= new Cell(new Paragraph(checkInfoA.getMachineLocation(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    //cell.setHeader(true);  
    
    cell.setColspan(3); 
    table.addCell(cell);  
    //table.endHeaders();
    
    
    //IP地址
    cell= new Cell(new Paragraph("IP地址", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(1); 
    table.addCell(cell);  
    //table.endHeaders();
    
    //IP地址 内容
    cell= new Cell(new Paragraph(checkInfoA.getIpadd(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    //cell.setHeader(true);  
    
    cell.setColspan(3); 
    table.addCell(cell);  
    //table.endHeaders();
    
   
    /*第二行*/
    
    
    //资产号
    cell= new Cell(new Paragraph("资产号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(2); 
    table.addCell(cell);  
    //table.endHeaders();

    //资产号 内容
    cell= new Cell(new Paragraph(checkInfoA.getPropertyNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    //cell.setHeader(true);  
  
    cell.setColspan(4); 
    table.addCell(cell); 
    
    //机器型号
    cell= new Cell(new Paragraph("机器型号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //机器型号 内容
    cell= new Cell(new Paragraph(checkInfoA.getModel(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    //cell.setHeader(true);  
    
    cell.setColspan(3); 
    table.addCell(cell);  
    
    //用途
    cell= new Cell(new Paragraph("用途", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //用途  内容
    cell= new Cell(new Paragraph(checkInfoA.getMachineUsage(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
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
    cell= new Cell(new Paragraph(checkInfoA.getSerialNumber(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setColspan(4); 
    table.addCell(cell); 
    
    //系统信息
    cell= new Cell(new Paragraph("系统信息", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //系统信息 内容
    cell= new Cell(new Paragraph(checkInfoA.getSystemInfo(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setColspan(3); 
    table.addCell(cell);  
    
    //维护人员
    cell= new Cell(new Paragraph("维护人员", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //维护人员  内容
    cell= new Cell(new Paragraph(checkInfoA.getMantainceStaff(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
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
    cell.setColspan(1); 
    table.addCell(cell); 
    //2月 
    cell= new Cell(new Paragraph("2月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell);
    //3月 
    cell= new Cell(new Paragraph("3月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //4月 
    cell= new Cell(new Paragraph("4月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //5月 
    cell= new Cell(new Paragraph("5月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //6月 
    cell= new Cell(new Paragraph("6月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //7月 
    cell= new Cell(new Paragraph("7月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //8月 
    cell= new Cell(new Paragraph("8月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //9月 
    cell= new Cell(new Paragraph("9月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //10月 
    cell= new Cell(new Paragraph("10月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //11月 
    cell= new Cell(new Paragraph("11月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    //12月 
    cell= new Cell(new Paragraph("12月", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setColspan(1); 
    table.addCell(cell); 
    
    
    /*第5行*/
    //应用系统 责任人填写
    cell= new Cell(new Paragraph("应 用 系 统 责 任 人 填 写 ", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(1);
    cell.setVerticalAlignment(1);
    cell.setHeader(true);  
    cell.setRowspan(20); 
    table.addCell(cell);  


    
    //补丁更新
    cell= new Cell(new Paragraph("补丁更新", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  

    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(1);
        table.addCell(cell); 
    }

  
    /*第6行*/

    //操作系统
    cell= new Cell(new Paragraph("操作系统", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":checkRecordAYearList.get(i+1).getOs(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(1);
        table.addCell(cell); 
    }
    
    
    /*第7行*/

    //应用程序
    cell= new Cell(new Paragraph("应用程序", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getApplication().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    
    /*第8行*/

    //数据库
    cell= new Cell(new Paragraph("数据库", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  

    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getDataBaseCheck().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    
    /*第9行*/

    //病毒查杀
    cell= new Cell(new Paragraph("病毒查杀", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  

    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第10行*/

    //360安全卫士
    cell= new Cell(new Paragraph("360安全卫士", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell); 
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getCheck360().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第11行*/

    //杀毒软件
    cell= new Cell(new Paragraph("杀毒软件", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getAntivirus().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第12行*/

    //用户账号
    cell= new Cell(new Paragraph("用户账号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  

    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第13行*/

    //正常使用账号
    cell= new Cell(new Paragraph("正常使用账号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":checkRecordAYearList.get(i+1).getAccountNormal(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第14行*/

    //非正常使用账号
    cell= new Cell(new Paragraph("非正常使用账号", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":checkRecordAYearList.get(i+1).getAccountAbnormal(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第15行*/

    //日志情况
    cell= new Cell(new Paragraph("日志情况", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第16行*/

    //事件日志
    cell= new Cell(new Paragraph("事件日志", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getEventLog().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第17行*/

    //web日志
    cell= new Cell(new Paragraph("web日志", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getWebLog().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第18行*/

    //数据库日志
    cell= new Cell(new Paragraph("数据库日志", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getWebLog().equals("1")?"√":""), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第19行*/

    //硬盘情况
    cell= new Cell(new Paragraph("硬盘情况", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第20行*/

    //磁盘空间情况
    cell= new Cell(new Paragraph("磁盘空间情况", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true); 
    table.addCell(cell);  

    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph(checkRecordAYearList.get(i+1)==null?"":(checkRecordAYearList.get(i+1).getHardDriverUsage()), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell); 
    }
    /*第21行*/

    //数据备份
    cell= new Cell(new Paragraph("数据备份", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    cell.setHeader(true);  
    table.addCell(cell);  
  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(1);
        table.addCell(cell); 
    }
    
    /*第22行*/

    //备份内容
    cell= new Cell(new Paragraph("备份内容", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //备份内容 内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupContent(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(4); 
    table.addCell(cell);  

    
    //变更1
    cell= new Cell(new Paragraph("变更1", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更1 内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupContentChange1(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  
    
    
    //变更2
    cell= new Cell(new Paragraph("变更2", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更2 内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupContentChange2(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  
    
    /*第23行*/

    //文件目录
    cell= new Cell(new Paragraph("文件目录", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  

    
    //文件目录 内容
    cell= new Cell(new Paragraph(checkInfoA.getFileDirectory(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(4); 
    table.addCell(cell);  
    
    //变更1
    cell= new Cell(new Paragraph("变更1", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更1 内容
    cell= new Cell(new Paragraph(checkInfoA.getFileDirectoryChange1(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  
    
    
    //变更2
    cell= new Cell(new Paragraph("变更2", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更2 内容
    cell= new Cell(new Paragraph(checkInfoA.getFileDirectoryChange2(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  
    
    /*第24行*/

    //备份周期
    cell= new Cell(new Paragraph("备份周期", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cell.setHeader(true);  
    table.addCell(cell);  

    //备份周期  内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupPeriod(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(4); 
    table.addCell(cell);  
    
    //变更1
    cell= new Cell(new Paragraph("变更1", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更1 内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupPeriodChange1(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  

    
    
    //变更2
    cell= new Cell(new Paragraph("变更2", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    table.addCell(cell);  
    
    //变更2 内容
    cell= new Cell(new Paragraph(checkInfoA.getBackupPeriodChange2(), new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(3); 
    table.addCell(cell);  

    
    /*第25行*/

    //系统责任人签字
    cell= new Cell(new Paragraph("系统责任人签字", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(2); 
    table.addCell(cell);  

    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(1);
        table.addCell(cell); 
    }
    
    /*第26行*/

    //系统管理员确认
    cell= new Cell(new Paragraph("系统管理员确认", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
    cell.setHeader(true);  
    cell.setColspan(2); 
    table.addCell(cell);  
    
    
    for(int i=0;i<12;i++)
    {
        // 每月信息
        cell= new Cell(new Paragraph("", new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setRowspan(1);
        table.addCell(cell); 
    }
    

	  }
	  catch(Exception ex)
	  {
		  System.out.println(ex.toString());
		  table=null;
	  }
    return table;
}

}
