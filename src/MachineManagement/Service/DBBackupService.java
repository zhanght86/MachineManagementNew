package MachineManagement.Service;


import Email.EmailHelper;
import Email.EmailInfo;
import Support.DBBackupHelper;
import Support.PropertiesLoader;

public class DBBackupService {

	
//	public  DBBackupResult work()
//	{
//		DBBackupResult dbBackupResult=new DBBackupResult();
//		try
//		{
//			PropertiesLoader propertiesLoader=new PropertiesLoader("dbbackup.properties");
//			DBBackupHelper dbBackupHelper=new DBBackupHelper();
//			String dataPath=dbBackupHelper.backup(propertiesLoader.getValue("binpath"), propertiesLoader.getValue("backpuptemppath"));
//			
//			EmailConfiguration emailConfiguration=new EmailConfiguration();
//			
//			EmailInfo emailInfo=new EmailInfo();
//			
//			  emailInfo.receiver.add("czjczj20@163.com");
//	 		  
//			  //设置邮件标题
//			  emailInfo.title=dataPath;
//
//			  emailInfo.body=dataPath;
//			  
//			  emailInfo.sender=emailConfiguration.userName;
//			  
//				EmailHelper emailHelper=new EmailHelper(emailConfiguration,emailInfo);
//				
//				if(emailHelper.Send())
//			    {
//					System.out.println("邮件已发送");
//			    }
//				else
//				{
//					System.out.println("邮件发送失败");
//				}
//			  
//		}
//		catch(Exception ex)
//		{
//		ex.printStackTrace();	
//		}
//		
//		return dbBackupResult;
//	}
	
}
