package com.briup.demo.service;

import com.briup.demo.bean.Customer;
import com.briup.demo.utils.CustomerException;

public interface ICustomerService {
	/**
	 * 查询用户名&密码
	 */
	Customer findCustomerByUsernameAndPassword(String username,String password) throws CustomerException;
	

}
