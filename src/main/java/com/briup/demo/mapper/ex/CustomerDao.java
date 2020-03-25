package com.briup.demo.mapper.ex;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	Customer findByUsernameAndPassword(String username,String password);
	
}
