package com.briup.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.mapper.ex.CustomerDao;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.CustomerException;
@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private CustomerDao customerDao;
	@Override
	public Customer findCustomerByUsernameAndPassword(String username,String password) throws CustomerException {
		return customerDao.findByUsernameAndPassword(username, password);
	}

}
