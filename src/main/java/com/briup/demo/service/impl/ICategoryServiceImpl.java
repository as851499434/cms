package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class ICategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryMapper categoryMapper;
	
	//栏目的拓展dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public List<Category> findAllCategotys() throws CustomerException {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		if(category==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有栏目");
		}
		if(category.getId()==null) {
			categoryMapper.insert(category);
		}else {
			categoryMapper.updateByPrimaryKey(category);
		}
	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		//删栏目时，需要先删除栏目中的文章信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		categoryMapper.selectByPrimaryKey(id);
		return categoryMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public List<CategoryEx> fiandAllCategoryEx() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExById(int id) throws ClassCastException {
		
		return categoryExMapper.findCategoryExById(id);
	}

}
