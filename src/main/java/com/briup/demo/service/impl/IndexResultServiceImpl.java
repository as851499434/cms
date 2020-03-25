package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.Link;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.mapper.ex.CategoryExMapper;
import com.briup.demo.service.IArticleService;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;


/**
 * 查询首页功能的所有方法
 * @author 别看了
 *
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService{

	//关联超链接的service层接口
	@Autowired
	private ILinkService linkService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public IndexResult findIndexAllResult() throws ClassCastException {
		
		IndexResult indexResult = new IndexResult();
		//设置所有的超链接信息
		List<Link> links = linkService.findAllLinks();
		indexResult.setLinks(links);
		//设置所有栏目及其包含的文章信息
		List<CategoryEx> categoryExs = categoryService.fiandAllCategoryEx();
		indexResult.setCategoryExs(categoryExs);
		return indexResult;
	}

}
