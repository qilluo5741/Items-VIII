package org.deepin.service;

import org.deepin.entity.UserInfo;

public interface IUserInfoService {
	//用户登录
	public UserInfo login(UserInfo user);
	//注册一个用户
	public int addUser(UserInfo user);
}
