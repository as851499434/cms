package com.briup.demo.service;
/**
 * 栏目相关的Service层
 * @author 别看了
 *
 */

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;

public interface ICategoryService {
	
	/**
	 * 查询所有的栏目
	 */
	public List<Category> findAllCategotys() throws CustomerException;
	
	/**
	 * 添加或修改栏目信息
	 */
	public void saveOrUpdateCategory(Category category) throws CustomerException;
	
	/**
	 * 根据id删除栏目信息
	 */
	public void deleteCategoryById(int id) throws CustomerException;
	
	/**
	 * 根据ID查找到指定的栏目信息 
	 */
	public Category findCategoryById(int id) throws CustomerException;
	
	/**
	 * 查询栏目信息并且级联查询包含文章的信息
	 * @return 
	 */
	public List<CategoryEx> fiandAllCategoryEx() throws CustomerException;
	
	 /**
	 * 查询栏目及其包含的文章所有数据
	 * (一个写在Category里)
	 */
	public CategoryEx findCategoryExById(int id) throws ClassCastException;
 
	
	
	
}
