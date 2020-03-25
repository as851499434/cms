package com.briup.demo.mapper.ex;

import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 处理 查询栏目及其包含的文章信息
 * @author 别看了
 *
 */
public interface CategoryExMapper {
	/**
	 * 处理 查询栏目及其包含的文章信息
	 * @author 别看了
	 *
	 */
	List<CategoryEx> findAllCategoryExs();
	
	/**
	 * 通过id查询 对应栏目及其包含的文章信息
	 */
	CategoryEx findCategoryExById(int id);
	
}
