package MachineManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.las.MachineManagement.Bean.Users;
import com.springframework.orm.ManchineManagementDao;

import MachineManagement.Common.CommonDataHelper;
import Support.HASH;



@Controller
@Scope("prototype")
public class LoginController {

	@Autowired(required=true) 
	private ManchineManagementDao manchineManagementDao;
	
	@Autowired(required=true) 
	private CommonDataHelper commonDataHelper;
	
	
	@RequestMapping("login2")
	public ModelAndView login2(HttpServletRequest request,HttpServletResponse response){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		return this.login(request, response, username, password, request.getSession());
	}

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,String username,String password,HttpSession httpSession){
		
		//100分钟session超时
		httpSession.setMaxInactiveInterval(6000);
		ModelAndView mav=null;

		//验证传递过来的参数是否正确，否则返回到登陆页面。
		if(this.checkParams(new String[]{username,password})){
           
   	    password =(HASH.HashToHEX(password, "SHA-1"));
   	    
   	    List<Users> usersList=manchineManagementDao.find("from Users where email=? and password=?",new Object[]{username,password});
   	    Users users=new Users();
			if(usersList!=null&&usersList.size()!=0)
			{

				users=usersList.get(0);
				httpSession.setAttribute("userid", users.getId());
				httpSession.setAttribute("loginname", users.getEmail());
				httpSession.setAttribute("displayname", users.getName());
				httpSession.setAttribute("SessionID", httpSession.getId());
				httpSession.setAttribute("isadmin", commonDataHelper.isadmin(users.getId()));
				
				Map<Object, Object> map= new HashMap<Object, Object>();
				map.put("userid", users.getId());
				map.put("loginname", users.getEmail());
				map.put("displayname",users.getName());
				map.put("result","登陆成功");
				map.put("isadmin", httpSession.getAttribute("isadmin"));
				mav=new ModelAndView(new RedirectView("main.do"));
			    //mav = new ModelAndView("main");

			}
			else
			{
				mav = new ModelAndView("login");
				mav.addObject("result","用户名或密码错误");
			}
		}
		else
        {
			mav = new ModelAndView("login");
		}
		return mav;
	}
	
	/***
	 * 验证参数是否为空
	 * @param params
	 * @return
	 */
	private boolean checkParams(String[] params){
		for(String param:params){
			if(param==""||param==null||param.isEmpty()){
				return false;
			}
		}
		return true;
	}
}
