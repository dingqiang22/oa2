package com.atguigu.shiroHellow;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyShiroHandler {
@RequestMapping(value="/shiro-login")
public String login(@RequestParam(value="username",required=false) String username,
		@RequestParam(value="password",required=false) String password
		){
	Subject currentUser = SecurityUtils.getSubject();
	if(!currentUser.isAuthenticated()){
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
		System.out.println("µÇÂ¼Ê§°Ü"+e.getMessage());
		return "redirect:/login.jsp";
		}
		
	}
	
	return "redirect:/list.jsp";
	
}
}
