package MachineManagement.Common;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.vlabs.umt.oauth.AccessToken;
import cn.vlabs.umt.oauth.Oauth;
import cn.vlabs.umt.oauth.UMTOauthConnectException;
import cn.vlabs.umt.oauth.UserInfo;
import cn.vlabs.umt.oauth.common.exception.OAuthProblemException;

/**
 * 接受重定向code，后台请求UMT OAuth服务器获取用户信息
 * @author zh
 *
 */
public class TokenController extends HttpServlet {
//	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream in = TokenController.class.getResourceAsStream("/umtoauthconfig.properties");
		Properties p = new Properties();
		p.load(in);
		Oauth oauth = new Oauth(p);
		try {
			AccessToken token;
			token = oauth.getAccessTokenByRequest(req);
			req.setAttribute("token", token.getAccessToken());
			req.setAttribute("refreshToken",token.getRefreshToken());
			//登录完成 获登录的用户信息
			String jj = token.getUserInfo().getPasswordType();
			UserInfo userinfo= token.getUserInfo();
			System.out.println(userinfo.getSecurityEmail());
			System.out.println(userinfo.getTrueName());
			System.out.println(userinfo.getUmtId());
			System.out.println(userinfo.getCstnetId());
			req.setAttribute("userInfo", token.getUserInfo());
//			req.getRequestDispatcher("tokenResult.jsp").forward(req, resp);
			resp.sendRedirect("login2.do?username="+userinfo.getCstnetId()+"&password="+123456+"");
//			req.getRequestDispatcher("login2.do").forward(req, resp);
			
			
//			//100分钟session超时
//			httpSession.setMaxInactiveInterval(6000);
//			ModelAndView mav=null;
//
//			String username=userinfo.getUmtId();
//			String password="123456";
//			//验证传递过来的参数是否正确，否则返回到登陆页面。
//			if(this.checkParams(new String[]{username,password})){
//	           String LoginResult=BusinessHelper.Login(username,password);
//				if(!LoginResult.equals("false"))
//				{
//
//					String[] result=LoginResult.split(",");
//					httpSession.setAttribute("userid", result[0]);
//					httpSession.setAttribute("loginname", result[1]);
//					httpSession.setAttribute("displayname", result[2]);
//					httpSession.setAttribute("SessionID", httpSession.getId());
//					
//					Map<String, String> map= new HashMap<String, String>();
//					map.put("userid", result[0]);
//					map.put("loginname", result[1]);
//					map.put("displayname",result[2]);
//					map.put("result","登陆成功");
//					
//					req.setAttribute("userInfo", token.getUserInfo());
//					req.getRequestDispatcher("tokenResult.jsp").forward(req, resp);
//					//mav=new ModelAndView(new RedirectView("main.do"));
//				    //mav = new ModelAndView("main");
//
//				}
//				else
//				{
//					req.setAttribute("userInfo", token.getUserInfo());
//					req.getRequestDispatcher("tokenResult.jsp").forward(req, resp);
//					mav.addObject("result","用户名或密码错误");
//				}
//			}
//			else
//	        {
//				req.setAttribute("userInfo", token.getUserInfo());
//				req.getRequestDispatcher("tokenResult.jsp").forward(req, resp);
//			}
	
			
		} catch (UMTOauthConnectException e) {
			//网络错误
		}catch(OAuthProblemException e){
			//错误码
			String error = e.getError();
			//系统oauth2连接错误
		} 
	}
//	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
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
