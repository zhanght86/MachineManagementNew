package MachineManagement.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import MachineManagement.DataBaseHelper.BusinessHelper;


@Controller
@Scope("prototype")
public class LoginController {

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
           String LoginResult=BusinessHelper.Login(username,password);
			if(!LoginResult.equals("false"))
			{

				String[] result=LoginResult.split(",");
				httpSession.setAttribute("userid", result[0]);
				httpSession.setAttribute("loginname", result[1]);
				httpSession.setAttribute("displayname", result[2]);
				httpSession.setAttribute("SessionID", httpSession.getId());
				httpSession.setAttribute("isadmin", BusinessHelper.isadmin(Integer.parseInt(result[0])));
				
				Map<Object, Object> map= new HashMap<Object, Object>();
				map.put("userid", result[0]);
				map.put("loginname", result[1]);
				map.put("displayname",result[2]);
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
