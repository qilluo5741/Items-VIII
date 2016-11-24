package org.deepin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.deepin.entity.UserInfo;
import org.deepin.service.IUserInfoService;
import org.deepin.util.Log;
import org.deepin.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
public class UserInfoController {
	@Resource
	private IUserInfoService service;
	@Autowired
	private HttpSession session;
	/**
	 * 用户登录
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	@RequestMapping("login")
	public String login(String userName,String userPwd){
		String password = MD5Util.encode(userPwd);
		UserInfo user=new UserInfo();
		user.setUserName(userName);
		user.setUserPwd(password);
		UserInfo userinfo=service.login(user);
		if(null!=userinfo){
			System.out.println("登录成功！");
			Log.getInstance().debug(userName+"在"+new Date()+"成功登录系统!");
			session.setAttribute("userName",userName);
			return "true";
		}else{
			System.out.println("登录失败！");
			Log.getInstance().debug(userName+"在"+new Date()+"登录系统失败!");
			return "false";
		}
	}
	/**
	 * 用户注册
	 * @param UserName
	 * @param UserPwd
	 * @return
	 */
	@RequestMapping("reguser")
	public String reguser(String userName,String userPwd){
		try {
			String password = MD5Util.encode(userPwd);
			String userId=RandomStringUtils.randomNumeric(17);
			Date date=new Date();
			SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String str=sdff.format(date); 
			UserInfo user=new UserInfo();
			user.setUserId(userId);
			user.setUserName(userName);
			user.setUserPwd(password);
			user.setUserStatus(0);
			user.setUserTime(str);
			user.setUserRemark("管理员什么都没留下！");
			int i=service.addUser(user);
			if(i>0){
				System.out.println("注册成功！");
				session.setAttribute("userName",userName);
				Log.getInstance().debug(userName+"在"+new Date()+"注册成功!");
				return "true";
			}else{
				System.out.println("注册失败！");
				return "false";
			}
		} catch (Exception e) {
			return "注册失败！";
		}
	}
}
