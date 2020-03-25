package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.bean.LinkExample.Criteria;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
/**
 * 操作链接的service功能类
 * @author zcg
 *
 */
@Service
public class LinkServiceImpl implements ILinkService {
	@Autowired
	private LinkMapper linkMapper;
	
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		//参数为引用类型，要做判空处理
		if(link == null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空！");
		}
		//
		if(link.getId()==null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
		//linkMapper.insert(link);
	}

	@Override
	public List<Link> findAllLinks() throws CustomerException {
		LinkExample example = new LinkExample();
		List<Link> list = linkMapper.selectByExample(example);
		return list;
		//return linkMapper.selectByExample(new LinkExample());
	}

	@Override
	public void deleteLinkById(int id) throws CustomerException {
		linkMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Link> findLinksByName(String name) throws CustomerException {
		
		name = name ==null?"":name.trim();
		LinkExample example = new LinkExample();
		
		if("".equals(name)) {
			//如果搜索条件没写，返回所有数据
			return linkMapper.selectByExample(example);
		}else {
			//如果搜索条件不为null，返回满足条件数据
			Criteria criteria = example.createCriteria();
			criteria.andNameLike(name);
			return linkMapper.selectByExample(example);
		}
		

	}

}







