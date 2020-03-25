package com.briup.demo.service;

import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.bean.ex.IndexResult;

/**
 * 首页数据管理的Service层接口
 * @author 别看了
 *
 */
public interface IIndexResultService {
	/**
	 * 查询首页需要的所有数据
	 * @return
	 * @throws ClassCastException
	 */
	 IndexResult findIndexAllResult() throws ClassCastException;
	 
	
}
