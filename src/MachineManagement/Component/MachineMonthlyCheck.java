package MachineManagement.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.las.MachineManagement.Bean.Machineinfo;

import Email.EmailHelper;
import Email.EmailInfo;

@Component
public class MachineMonthlyCheck {
	
	
	
	public void checkByUserId()
	{
		try
		{
			MachineManagement.DataBaseHelper.Database db=BusinessHelper.db;
			Connection connection=db.getConnection();
			Statement statment=db.getStatement();
			PreparedStatement perstmt ;
			String sql="";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			String sqlDate=sdf.format(new Date());
			ResultSet rs=null;
			
			Calendar cal=Calendar.getInstance();//使用日历类
			int year=cal.get(Calendar.YEAR);//得到年
			int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
			int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
			int hour=cal.get(Calendar.HOUR);//得到小时
			int minute=cal.get(Calendar.MINUTE);//得到分钟
			int second=cal.get(Calendar.SECOND);//得到秒
			
			
			sql="select * from users where state=1 and id<>1 and id<>2 and id<>3 order by id asc;";
			PreparedStatement perstmt0= connection.prepareStatement(sql);
			ResultSet rs0=perstmt0.executeQuery();
			while(rs0.next())
			{
				
				int userid=rs0.getInt("id");
				String username=rs0.getString("name");
				String email=rs0.getString("email");
				
				sql="select u.id as userid,m.id as machineid from machineinfo m left join users_machineinforesponsible_r umr on umr.machineinfoid=m.id left join users u on u.id=umr.userid where m.state=1 and m.MachineType=2 and userid=? order by userid as";
				PreparedStatement perstmt1= connection.prepareStatement(sql);
				ResultSet rs1=perstmt1.executeQuery();
				String checkResult="";
				boolean needCheck=false;
				int amachineid=0;
				while(rs1.next())
				{
					int macineid=rs1.getInt("id");
					amachineid=macineid;
					Machineinfo machineInfo=new Machineinfo();
					machineInfo.setId(macineid);
					checkResult=BusinessHelper.CheckCheckRecord(machineInfo,year,month);
					if(!checkResult.equalsIgnoreCase(""))
					{
						needCheck=true;
						break;
					}
				}
				
				if(needCheck)
				{
					EmailConfiguration emailConfiguration=BusinessHelper.getEmailConfigurationByUserID(userid);
					EmailInfo emailInfo=BusinessHelper.getEmalInfo(amachineid,emailConfiguration);
					EmailHelper emailHelper=new EmailHelper(emailConfiguration,emailInfo);
					emailInfo.body="<p>您好 ： </p> <br> <p>您所管理的服务器设备本月检查表未填写完整，请登录 <a href='http://159.226.100.85' >http://159.226.100.85</a> 查看详情。</p> <p>中国科学院文献情报中心资产管理系统</p><br>谢谢！ </p>";
					emailHelper.Send();
				}
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
