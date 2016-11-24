package org.deepin.serviceImpl;

import javax.annotation.Resource;

import org.deepin.entity.UserInfo;
import org.deepin.mapper.UserInfoMapper;
import org.deepin.service.IUserInfoService;
import org.springframework.stereotype.Service;
@Service
public class UserInfoServiceImpl implements IUserInfoService {
	@Resource
	private UserInfoMapper mapper;
	public UserInfo login(UserInfo user) {
		return mapper.login(user);
	}
	public int addUser(UserInfo user) {
		return mapper.addUser(user);
	}
}
