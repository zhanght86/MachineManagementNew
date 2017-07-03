//package com.MachineManagement.DataBaseHelper;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import com.Email.EmailInfo;
//import com.MachineManagement.Controller.CheckinfoHistory;
//import com.Support.Formatter;
//import com.Support.HASH;
//import com.las.MachineManagement.Bean.Checkinfoa;
//import com.las.MachineManagement.Bean.Checkinfob;
//import com.las.MachineManagement.Bean.Machineinfo;
//import com.las.MachineManagement.Bean.Transferrecord;
//
//
//public class BusinessHelperOld {
//
//	private static Database db=new Database();
//	public static Connection con=db.getConnection();
//	public static CallableStatement cs=db.getCallableStatement();
//	public static Statement statement=db.getStatement();
//
//
//	
//    //登陆
//    public static String Login(String username,String password)
//    {
//    	String returnValue="";
//
//    	try
//    	{
//
//		    password =(HASH.HashToHEX(password, "SHA-1"));
//
//    	  cs=con.prepareCall("call login(?,?,?);");
//    	  cs.setString(1, username);
//    	  cs.setString(2, password);
//    	  cs.registerOutParameter(3, Types.VARCHAR);
//    	  cs.execute();
//    	  returnValue=cs.getString(3);
//
//    	}catch(Exception ex)
//    	{
//    		System.out.print(ex.toString());
//    		returnValue="false";
//    	}
//    	return returnValue;
//    }
//
//    
//    
//    public static Boolean isadmin(int userid)
//    {
//    	boolean check=false;
//    	String returnValue="";
//
//    	try
//    	{
//    	  cs=con.prepareCall("call isadmin(?,?);");
//    	  cs.setInt(1, userid);
//
//    	  cs.registerOutParameter(2, Types.VARCHAR);
//    	  cs.execute();
//    	  
//    	  returnValue=cs.getString(2);
//          
//    	  if(returnValue.equals("fasle"))
//    	  {
//    		  check=false;
//    	  }
//    	  else if(returnValue.equals("true"))
//    	  {
//    		  check=true;
//    	  }
//    	  
//    	}catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    		System.out.print(ex.toString());
//    	}
//    	return check;
//    }
//    
//    
//    public static String getUserRightByUserId(int userid)
//	{
//
//		String userRighrStr=" inner join (select  users_machineinforesponsible_r.machineinfoid as machineinfoid from users_machineinforesponsible_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
//		try
//		{
//			if(isadmin(userid))
//			{
//				userRighrStr="  ";
//			}
//			else
//			{
//				 userRighrStr=" inner join (select  users_machineinforesponsible_r.machineinfoid as machineinfoid from users_machineinforesponsible_r where userid="+userid+") righttable on righttable.machineinfoid=machineinfo.id ";
//			}
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//			System.out.println(ex.toString());
//			 userRighrStr=" inner join (select  users_machineinforesponsible_r.machineinfoid as machineinfoid from users_machineinforesponsible_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
//			
//		}
//		
//		return userRighrStr;
//	}
//	
//    
//    //查询设备基本信息
//    public static List<Object>  getMachineInfoList(String keyword,int pageamount,int pagecounter,String showall,String searchcondition,String orderstring,int userid)
//    {
//    	List<Object> result=new ArrayList<Object>();
//    	String userRight=getUserRightByUserId(userid);
//    	List<Machineinfo> machineInfoList=new ArrayList<Machineinfo>();
//    	ResultSet rs=null;
//    	String returnValue="";
//    	try
//    	{
//    		cs=con.prepareCall("call machineinfo_search(?,?,?,?,?,?,?,?);");
//	 		cs.setString(1,keyword);
//	 		cs.setInt(2, pageamount);
//	 		cs.setInt(3, pagecounter);
//	 		cs.setString(4, showall);
//	 		cs.setString(5, searchcondition);
//	 		cs.setString(6, orderstring);
//	 		cs.setString(7, userRight);
//
//	    	  cs.registerOutParameter(8, Types.VARCHAR);
//	    	  cs.execute();
//	    	  
//	    	  returnValue=cs.getString(8);
//	    	  result.add(0, returnValue);
//	    	  
//    		  rs=cs.getResultSet();
//    			
//    			if(rs!=null)
//    			{
//    			  int rowindex=1;
//    			  while(rs.next())
//    			  {
//    			    Machineinfo mi=new Machineinfo(Integer.parseInt(rs.getString(1).toString()),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9).toString(),rs.getString(10).toString(),rs.getString(11).toString(),rs.getTimestamp(12),rs.getString(13),rs.getString(14),rs.getString(15).toString(),rs.getString(16).toString(),rs.getTimestamp(17),rs.getTimestamp(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(26));
//    			    mi.setCheckStateA(rs.getString("checkstatea"));
//    			    mi.setCheckStateB(rs.getString("checkstateb"));
//    			    mi.setDisplayNumber(((pagecounter-1)*pageamount)+rowindex);
//   			     mi.responsible=rs.getString("r_name");
//   			     mi.department=rs.getString("r_department");
//   			     mi.responsibleEmail=rs.getString("r_email");
//   			     mi.responsibleContactNumber=rs.getString("r_contactnumber");
//   			     mi.responsibleUserId=Integer.parseInt(rs.getString("r_id"));
//   			     mi.machineType=rs.getString("MachineType");
//    			    machineInfoList.add(mi);
//    			    rowindex++;
//    			  }
//    			  result.add(1, machineInfoList);
//    			  
//    			
//    		}
//    		else{}
//    	}
//    	catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    	 System.out.println("getMachineInfoList: "+ex.toString());
//    	}
//    	return result;
//    }
//    
//    //通过ID查找machineinfo
//    public static Machineinfo getMachineInfoById(int id)
//    {
//    	Machineinfo mi=null;
//    	ResultSet rs=null;
//    	try
//    	{
//
//    		cs=con.prepareCall("call machineinfo_search_by_id(?);");
//    		cs.setInt(1,id);
//    		
//    		if(cs.execute())
//    		{
//    			rs=cs.getResultSet();
//    			
//    
//    			  if(rs.next())
//    			  {
//    			     mi=new Machineinfo(Integer.parseInt(rs.getString(1).toString()),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9),rs.getString(10).toString(),rs.getString(11).toString(),rs.getTimestamp(12),rs.getString(13),rs.getString(14),rs.getString(15).toString(),rs.getString(16).toString(),rs.getTimestamp(17),rs.getTimestamp(18),rs.getString(19),rs.getString(20),rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(26));
//    			     mi.setCheckStateA(rs.getString(27));
//    			     mi.setCheckStateB(rs.getString(28));
//    			     // 使用users表中的数据替代machineinfo中的责任人相关数据
//    			     mi.responsible=rs.getString("r_name");
//    			     mi.department=rs.getString("r_department");
//    			     mi.responsibleEmail=rs.getString("r_email");
//    			     mi.responsibleContactNumber=rs.getString("r_contactnumber");
//    			     mi.responsibleUserId=Integer.parseInt(rs.getString("r_id"));
//    			     mi.machineType=rs.getString("MachineType");
//
//    			  }
//    			  else
//    			  {
//    				  mi=null;
//    			  }
//
//    		}
//    		else
//    		{}
//    	}
//    	catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    		System.out.println(ex.toString());
//    		mi=null;
//    	}
//
//    	return mi;
//    }
//    
//    //新增机器
//    public static Boolean AddMachine(String propertyNumber,String machineLocation,String model,String ipAdd,String machineUsage,String department,String responsible,String responsibleEmail,String responsibleContactNumber,String machineOwner,Date purchaseTime,String price,String project,String comments,Date moveInTime,String Purchaser,String PurchaseMethod, String Supplier, String SupplierContact, String SupplierContactNumber,String MachineType,int userid,int responsibleid,String PropertyName)
//    {
//    	Boolean result=false;
//    	String returnValue="";
//    	
//    	try
//    	{
//    	  cs=con.prepareCall("call machineinfo_insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//    	  cs.setString(1, propertyNumber);
//    	  cs.setString(2, machineLocation);
//    	  cs.setString(3, model);
//    	  cs.setString(4, ipAdd);
//    	  cs.setString(5, machineUsage);
//    	  cs.setString(6, department);
//    	  cs.setString(7, responsible);
//    	  cs.setString(8, responsibleEmail);
//    	  cs.setString(9, responsibleContactNumber);
//    	  cs.setString(10, machineOwner);
//    	  cs.setTimestamp(11, new java.sql.Timestamp(purchaseTime.getTime())); 
//    	  cs.setString(12, price);
//    	  cs.setString(13, project);
//    	  cs.setString(14, comments);
//    	  cs.setTimestamp(15,new java.sql.Timestamp( moveInTime.getTime()));
//    	  cs.setString(16, Purchaser);
//    	  cs.setString(17, PurchaseMethod);
//    	  cs.setString(18, Supplier);
//    	  cs.setString(19, SupplierContact);
//    	  cs.setString(20, SupplierContactNumber);
//    	  cs.setString(21, MachineType);
//          cs.setInt(22, userid);
//          cs.setInt(23, responsibleid);
//          cs.setString(24, PropertyName);
//          
//    	  cs.registerOutParameter(25, Types.VARCHAR);
//    	  cs.execute();
//    	  returnValue=cs.getString(25);
//    
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}catch(Exception ex)
//    	{
//    		System.out.print(ex.toString());
//    		result=false;
//    	}
//    	return result;
//    }
//    
//    //更新机器
//    public static Boolean UpdateMachine(int id,String propertyNumber,String machineLocation,String model,String ipAdd,String machineUsage,String department,String responsible,String responsibleEmail,String responsibleContactNumber,String machineOwner,Date purchaseTime,String price,String project,String comments,Date moveInTime,String Purchaser,String PurchaseMethod, String Supplier, String SupplierContact, String SupplierContactNumber,String MachineType,int userid,String PropertyName)
//    {
//    	Boolean result=false;
//    	String returnValue="";
//    	
//    	try
//    	{
//
//
//    	  cs=con.prepareCall("call machineinfo_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//    	  cs.setInt(1, id);
//    	  cs.setString(2, propertyNumber);
//    	  cs.setString(3, machineLocation);
//    	  cs.setString(4, model);
//    	  cs.setString(5, ipAdd);
//    	  cs.setString(6, machineUsage);
//    	  cs.setString(7, department);
//    	  cs.setString(8, responsible);
//    	  cs.setString(9, responsibleEmail);
//    	  cs.setString(10, responsibleContactNumber);
//    	  cs.setString(11, machineOwner);
//    	  cs.setTimestamp(12, new java.sql.Timestamp(purchaseTime.getTime()));  
//    	  cs.setString(13, price);
//    	  cs.setString(14, project);
//    	  cs.setString(15, comments);
//    	  cs.setTimestamp(16, new java.sql.Timestamp(moveInTime.getTime()) ); 
//    	  cs.setString(17, Purchaser);
//    	  cs.setString(18, PurchaseMethod);
//    	  cs.setString(19, Supplier);
//    	  cs.setString(20, SupplierContact);
//    	  cs.setString(21, SupplierContactNumber);
//    	  cs.setString(22, MachineType);
//    	  cs.setInt(23, userid);
//    	  cs.setString(24, PropertyName);
//    	  
//    	  cs.registerOutParameter(25, Types.VARCHAR);
//    	  cs.execute();
//    	  returnValue=cs.getString(25);
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}catch(Exception ex)
//    	{
//    		System.out.print(ex.toString());
//    		result=false;
//    	}
//    	return result;
//    }
//    
//    //删除机器
//    public static Boolean DeleteMachine(int id)
//    {
//    	Boolean result=false;
//    	String returnValue="";
//    	
//    	try
//    	{
//    	  cs=con.prepareCall("call machineinfo_delete(?,?,?);");
//    	  cs.setInt(1, id);
//    	  cs.setString(2, "");
//    	  cs.registerOutParameter(3, Types.VARCHAR);
//    	  cs.execute();
//    	  returnValue=cs.getString(3);
//    
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}catch(Exception ex)
//    	{
//    		System.out.print(ex.toString());
//    		result=false;
//    	}
//    	return result;
//    }
//   
//    
//    
//    
//    
//    //得到检查表A的信息
//    public static Checkinfoa getCheckInfoA(int id,int year)
//    {
//    	Checkinfoa checkInfoa=new Checkinfoa();
//    	ResultSet rs;
//    	try
//    	{
//  
//      	  cs=con.prepareCall("call checkinfoa_search_by_id(?,?);");
//      	  cs.setInt(1, id);
//      	  cs.setInt(2,year);
//
//         if(cs.execute())
//         {
// 			rs=cs.getResultSet();
// 			if(rs!=null)
// 			{
// 			  if(rs.next())
// 			  {
// 				 checkInfoa=new Checkinfoa(Integer.parseInt(rs.getString(1)),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9).toString(),rs.getString(10).toString(),rs.getString(11).toString(),rs.getString(12).toString(),rs.getString(13).toString(),rs.getString(14).toString(),rs.getString(15).toString(),rs.getString(16).toString(),rs.getString(17).toString(),rs.getString(18).toString(),rs.getString(19).toString(),rs.getString(20).toString(), rs.getInt(21),Integer.parseInt(rs.getString(22).toString()));
// 			  }
//
// 			}
// 			else{checkInfoa=null;}
//         }
//         else
//         {
//        	 checkInfoa=null;
//         }
//    	}
//    	catch(Exception ex)
//    	{
//    		checkInfoa=null;
//    	}
//
//    	return checkInfoa;
//    }
//
//    //得到检查A的月度信息
//    public static Checkrecorda getCheckRecordA(int id,int year,int month)
//    {
//    	Checkrecorda checkRecordA=new Checkrecorda();
//    	ResultSet rs=null;
//    	
//    	try
//    	{
//    	  cs=con.prepareCall("call checkrecorda_search_by_id(?,?,?);");
//       	  cs.setInt(1, id);
//       	  cs.setInt(2, year);
//       	  cs.setInt(3, month);
//       	  
//       	  cs.execute();
//       	  rs=cs.getResultSet();
//       	  
//       	  if(rs.next())
//       	  {
//
//       		checkRecordA=new CheckRecordA(Integer.parseInt(rs.getString("id")) ,Integer.parseInt(rs.getString("CheckInfoID")) , Formatter.dbNull(rs.getString("os")), Integer.parseInt(rs.getString("MonthNumber")), Formatter.dbNull(rs.getString("Patch")), Formatter.dbNull(rs.getString("Application")),
//       				Formatter.dbNull(rs.getString("DataBaseCheck")), Formatter.dbNull(rs.getString("360Check")), Formatter.dbNull(rs.getString("Antivirus")), Formatter.dbNull(rs.getString("PasswordStrength")), Formatter.dbNull(rs.getString("AccountNormal")),
//       				Formatter.dbNull(rs.getString( "AccountAbnormal")), Formatter.dbNull(rs.getString("EventLog")), Formatter.dbNull(rs.getString("WebLog")),Formatter.dbNull( rs.getString("DataBaseLog")),Formatter.dbNull(rs.getString("HardDriverUsage")),
//       				Formatter.dbNull(rs.getString( "PageException")), Formatter.dbNull(rs.getString( "ReactionaryException")), Formatter.dbNull(rs.getString("PatchScanExist")), Formatter.dbNull(rs.getString("PatchScanHandle")),
//       				Formatter.dbNull(rs.getString("PatchScanOperator")), Formatter.dbNull(rs.getString("Service")), Formatter.dbNull(rs.getString("OSResponsibleSingnature")), Formatter.dbNull(rs.getString("OSAdminsitratorSignature")),
//       				Formatter.dbNull(rs.getString("CheckState")));
//       		
//       	  }
//    		
//    	}
//    	catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    		checkRecordA=null;
//    	}
//    	
//    	return checkRecordA;
//    }
//   
//    //更新检查表A
//    public static Boolean updateCheckInfoA(Checkinfoa checkinfoa)
//    {
//    	boolean result=false;
//       	String returnValue="";
//    	try
//    	{ 
//    	  cs=con.prepareCall("call checkinfoa_update_by_id(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//            
//     	  cs.setInt(1, checkInfoA.id);
//     	  cs.setString(2, checkInfoA.flowNumber);
//     	  cs.setString(3, checkInfoA.propertyNumber);
//     	  cs.setString(4, checkInfoA.serialNumber);
//     	  cs.setString(5, checkInfoA.responsibilityDepartment);
//     	  cs.setString(6, checkInfoA.machineLocation);
//     	  cs.setString(7, checkInfoA.model);
//     	  cs.setString(8, checkInfoA.systemInfo);
//     	  cs.setString(9, checkInfoA.ipAdd);
//     	  cs.setString(10, checkInfoA.machineUsage);
//     	  cs.setString(11, checkInfoA.mantainceStaff);
//     	  cs.setString(12, checkInfoA.backupContent);
//     	  cs.setString(13, checkInfoA.backupContentChange1);
//     	  cs.setString(14, checkInfoA.backupContentChange2);
//     	  cs.setString(15, checkInfoA.fileDirectory);
//     	  cs.setString(16, checkInfoA.fileDirectoryChange1);
//     	  cs.setString(17, checkInfoA.fileDirectoryChange2);
//     	  cs.setString(18, checkInfoA.backupPeriod);
//     	  cs.setString(19, checkInfoA.backupPeriodChange1);
//     	  cs.setString(20, checkInfoA.backupPeriodChange2);
//     	  cs.setInt(21, checkInfoA.year);
//     	  cs.setInt(22, checkInfoA.machineInfoID);
//     	  cs.registerOutParameter(23, Types.VARCHAR);
//     	  cs.execute();
//
//    	  returnValue=cs.getString(23);
//    	  
//    	    
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}
//    	catch(Exception ex)
//    	{
//    		result=false;
//    		System.out.println(ex.toString());
//    	}
//    	
//    	return result;
//    }
//    
//    //更新检查表A的月度记录
//    public static Boolean updateCheckRecordA(CheckRecordA checkRecordA)
//    {
//    	boolean result=false;
//       	String returnValue="";
//    	try
//    	{ 
//    	  cs=con.prepareCall("call checkrecorda_update_by_id(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//    	 
//    	  
//    	  
//    	  
//     	  cs.setInt(1, checkRecordA.id);
//     	  cs.setInt(2, checkRecordA.checkInfoID);
//     	  cs.setString(3, checkRecordA.os);
//     	  cs.setInt(4, checkRecordA.monthNumber);
//     	  cs.setString(5, checkRecordA.patch);
//     	  cs.setString(6, checkRecordA.application);
//     	  cs.setString(7, checkRecordA.dataBaseCheck);
//     	  cs.setString(8, checkRecordA.check360);
//     	  cs.setString(9, checkRecordA.antivirus);
//     	  cs.setString(10, checkRecordA.passwordStrength);
//     	  cs.setString(11, checkRecordA.accountNormal);
//     	  cs.setString(12, checkRecordA.accountAbnormal);
//     	  cs.setString(13, checkRecordA.eventLog);
//     	  cs.setString(14, checkRecordA.webLog);
//     	  cs.setString(15, checkRecordA.dataBaseLog);
//     	  cs.setString(16, checkRecordA.hardDriverUsage);
//    	  cs.setString(17, checkRecordA.pageException);
//    	  cs.setString(18, checkRecordA.reactionaryException);
//    	  cs.setString(19, checkRecordA.patchScanExist);
//    	  cs.setString(20, checkRecordA.patchScanHandle);
//    	  cs.setString(21, checkRecordA.patchScanOperator);
//    	  cs.setString(22, checkRecordA.service);
//     	  cs.setString(23, checkRecordA.osResponsibleSingnature);
//     	  cs.setString(24, checkRecordA.osAdminsitratorSignature);
//     	  cs.registerOutParameter(25, Types.VARCHAR);
//     	  
//     	  cs.execute();
//     	  
//    	  returnValue=cs.getString(25);
//    	  
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}
//    	catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    		result=false;
//    	}
//    	
//    	return result;
//    }
//
//    
//
//    
//    //得到检查表B的信息
//    public static Checkinfob getCheckInfoB(int id,int year)
//    {
//    	Checkinfob checkInfob=new Checkinfob();
//    	ResultSet rs;
//    	try
//    	{
//  
//      	  cs=con.prepareCall("call checkinfob_search_by_id(?,?);");
//      	  cs.setInt(1, id);
//      	  cs.setInt(2,year);
//
//         if(cs.execute())
//         {
// 			rs=cs.getResultSet();
// 			if(rs!=null)
// 			{
// 			  if(rs.next())
// 			  {
//
// 				 checkInfob=new Checkinfob(Integer.parseInt(rs.getString(1)),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9).toString(),rs.getString(10).toString(),rs.getString(11).toString(),rs.getInt(12),Integer.parseInt(rs.getString(13).toString()),rs.getString(14).toString());
// 			     
// 			  }
//
// 			}
// 			else{checkInfob=null;}
//         }
//         else
//         {
//        	 checkInfob=null;
//         }
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getCheckInfoB"+ex.toString());
//    		checkInfob=null;
//    	}
//
//    	return checkInfob;
//    }
//
//    //得到检查B的月度信息
//    public static CheckRecordB getCheckRecordB(int id,int year,int month)
//    {
//    	CheckRecordB checkRecordB=new CheckRecordB();
//    	ResultSet rs=null;
//    	
//    	try
//    	{
//    	  cs=con.prepareCall("call checkrecordb_search_by_id(?,?,?);");
//       	  cs.setInt(1, id);
//       	  cs.setInt(2, year);
//       	  cs.setInt(3, month);
//       	  
//       	  cs.execute();
//       	  rs=cs.getResultSet();
//       	  
//       	  if(rs.next())
//       	  {
//       		checkRecordB=new CheckRecordB(Integer.parseInt(rs.getString(1).toString()),Integer.parseInt(rs.getString(2).toString()),Integer.parseInt(rs.getString(3).toString()),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9).toString(),rs.getString(10).toString(),rs.getString(11));
//       	  }
//    		
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getCheckRecordB"+ex.toString());
//    		checkRecordB=null;
//    	}
//    	
//    	return checkRecordB;
//    }
//   
//    //更新检查表B
//    public static Boolean updateCheckInfoB(Checkinfob checkInfoB)
//    {
//    	boolean result=false;
//       	String returnValue="";
//    	try
//    	{ 
//    	  cs=con.prepareCall("call checkinfob_update_by_id(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
//     	  cs.setInt(1, checkInfoB.id);
//     	  cs.setString(2, checkInfoB.flowNumber);
//     	  cs.setString(3, checkInfoB.propertyNumber);
//     	  cs.setString(4, checkInfoB.serialNumber);
//     	  cs.setString(5, checkInfoB.responsibilityDepartment);
//     	  cs.setString(6, checkInfoB.machineLocation);
//     	  cs.setString(7, checkInfoB.model);
//     	  cs.setString(8, checkInfoB.systemInfo);
//     	  cs.setString(9, checkInfoB.ipAdd);
//     	  cs.setString(10, checkInfoB.machineUsage);
//     	  cs.setString(11, checkInfoB.mantainceStaff);
//     	  cs.setInt(12, checkInfoB.year);
//     	  cs.setInt(13, checkInfoB.machineInfoID);
//     	  cs.setString(14, checkInfoB.comments);
//     	  cs.registerOutParameter(15, Types.VARCHAR);
//     	  
//     	  cs.execute();
//     	  
//    	  returnValue=cs.getString(15);
//    	    
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}
//    	catch(Exception ex)
//    	{
//    		result=false;
//    	}
//    	
//    	return result;
//    }
//    
//    //更新检查表B的月度记录
//    public static Boolean updateCheckRecordB(CheckRecordB checkRecordB)
//    {
//    	boolean result=false;
//       	String returnValue="";
//    	try
//    	{ 
//    	  cs=con.prepareCall("call checkrecordb_update_by_id(?,?,?,?,?,?,?,?,?,?,?);");
//    	 
//     	  cs.setInt(1, checkRecordB.id);
//     	  cs.setInt(2, checkRecordB.checkInfoID);
//     	  cs.setInt(3, checkRecordB.monthNumber);
//     	  cs.setString(4, checkRecordB.networkBackup);
//     	  cs.setString(5, checkRecordB.harddriverBackup);
//     	  cs.setString(6, checkRecordB.logUploadAnalysis);
//     	  cs.setString(7, checkRecordB.firewallCheck);
//     	  cs.setString(8, checkRecordB.monthlyFloatAmount);
//     	  cs.setString(9, checkRecordB.serverStoppedInfo);
//     	  cs.setString(10, checkRecordB.signature);
//  
//     	  cs.registerOutParameter(11, Types.VARCHAR);
//     	  
//     	  cs.execute();
//     	  
//    	  returnValue=cs.getString(11);
//    	    
//    	  if(returnValue.equals("true"))
//    	  {
//    		  result=true;
//    	  }
//    	  else
//    	  {
//    		  result=false;
//    	  }
//    	}
//    	catch(Exception ex)
//    	{
//    		result=false;
//    	}
//    	
//    	return result;
//    }
//
//    
//    
//    //得到检查表历史
//    public static List<CheckinfoHistory> getCheckInfoHistory(String keyword_type,String keyword_machineid,String keyword_year)
//    {
//    	CheckinfoHistory ci=new CheckinfoHistory();
//    	List<CheckinfoHistory> checkInfoList=new ArrayList<CheckinfoHistory>();
//    	
//    	ResultSet rs;
//    	try
//    	{
//  
//      	  cs=con.prepareCall("call checkinfo_history(?,?,?);");
//      	  cs.setString(1,keyword_type);
//      	  cs.setString(2,keyword_machineid);
//      	  cs.setString(3,keyword_year);
//         if(cs.execute())
//         {
// 			rs=cs.getResultSet();
// 			if(rs!=null)
// 			{
// 			  while(rs.next())
// 			  {
// 				ci=new CheckInfo(rs.getString(1).toString(),rs.getInt(2),rs.getInt(3));
// 				checkInfoList.add(ci);
// 			  }
//
// 			}
// 			else{checkInfoList=null;}
//         }
//         else
//         {
//        	 checkInfoList=null;
//         }
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getCheckInfoHistory"+ex.toString());
//    		checkInfoList=null;
//    	}
//    	return checkInfoList;
//    }
//
//
//    
//    // 通过machineid得到这个设备的相关调转记录
//    public static List<Transferrecord> getTransferRecordByMachineInfoID(int machineinfoid)
//    {
//    	List<Transferrecord> transferRecordList=new ArrayList<Transferrecord>();
//    	Transferrecord transferRecord=new Transferrecord();
//    	ResultSet rs;
//    	
//    	try
//    	{
//    		cs=con.prepareCall("call transferrecord_search_by_machineid(?);");
//        	  cs.setInt(1,machineinfoid);
//  
//           if(cs.execute())
//           {
//   			rs=cs.getResultSet();
//   			if(rs!=null)
//   			{
//   			  while(rs.next())
//   			  {
//   				transferRecord=new Transferrecord(Integer.parseInt(rs.getString(1)) ,Integer.parseInt(rs.getString(2)),rs.getString(3).toString(),rs.getString(4),rs.getString(5).toString(),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getTimestamp(10));
//   				transferRecordList.add(transferRecord);
//   			  }
//
//   			}
//   			else{transferRecordList=null;}
//           }
//           else
//           {
//        	   transferRecordList=null;
//           }
//    		
//    	}
//    	catch(Exception ex)
//    	{
//    		ex.printStackTrace();
//    		System.out.println(ex.toString());
//    		transferRecordList=null;
//    	}
//    	return transferRecordList;
//    }
//    
//    //插入新的调转记录
//    public static Boolean addTransferRecord(Transferrecord transferRecord)
//    {
//    	Boolean result=false;
//       	String returnValue="";
//    	try
//    	{
//    		
//    		 cs=con.prepareCall("call transferrecord_add_by_machineid(?,?,?,?,?,?);");
//        	 
//        	  cs.setInt(1,transferRecord.machineInfoID);
//        	  cs.setString(2, transferRecord.curOwner);
//        	  cs.setString(3, transferRecord.curOwnerEmail);
//        	  cs.setString(4, transferRecord.department);
//        	  cs.setString(5, transferRecord.reason);
//        	  
//        	  cs.registerOutParameter(6, Types.VARCHAR);
//        	  cs.execute();
//        	  
//       	  returnValue=cs.getString(6);
//
//       	  if(returnValue.equals("true"))
//       	  {
//       		  result=true;
//       	  }
//       	  else
//       	  {
//       		  result=false;
//       	  }
//    	}
//    	catch(Exception ex)
//    	{
//    		result=false;
//    	}
//
//    	return result;
//    }
//    
//    //更新跳转记录
//    public static Boolean updateTransferRecord(Transferrecord transferRecord)
//    {
//    	Boolean result=false;
//    	String returnValue="";
//    	try
//    	{
//    	  cs=con.prepareCall("call transferrecord_update(?,?,?,?,?,?,?,?,?,?);");
//        	 
//       	  cs.setInt(1,transferRecord.id);
//       	  cs.setInt(2,transferRecord.machineInfoID);
//       	  cs.setString(3, transferRecord.preOwner);
//       	  cs.setString(4, transferRecord.preOwnerEmail);
//     	  cs.setString(5, transferRecord.curOwner);
//     	  cs.setString(6, transferRecord.curOwnerEmail);
//     	  cs.setInt(7, transferRecord.preid);
//     	  cs.setString(8, transferRecord.department);
//     	  cs.setString(9, transferRecord.reason);
//    
//       	  cs.registerOutParameter(10, Types.VARCHAR);
//       	  cs.execute();
//       	  
//      	  returnValue=cs.getString(10);
//      	    
//      	  if(returnValue.equals("true"))
//      	  {
//      		  result=true;
//      	  }
//      	  else
//      	  {
//      		  result=false;
//      	  }
//    		
//    	}
//    	catch(Exception ex)
//    	{
//    		 result=false;
//    	}
//    	return result;
//    }
//
//    
//    
//
//   
//    //更新跳转记录
//    public static Boolean deleteTransferRecord(Transferrecord transferRecord)
//    {
//    	Boolean result=false;
//    	String returnValue="";
//    	try
//    	{
//    	  cs=con.prepareCall("call transferrecord_delete(?,?);");
//        	 
//       	  cs.setInt(1,transferRecord.id);
//    
//       	  cs.registerOutParameter(2, Types.VARCHAR);
//       	  cs.execute();
//       	  
//      	  returnValue=cs.getString(2);
//      	    
//      	  if(returnValue.equals("true"))
//      	  {
//      		  result=true;
//      	  }
//      	  else
//      	  {
//      		  result=false;
//      	  }
//    		
//    	}
//    	catch(Exception ex)
//    	{
//    		 result=false;
//    	}
//    	return result;
//    }
//    
//     //通过userid得到他的email配置对象
//    public static EmailConfiguration getEmailConfigurationByUserID(int userid)
//    {
//    	EmailConfiguration emailConfiguration=new EmailConfiguration();
//    	ResultSet rs;
//    	try
//    	{
//    		cs=con.prepareCall("call emailconfiguration_search_by_userid(?);");
//      	    cs.setInt(1,userid);
//
//         if(cs.execute())
//         {
// 			rs=cs.getResultSet();
//
// 			  if(rs.next())
// 			  {
// 				 emailConfiguration=new EmailConfiguration(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4),rs.getString(5),rs.getString(6));
// 			  }
// 			else{emailConfiguration=null;}
//         }
//         else
//         {
//        	 emailConfiguration=null;
//         }
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getEmailConfigurationByUserID : "+ex.toString());
//    		emailConfiguration=null;
//    	}
//    	
//    	return emailConfiguration;
//    }
//
//    //得到通过machineid实例和制定年月生成的EmailInfo
// 	public static EmailInfo getEmalInfo(int machineid,EmailConfiguration emailConfiguration)
// 	{
// 		  EmailInfo emailInfo=new EmailInfo();
// 		  
// 	  	  Calendar cal = Calendar.getInstance();
//     	  int year = cal.get(Calendar.YEAR);
//     	  int month=cal.get(Calendar.MONTH)+1;
//     	  int day=cal.get(Calendar.DAY_OF_MONTH);
//     	  
//     	  Machineinfo machineInfo=BusinessHelper.getMachineInfoById(machineid);
// 		  CheckRecordA checkRecordA=BusinessHelper.getCheckRecordA(machineid,year,month);
// 		  CheckRecordB checkRecordB=BusinessHelper.getCheckRecordB(machineid,year,month);
// 		 
// 		  
// 		  //设置收件人
// 		  emailInfo.receiver.add(machineInfo.responsibleEmail);
// 		  
// 		  //设置邮件标题
// 		  emailInfo.title="设备月度检查提示-设备资产号："+machineInfo.propertyNumber;
//
// 		  emailInfo.body=BusinessHelper.CheckCheckRecord(machineInfo, year, month);
// 		  
// 		  emailInfo.sender=emailConfiguration.userName;
//
// 		  return emailInfo;
// 	}
//    
// 	
//    
//    //通过设备基本信息实例和制定的年月，检查记录是否填写完整
//    public static String CheckCheckRecord(Machineinfo machineInfo,int year,int month)
//    {
//    	String result="";
//    	String checkResultA="";
//    	String checkResultB="";
//    	try
//    	{
//    		checkResultA=getCheckRecordACheckResult(machineInfo,year,month);
//
//    		checkResultB=getCheckRecordBCheckResult(machineInfo,year,month);
//    		
//       	     if(!checkResultA.equals("")||!checkResultB.equals(""))
//       	     {
//       	    	Calendar cal=Calendar.getInstance();
//       	         Date d=cal.getTime();
//       	    	 result="<p>您好 ： </p>"+"<br>"+"<p>您所管理的设备 ："+machineInfo.propertyNumber.toString()+"，"+machineInfo.machineUsage+"， IP地址为： "+machineInfo.ipAdd+"，检查表未填写完整，详情如下： </p>"+"<p>"+checkResultA+"</p>"+"<p>"+checkResultB+"</p>"+"<p>请您务必在本月12日之前填写完整</p>"+"<br>"+"<p>谢谢</p>"+"<p>中国科学院文献情报中心资产管理系统</p>"+"<p>"+d.toLocaleString()+"</p>";
//       	        // result="<p>"+"\r\n"+"资产号： "+"\r\n"+"</p>"+checkResultA+"\r\n"+checkResultB+"\r\n"+"<p>"+"中国科学院文献情报中心      "+d.toLocaleString()+"\r\n"+"</p>";
//       	     }
//
//
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.CheckCheckRecord : "+ex.toString());
//    		result="";
//    	}
//    	return result;
//    }
//    
//    public static String getCheckRecordACheckResult(Machineinfo machineInfo,int year,int month)
//    {
//    	String checkResultA="";
//
//      	try
//    	{
//  		 cs=con.prepareCall("call checkrecorda_check(?,?,?,?);");
//  	     cs.setInt(1,machineInfo.id);
//         cs.setInt(2, year);
//  	     cs.setInt(3, month);
//  	  
//  	     cs.registerOutParameter(4, Types.VARCHAR);
//  	     cs.execute();
//  	  
//  	     checkResultA=cs.getString(4);
//  	     
//  	     if(!checkResultA.equals(""))
//  	     {
//  	    	checkResultA="<p>"+year+"年"+month+"月"+",检查表(A),有如下项没有填写完整 : "+"\r\n"+checkResultA+"\r\n"+"</p>";
//  	     }
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getCheckRecordACheckResult : "+ex.toString());
//    		checkResultA="";
//    	}
//  	     return checkResultA;
//    }
//    
//    public static String getCheckRecordBCheckResult(Machineinfo machineInfo,int year,int month)
//    {
//    	String checkResultB="";
//
//      	try
//    	{
//  		 cs=con.prepareCall("call checkrecordb_check(?,?,?,?);");
//  	     cs.setInt(1,machineInfo.id);
//         cs.setInt(2, year);
//  	     cs.setInt(3, month);
//  	  
//  	     cs.registerOutParameter(4, Types.VARCHAR);
//  	     cs.execute();
//  	  
//  	     checkResultB=cs.getString(4);
//  	     
//  	     if(!checkResultB.equals(""))
//  	     {
//  	    	checkResultB="<p>"+year+"年"+month+"月"+",检查表(B),有如下项没有填写完整 : "+"\r\n"+checkResultB+"\r\n"+"</p>";
//  	     }
//    	}
//    	catch(Exception ex)
//    	{
//    		System.out.println("BusinessHelper.getCheckRecordBCheckResult : "+ex.toString());
//    		checkResultB="";
//    	}
//  	     return checkResultB;
//    }
//    
// 
//     //通过机器ID和年份得到当年的所有A类检查记录
//     public static HashMap <Integer ,CheckRecordA> getCheckRecordAByMachineIdAllyear(int machineid,int year)
//     {
//    	 HashMap<Integer, CheckRecordA> checkRecordAYearList=new HashMap<Integer, CheckRecordA>();
//    	 CheckRecordA checkRecordA=null;
//    	 ResultSet rs=null;
//    		try
//        	{
//      		 cs=con.prepareCall("call checkrecorda_search_by_checkinfoaid_allyear(?,?);");
//      	     cs.setInt(1,machineid);
//             cs.setInt(2, year);
//      	     cs.execute();
//      	     
//      	      rs=cs.getResultSet();
//      	     
//      	     if(rs!=null)
//      	     {
//      	    	while(rs.next())
//      	    	{
//      	       		checkRecordA=new CheckRecordA(Integer.parseInt(rs.getString("id")),Integer.parseInt(rs.getString("CheckInfoID")) ,Formatter.dbNull( rs.getString("os")), Integer.parseInt(rs.getString("MonthNumber")), Formatter.dbNull(rs.getString("Patch")), Formatter.dbNull(rs.getString("Application")),
//      	       			Formatter.dbNull(rs.getString("DataBaseCheck")), Formatter.dbNull(rs.getString("360Check")), Formatter.dbNull(rs.getString("Antivirus")), Formatter.dbNull(rs.getString("PasswordStrength")), Formatter.dbNull(rs.getString("AccountNormal")),
//      	       		Formatter.dbNull(rs.getString( "AccountAbnormal")), Formatter.dbNull(rs.getString("EventLog")),Formatter.dbNull( rs.getString("WebLog")), Formatter.dbNull(rs.getString("DataBaseLog")),Formatter.dbNull(rs.getString("HardDriverUsage")),
//      	       	Formatter.dbNull(rs.getString( "PageException")), Formatter.dbNull(rs.getString( "ReactionaryException")), Formatter.dbNull(rs.getString("PatchScanExist")), Formatter.dbNull(rs.getString("PatchScanHandle")),
//      	      Formatter.dbNull(rs.getString("PatchScanOperator")), Formatter.dbNull(rs.getString("Service")), Formatter.dbNull(rs.getString("OSResponsibleSingnature")), Formatter.dbNull(rs.getString("OSAdminsitratorSignature")),
//      	    Formatter.dbNull(rs.getString("CheckState")));
//      	       		if(checkRecordA!=null)
//      	    		{
//      	    			checkRecordAYearList.put(checkRecordA.monthNumber, checkRecordA);
//      	    		}
//      	    		checkRecordA=null;
//      	    	}
//      	     }
//        	}
//        	catch(Exception ex)
//        	{
//        		ex.printStackTrace();
//        		System.out.println(ex.toString());
//        		checkRecordAYearList=null;
//        	}
//    	 return checkRecordAYearList;
//     }
//    
//     //通过机器ID和年份得到当年的所有A类检查记录
//     public static HashMap <Integer ,CheckRecordB> getCheckRecordBByMachineIdAllyear(int machineid,int year)
//     {
//    	 HashMap<Integer, CheckRecordB> checkRecordBYearList=new HashMap<Integer, CheckRecordB>();
//    	 CheckRecordB checkRecordB=null;
//    	 ResultSet rs=null;
//    		try
//        	{
//      		 cs=con.prepareCall("call checkrecordb_search_by_checkinfobid_allyear(?,?);");
//      	     cs.setInt(1,machineid);
//             cs.setInt(2, year);
//      	     cs.execute();
//      	     
//      	      rs=cs.getResultSet();
//      	     
//      	     if(rs!=null)
//      	     {
//      	    	while(rs.next())
//      	    	{
//      	   		    checkRecordB=new CheckRecordB(Integer.parseInt(rs.getString(1).toString()),Integer.parseInt(rs.getString(2).toString()),Integer.parseInt(rs.getString(3).toString()),rs.getString(4).toString(),rs.getString(5).toString(),rs.getString(6).toString(),rs.getString(7).toString(),rs.getString(8).toString(),rs.getString(9).toString(),rs.getString(10).toString(),rs.getString(11));
//      	      
//      	    		if(checkRecordB!=null)
//      	    		{
//      	    			checkRecordBYearList.put(checkRecordB.monthNumber, checkRecordB);
//      	    		}
//      	    		checkRecordB=null;
//      	    	}
//      	     }
//        	}
//        	catch(Exception ex)
//        	{
//        		ex.printStackTrace();
//        		System.out.println(ex.toString());
//        		checkRecordBYearList=null;
//        	}
//    	 return checkRecordBYearList;
//     }
//    
//     
//     // 得到用户列表
//     public static List<User>  getUserList()
//     {
//     	List<User> userList=new ArrayList<User>();
//     	
//     	ResultSet rs=null;
//     	try
//     	{
//     		cs=con.prepareCall("call laod_users();");
// 	    	cs.execute();
// 	    	  
//
//     		  rs=cs.getResultSet();
//     			
//     			if(rs!=null)
//     			{
//     			int rowindex=1;
//     			  while(rs.next())
//     			  {
//     			    User  user=new User(Integer.parseInt(rs.getString(1).toString()),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
//     			   user.setDisplayID(rowindex);
//     			    userList.add(user);
//     			    rowindex++;
//     			  }
//
//     		}
//     		else{}
//     	}
//     	catch(Exception ex)
//     	{
//     	 System.out.println("getUserList: "+ex.toString());
//     	userList=null;
//     	}
//     	return userList;
//     
//     }
//     
//     
//     
//     // 新增用户
//     public static String  AddUser(String name,String username,String password,int userroleid,String email,String department,String contactNumber)
//     {
//    	 String returnValue="";
//      	try
//      	{
//      		cs=con.prepareCall("call add_user(?,?,?,?,?,?,?,?);");
//     		cs.setString(1, name);
//      		cs.setString(2, username);
//      		cs.setString(3, password);
//      		cs.setInt(4, userroleid);
//      		cs.setString(5, email);
//      		cs.setString(6, department);
//    		cs.setString(7, contactNumber);
//    		
//      		 cs.registerOutParameter(8, Types.VARCHAR);
//  	    	 cs.execute();
//
//  	    	returnValue=cs.getString(8).toString();
//
//  	    	 if(String.valueOf(returnValue).equals("true"))
//  	    	 {
//  	    		returnValue="新增用户成功";
//  	    	 }
//      	}
//      	catch(Exception ex)
//      	{
//      		ex.printStackTrace();
//      	    System.out.println("AddUser: "+ex.toString());
//      	  returnValue="新增用户失败";
//      	}
//
//    	return returnValue;
//     }
//     
//     
//     // 新增用户
//     public static String  DeleteUser(int userid,String username,String email)
//     {
//    	 String result="";
//      	try
//      	{
//      		cs=con.prepareCall("call users_delete(?,?,?,?);");
//      		cs.setInt(1, userid);
//      		cs.setString(2, username);
//      		cs.setString(3, email);
//
//      		 cs.registerOutParameter(4, Types.VARCHAR);
//  	    	 cs.execute();
//
//  	    	 result=cs.getString(4);
//  	    	 if(result.equals("true"))
//  	    	 {
//  	    		result="删除成功";
//  	    	 }
//      	}
//      	catch(Exception ex)
//      	{
//      		ex.printStackTrace();
//      	    System.out.println("DeleteUser: "+ex.toString());
//      	   result="删除失败";
//      	}
//
//    	return result;
//     }
//     
//     // 通过用户ID取得用户详情
//     public static User  getUserByUserId(int userid)
//     {
//     	User user=new User();
//     	
//     	ResultSet rs=null;
//     	try
//     	{
//     		cs=con.prepareCall("call users_search_by_id(?);");
//     		cs.setInt(1, userid);
// 	    	cs.execute();
//
//     	    rs=cs.getResultSet();
//     	
//     			if(rs.next())
//     			{
//     			     user=new User(Integer.parseInt(rs.getString(1).toString()),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
//     		         user.setUserRole(rs.getInt(13));
//     			}
//     	}
//     	catch(Exception ex)
//     	{
//     	 System.out.println("getUserByUserId: "+ex.toString());
//     	user=null;
//     	}
//     	return user;
//     
//     }
//
//     //更新用户信息
//     public static String  updateUser(User user)
//     {
//     	//boolean result=false;
//        String returnValue="";
//     	try
//     	{ 
//     	  cs=con.prepareCall("call users_update(?,?,?,?,?,?,?,?,?,?,?,?);");
//     	 
//      	  cs.setInt(1, user.id);
//      	  cs.setString(2, user.name);
//      	  cs.setString(3, user.age);
//      	  cs.setString(4, user.gender);
//      	  cs.setString(5, user.username);
//      	  cs.setString(6, user.password);
//      	  cs.setString(7, user.email);
//      	  cs.setString(8, user.islogin);
//      	  cs.setString(9, user.department);
//          cs.setString(10, user.contactNumber);
//      	  cs.setInt(11, user.userRole);
//      	  
//      	  cs.registerOutParameter(12, Types.VARCHAR);
//      	  
//      	  cs.execute();
//      	  
//     	  returnValue=cs.getString(12);
//     	    
//     	  if(returnValue.equals("true"))
//     	  {
//     		 returnValue="更新成功";
//     	  }
//     	  else
//     	  {
//     		 returnValue="更新失败";
//     	  }
//     	}
//     	catch(Exception ex)
//     	{
//     		System.out.println(ex.toString());
//     		returnValue="";
//     	}
//     	
//     	return returnValue;
//     }
//     
//
//     //更新用户信息
//     public static List<User>  getShortUserList()
//     {
//    	 List<User> userList=new ArrayList<User>();
//    	 
//     	ResultSet rs=null;
//     	try
//     	{ 
//     		String sql="select users.*,concat(users.name,'(',users.department,')') as fullname from users where state='1' order by department;";
//     		 rs=db.query(sql);
//     		int rowindex=1;
//      	   if(rs!=null)
//      	   {
//      		   while(rs.next())
//      		   {
//    			    User  user=new User(Integer.parseInt(rs.getString(1).toString()),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
//    			    user.setDisplayID(rowindex);
//    			    userList.add(user);
//      		   }
//      	   }
//      	   else
//      	   {
//      		 userList=null;
//      	   }
//     	}
//     	catch(Exception ex)
//     	{
//     		System.out.println(ex.toString());
//     		userList=null;
//     	}
//     	
//     	return userList;
//     }
//     
//     
//     
//     // 得到当前用户可以访问的功能列表
//     public static List<Function>  getFunctionList(int userid)
//     {
//     	List<Function> functionList=new ArrayList<Function>();
//     	
//     	ResultSet rs=null;
//     	try
//     	{
//     		cs=con.prepareCall("call get_functions_by_userid(?);");
//     		cs.setInt(1, userid);
// 	    	cs.execute();
// 	    	  
//
//     		  rs=cs.getResultSet();
//     			
//     			if(rs!=null)
//     			{
//     			int rowindex=1;
//     			  while(rs.next())
//     			  {
//     				 Function  user=new Function(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),Integer.parseInt(rs.getString(7)),rs.getTimestamp(8),rs.getString(9));
//    
//     			    functionList.add(user);
//     			    rowindex++;
//     			  }
//
//     		}
//     		else{}
//     	}
//     	catch(Exception ex)
//     	{
//     	 System.out.println("getFunctionList: "+ex.toString());
//     	functionList=null;
//     	}
//     	return functionList;
//     
//     }
//     
//     
//     
//}
