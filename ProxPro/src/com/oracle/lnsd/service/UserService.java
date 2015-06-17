package com.oracle.lnsd.service;

import org.springframework.stereotype.Component;

@Component
public class UserService implements UserServiceInterface {
	
	@Override
	public void saveOrUpdate() {
		System.out.println("保存用户信息");
		int i = 1/0;
	}
	@Override
	public void delete() {
		System.out.println("删除一条用户信息");
	}
	@Override
	public void shearchByName() {
		System.out.println("检索一条用户记录通过姓名");
	}
	@Override
	public void shearchById() {
		System.out.println("检索一条用户记录通过id");
	}
}
