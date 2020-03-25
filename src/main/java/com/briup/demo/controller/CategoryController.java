package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(description = "栏目相关接口")
public class CategoryController {
	//栏目的dao
	@Autowired
	private ICategoryService CategoryService;
	//文章的dao
	
	@GetMapping("/getAllCategory")
	@ApiOperation("获取所有栏目")
	public Message<List<Category>> findAllCategorys(Category category){
		List<Category> list = CategoryService.findAllCategotys();
		return MessageUtil.success(list);
		}
	
	@PostMapping("/saveCategory")
	@ApiOperation("新增栏目")
	public Message<String> SaveCategory(Category category){
		try {
			CategoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (Exception e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
			
		}
	
	}
	
	@PostMapping("/deleteCategoryById")
	@ApiOperation("删除栏目信息")
	public Message<String> deleteCategoryById(int id){
		CategoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
		
	@PostMapping("/findCategoryById")
	@ApiOperation("指定Id查找栏目信息")
	public Message<String> findCategoryById(int id){
		CategoryService.findCategoryById(id);
		return MessageUtil.success();
	}
	
	/**
	 * 根据ID查找栏目以及包含的所有文章信息
	 */
	@GetMapping("/findCategoryExById")
	@ApiOperation("根据ID查找栏目以及包含的所有文章信息")
	public Message<CategoryEx> findCategoryExById(int id){
		return MessageUtil.success(CategoryService.findCategoryExById(id));
	}
	
	}
