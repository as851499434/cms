package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.service.ICustomerService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description = "用户模块")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/login")
	@ApiOperation("登录")
	public Message<Customer> login(String username,String password){
		Customer customer = customerService.findCustomerByUsernameAndPassword(username, password);
		if (customer==null) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "用户名或密码错误");
		}else {
			return MessageUtil.success(customer);
		}
	}
	

}
