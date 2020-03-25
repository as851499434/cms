package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

/**
 * 
* @ClassName: ArticleServiceImpl  
* @Description: 实现文章管理相关的逻辑类 
* @author WXL
* @date 2020年2月28日
 */
@Service
public class IArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void saveOrUpdateArticle(Article article) throws CustomerException {

		if(article == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE	, "参数为空");
		}
		
		if(article.getId() == null) {
			//需要额外添加两条数据
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articleMapper.insert(article);		
		}else {
			
			//article.setPublishdate(new Date());
			articleMapper.updateByPrimaryKey(article);
		}
		
		
	}

	@Override
	public void deleteArticleById(int id) throws CustomerException {

		articleMapper.deleteByPrimaryKey(id);
		
		
	}

	@Override
	public List<Article> findArticleByCondition(String keyStr, String condition) throws CustomerException {

		//分4种情况;
		/*
		 *1,没有添加任何条件,查询所有文章
		 *2,没有指定栏目,只指定了查询的关键字,则根据关键字查询所有满足条件的文章
		 *3.指定栏目没有指定关键字,根据栏目查询其中所有文章
		 *4.指定栏目,同时指定查询的关键字,则根据栏目和关键字查询满足条件的文章
		 */
		
		keyStr = keyStr == null ?"": keyStr.trim();
		condition =condition == null ?"": condition.trim();
		ArticleExample example = new ArticleExample();
		if("".equals(keyStr) && "".equals(condition)) {
			//qing情况1
			return articleMapper.selectByExample(example);
		}else if(!"".equals(keyStr) && "".equals(condition)) {
			//q情况2
			example.createCriteria().andTitleLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
			
		}else if(!"".equals(condition) && "".equals(keyStr)) {
			
			//情况3
			CategoryExample categoryExample = new CategoryExample();
			
			categoryExample.createCriteria().andNameEqualTo(condition);
			
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			if(category.size()>0) {
				
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}
			else {
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有指定的栏目");
	
			}
			//根据栏目信息找到里面的文章
			return articleMapper.selectByExample(example);
		}else {
			//情况4
			CategoryExample categoryExample = new CategoryExample();
			
			categoryExample.createCriteria().andNameEqualTo(condition);
			
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			
			example.createCriteria().andCategoryIdEqualTo(category.get(0).getId()).andTitleLike("%"+keyStr+"%");
			
			return articleMapper.selectByExample(example);
		}
		
		
		
	}

	@Override
	public Article findArticleById(int id) throws CustomerException {

		Article article = articleMapper.selectByPrimaryKey(id);
		
		Integer cli = article.getClicktimes() == null? 0:article.getClicktimes();
		article.setClicktimes(cli+1);
		
		this.saveOrUpdateArticle(article);
		
		return article; 
	
	}


	
	
	
}
