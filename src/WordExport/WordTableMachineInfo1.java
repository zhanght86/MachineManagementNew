package WordExport;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import com.las.MachineManagement.Bean.Machineinfo;
import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

public class WordTableMachineInfo1 {
	
	
public static Table getTable(List<Machineinfo> machneInfoList)
{
	Table table;
	BigDecimal total=new BigDecimal("0");
	  try
	  {
    /*创建表格*/ 
    table = new Table(9);   
    table.setBorderWidth(1);  
    table.setBorderColor(Color.black);  
    table.setPadding(0);  
    table.setSpacing(0);  
    table.setWidth(100);
    table.setWidths(new float[]{6,12,10,14,15,15,11,9,8});
    //表格单元格
    Cell cell;
    
    
    //序号
    cell= new Cell(new Paragraph("序号", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //资产号
    cell= new Cell(new Paragraph("资产号", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //资产类型
    cell= new Cell(new Paragraph("资产类型", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //机器位置
    cell= new Cell(new Paragraph("机器位置", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    
    //IP
    cell= new Cell(new Paragraph("IP", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //机型
    cell= new Cell(new Paragraph("机型", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //用途
    cell= new Cell(new Paragraph("用途", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    
    //部门
    cell= new Cell(new Paragraph("部门", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    //责任人
    cell= new Cell(new Paragraph("责任人", new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setHeader(true);  
    cell.setColspan(1); 
    table.addCell(cell);  
    
    

    
    
    for (int i=0;i<machneInfoList.size();i++)
    {
    	//序号内容
        cell= new Cell(new Paragraph(String.valueOf(i+1) , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);  
        
        //资产号
        cell= new Cell(new Paragraph(machneInfoList.get(i).getPropertyNumber() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);  
        
    	//资产类型
        String typeNmae="";
        if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("1"))
        {
        	typeNmae="电脑";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("2"))
        {
        	typeNmae="服务器";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("3"))
        {
        	typeNmae="笔记本";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("4"))
        {
        	typeNmae="软件";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("5"))
        {
        	typeNmae="硬件";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("6"))
        {
        	typeNmae="网络设备";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("7"))
        {
        	typeNmae="家具";
        }
        else if(machneInfoList.get(i).getMachineType().equalsIgnoreCase("8"))
        {
        	typeNmae="打印机";
        }
        cell= new Cell(new Paragraph(String.valueOf(typeNmae) , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);  
        
        
        //机器位置
        cell= new Cell(new Paragraph(machneInfoList.get(i).getMachineLocation() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell);  
        
        //ip
        cell= new Cell(new Paragraph(machneInfoList.get(i).getIpadd() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell);  
        
        //机型
        cell= new Cell(new Paragraph(machneInfoList.get(i).getModel() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell);  
        
        //用途
        cell= new Cell(new Paragraph(machneInfoList.get(i).getMachineUsage() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        table.addCell(cell);  
        
        //部门
        cell= new Cell(new Paragraph(machneInfoList.get(i).getDepartment() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);  
        
        //责任人
        cell= new Cell(new Paragraph(machneInfoList.get(i).getResponsible() , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);  
        
        //价格
//        cell= new Cell(new Paragraph(machneInfoList.get(i).price , new Font(Font.NORMAL,11, Font.NORMAL, new Color(0, 0, 0)))); 
//        table.addCell(cell);
//        if(!machneInfoList.get(i).price.trim().equals("")&&!machneInfoList.get(i).price.trim().equals(null))
//        {   
//        	total=total.add(new BigDecimal(machneInfoList.get(i).price.trim()));
//        }
    }
    
    
//    Table t=new Table(1);
//    t.setAlignment(0);
//    t.setWidth(99);
//
//    t.setAutoFillEmptyCells(true);
//    t.setBorder(1);
//    t.setPadding(0);
//    Cell c1=new Cell();
//    t.addCell(c1);
    
//    cell= new Cell(new Paragraph("总计" , new Font(Font.NORMAL,14, Font.BOLD, new Color(0, 0, 0)))); 
//    cell.setColspan(1);
//    table.addCell(cell);  

//    total.setScale(2, BigDecimal.ROUND_HALF_UP);
//    cell= new Cell(new Paragraph(total.toString(), new Font(Font.NORMAL,14, Font.NORMAL, new Color(0, 0, 0)))); 
//    cell.setColspan(6);
//    table.addCell(cell);  
	  }
	  catch(Exception ex)
	  {
		  System.out.println(ex.toString());
		  table=null;
	  }
    return table;
}

}
