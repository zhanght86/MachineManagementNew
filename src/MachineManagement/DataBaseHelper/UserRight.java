package MachineManagement.DataBaseHelper;

public class UserRight {
	
	//通过用户ID得到用户的数据权限
//	public static String getUserRightByUserId(int userid)
//	{
//		String userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
//		try
//		{
//			if(userid==1)
//			{
//				userRighrStr="";
//			}
//			else
//			{
//				 userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid="+userid+") righttable on righttable.machineinfoid=machineinfo.id ";
//			}
//		}
//		catch(Exception ex)
//		{
//			ex.printStackTrace();
//			System.out.println(ex.toString());
//			 userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
//			
//		}
//		
//		return userRighrStr;
//	}
//	
	//通过用户ID达到用户的功能权限
	public static String getSystemFunctionsUserRightByUserId(int userid)
	{
		String userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
		try
		{
			if(userid==1)
			{
				userRighrStr="";
			}
			else
			{
				 userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid="+userid+") righttable on righttable.machineinfoid=machineinfo.id ";
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println(ex.toString());
			 userRighrStr=" inner join (select  users_machineinfo_r.machineinfoid as machineinfoid from users_machineinfo_r where userid=0) righttable on righttable.machineinfoid=machineinfo.id ";
			
		}
		
		return userRighrStr;
	}
	
}
