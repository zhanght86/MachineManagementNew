package MachineManagement.Common;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.las.MachineManagement.Bean.UsersUserroleR;
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
}

