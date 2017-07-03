package MachineManagement.Common;

import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.las.MachineManagement.Bean.*;
import com.springframework.orm.ManchineManagementDao;
 
/**
 * @author chen zijun
 *
 */

@Component
public class CommonDataHelper {
	
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	
	/**
	 * 是否是管理员检查
	 * @param userid
	 * @return
	 */
	public boolean isadmin(int userid)
	{
	   	boolean check=false;
    	try
    	{
    		List<UsersUserroleR> usersUserroleRList=manchineManagementDao.find("from UsersUserroleR where userid=? and roleid=?",new Object[]{userid,0});
    		if(usersUserroleRList!=null&&usersUserroleRList.size()!=0)
    		{
    			check=true;
    		}
    		else
    		{
    			check=false;
    		}
    	  
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    		System.out.print(ex.toString());
    	}
    	return check;
	}
	
	
    //通过设备基本信息实例和制定的年月，检查记录是否填写完整
    public  String CheckCheckRecord(Machineinfo machineInfo,int year,int month)
    {
    	String result="";
    	String checkResultA="";
    	String checkResultB="";
    	try
    	{
    		checkResultA=getCheckRecordACheckResult(machineInfo,year,month);

    		checkResultB=getCheckRecordBCheckResult(machineInfo,year,month);
    		
       	     if(!checkResultA.equals("")||!checkResultB.equals(""))
       	     {
       	    	Calendar cal=Calendar.getInstance();
       	         Date d=cal.getTime();
       	    	 result="<p>您好 ： </p>"+"<br>"+"<p>您所管理的设备 ："+machineInfo.getPropertyNumber().toString()+"，"+machineInfo.getMachineUsage()+"， IP地址为： "+machineInfo.getIpadd()+"，检查表未填写完整，详情如下： </p>"+"<p>"+checkResultA+"</p>"+"<p>"+checkResultB+"</p>"+"<p>请您务必在本月12日之前填写完整</p>"+"<br>"+"<p>谢谢</p>"+"<p>中国科学院文献情报中心资产管理系统</p>"+"<p>"+d.toLocaleString()+"</p>";
       	        // result="<p>"+"\r\n"+"资产号： "+"\r\n"+"</p>"+checkResultA+"\r\n"+checkResultB+"\r\n"+"<p>"+"中国科学院文献情报中心      "+d.toLocaleString()+"\r\n"+"</p>";
       	     }


    	}
    	catch(Exception ex)
    	{
    		System.out.println("BusinessHelper.CheckCheckRecord : "+ex.toString());
    		result="";
    	}
    	return result;
    }
    
    /**
     * 设备检查表A是否填写完整
     * @param machineInfo
     * @param year
     * @param month
     * @return
     */
    public   String getCheckRecordACheckResult(Machineinfo machineInfo,int year,int month)
    {
    	String checkResultA="";

      	try
    	{
      		List<Checkrecorda> checkrecordaList=manchineManagementDao.find("from Checkrecorda wher checkinfoa.machineinfo.id=? and checkinfoa.year=? and monthNumber=?",new Object[]{machineInfo.getId(),String.valueOf(year),month});
      		Checkrecorda checkrecorda=new Checkrecorda();
      		if(checkrecordaList!=null&&checkrecordaList.size()!=0)
      		{
      			checkrecorda=checkrecordaList.get(0);
      		}
      		
      		if(checkrecorda.getOs().trim().equalsIgnoreCase(""))
      		{
      			checkResultA+="操作系统;";
      		}
      		
//      		if(checkrecorda.getApplication().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="应用程序";
//      		}
      		
//      		if(checkrecorda.getDataBaseCheck().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="数据库;";
//      		}
      		
//      		if(checkrecorda.getOs().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="操作系统;";
//      		}
      		
//      		if(checkrecorda.getCheck360().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="360安全卫士;";
//      		}
      		
      		if(checkrecorda.getAccountNormal().trim().equalsIgnoreCase(""))
      		{
      			checkResultA+="是否正常使用账号;";
      		}
      		
//      		if(checkrecorda.getAccountAbnormal().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="非正常使用账号;";
//      		}
      		
      		if(checkrecorda.getEventLog().trim().equalsIgnoreCase(""))
      		{
      			checkResultA+="事件日志;";
      		}
      		
//      		if(checkrecorda.getWebLog().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="web日志;";
//      		}
      		
      		if(checkrecorda.getOsresponsibleSingnature().trim().equalsIgnoreCase(""))
      		{
      			checkResultA+="系统责任人签字;";
      		}
      		
//      		if(checkrecorda.getOsadminsitratorSignature().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultA+="系统管理员签字;";
//      		}
      	  
      	  
 
		     if(!checkResultA.equals(""))
		     {
		    	checkResultA="<p>"+year+"年"+month+"月"+",检查表(A),有如下项没有填写完整 : "+"\r\n"+checkResultA+"\r\n"+"</p>";
		     }
  	     
  	     
    	}
    	catch(Exception ex)
    	{
    		System.out.println("BusinessHelper.getCheckRecordACheckResult : "+ex.toString());
    		checkResultA="";
    	}
  	     return checkResultA;
    }
    
    
    /**
     * 设备检查表B是否填写完整
     * @param machineInfo
     * @param year
     * @param month
     * @return
     */
    public   String getCheckRecordBCheckResult(Machineinfo machineInfo,int year,int month)
    {
    	String checkResultB="";

      	try
    	{

      		List<Checkrecordb> checkrecordbList=manchineManagementDao.find("from Checkrecordb wher checkinfoa.machineinfo.id=? and checkinfoa.year=? and monthNumber=?",new Object[]{machineInfo.getId(),String.valueOf(year),month});
      		Checkrecordb checkrecordb=new Checkrecordb();
      		if(checkrecordbList!=null&&checkrecordbList.size()!=0)
      		{
      			checkrecordb=checkrecordbList.get(0);
      		}
      		
//      		if(checkrecordb.getNetworkBackup().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="网络备份（系统数据）;";
//      		}
//      		
//     		if(checkrecordb.getHarddriverBackup().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="光盘备份（系统数据）;";
//      		}
//     		
//     		if(checkrecordb.getLogUploadAnalysis().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="日志上载分析;";
//      		}
//     		
//     		if(checkrecordb.getFirewallCheck().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="防火墙检查;";
//      		}
//     		
//     		if(checkrecordb.getMonthlyFloatAmount().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="本月流量;";
//      		}
//     		
//     		if(checkrecordb.getServerStoppedInfo().trim().equalsIgnoreCase(""))
//      		{
//      			checkResultB+="停止服务情况;";
//      		}
     		
       		if(checkrecordb.getSignature().trim().equalsIgnoreCase(""))
      		{
      			checkResultB+="签字;";
      		}
 
      	  
  	     if(!checkResultB.equals(""))
  	     {
  	    	checkResultB="<p>"+year+"年"+month+"月"+",检查表(B),有如下项没有填写完整 : "+"\r\n"+checkResultB+"\r\n"+"</p>";
  	     }
    	}
    	catch(Exception ex)
    	{
    		System.out.println("BusinessHelper.getCheckRecordBCheckResult : "+ex.toString());
    		checkResultB="";
    	}
  	     return checkResultB;
    }
    
    
    
}

