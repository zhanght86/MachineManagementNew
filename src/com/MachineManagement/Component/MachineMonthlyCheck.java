package com.MachineManagement.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Email.EmailHelper;
import com.Email.EmailInfo;
import com.MachineManagement.Common.CommonDataHelper;
import com.las.MachineManagement.Bean.Emailconfiguration;
import com.las.MachineManagement.Bean.Emailrecord;
import com.las.MachineManagement.Bean.Machineinfo;
import com.las.MachineManagement.Bean.Users;
import com.las.MachineManagement.Bean.UsersMachineinfoR;
import com.springframework.orm.ManchineManagementDao;

@Component
public class MachineMonthlyCheck {
	
	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	@Autowired(required=true) 
	private CommonDataHelper commonDataHelper;
	
	
	
	public void checkByUserId()
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String sqlDate=sdf.format(new Date());
			
			Calendar cal=Calendar.getInstance();//使用日历类
			int year=cal.get(Calendar.YEAR);//得到年
			int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
			int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
			int hour=cal.get(Calendar.HOUR);//得到小时
			int minute=cal.get(Calendar.MINUTE);//得到分钟
			int second=cal.get(Calendar.SECOND);//得到秒
			
			
			
			List<Users> usersList=manchineManagementDao.find("from Users where  state=1 and id<>1 and id<>2 and id<>3   and id =4 order by id asc");
			for(Users users:usersList)
			{
			
				int userid=users.getId();
				String username=users.getName();
				String email=users.getEmail();
				boolean needCheck=false;
				List<UsersMachineinfoR>  usersMachineinfoRList=manchineManagementDao.find("from UsersMachineinfoR where userid=? and state=1 ",new Object[]{String.valueOf(userid)});
				String receiver="";
				System.out.println(users.getEmail());
				String checkResult="";
				for(UsersMachineinfoR usersMachineinfoR:usersMachineinfoRList)
				{
				
					int macineid=Integer.parseInt(usersMachineinfoR.getMachineinfoid());
					Machineinfo machineInfo=new Machineinfo();
					List<Machineinfo>  machineinfoList=manchineManagementDao.find(" from Machineinfo where id=? ",new Object[]{macineid});
					if(machineinfoList!=null&&machineinfoList.size()!=0)
					{
		
						machineInfo=machineinfoList.get(0);
						 checkResult=commonDataHelper.CheckCheckRecord(machineInfo,year,month);
//						System.out.println(checkResult);
					}
					else
					{
					}
			
					if(!checkResult.equalsIgnoreCase(""))
					{
						receiver=machineInfo.getResponsibleEmail();
						needCheck=true;
						break;
					}
				}
				
				if(needCheck)
				{
					Emailconfiguration emailConfiguration=new Emailconfiguration();
					List<Emailconfiguration> emailconfigurationList= manchineManagementDao.find("from Emailconfiguration");
					if(emailconfigurationList!=null&&emailconfigurationList.size()!=0)
					{
						emailConfiguration=emailconfigurationList.get(0);
					}
					else
					{
						
					}
					
					EmailInfo emailInfo=new EmailInfo();
					//设置收件人
					emailInfo.receiver.add(receiver);
					//设置邮件标题
					emailInfo.title="设备月度检查";
					emailInfo.body="<p>您好 ： </p> <br> <p>您所管理的服务器设备本月检查表未填写完整，请登录 <a href='http://159.226.100.85' >http://159.226.100.85</a> 查看详情。</p> <p>中国科学院文献情报中心资产管理系统</p><br>谢谢！ </p>";
					EmailHelper emailHelper=new EmailHelper(emailConfiguration,emailInfo);
					emailHelper.Send();
					System.out.println("邮件通知："+receiver);
					
					//邮件记录
					Emailrecord emailrecord=new Emailrecord();
					emailrecord.setMachineinfo(null);
					emailrecord.setEmailSender(emailConfiguration.getUserName());
					emailrecord.setEmailReceiver(receiver);
					emailrecord.setEmailContent(emailInfo.body);
					emailrecord.setCreateDatetime(new Date());
					emailrecord.setUpdateDatetime(new Date());
					emailrecord.setState("1");
					manchineManagementDao.saveOrUpdate(emailrecord);
					
				}
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
