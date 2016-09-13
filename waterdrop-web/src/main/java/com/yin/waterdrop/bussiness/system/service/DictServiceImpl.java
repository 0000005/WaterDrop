package com.yin.waterdrop.bussiness.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yin.waterdrop.bussiness.system.dao.DictDao;
import com.yin.waterdrop.bussiness.system.entity.Dict;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yin.waterdrop.bussiness.system.entity.service.impl.Dict
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {
	@Autowired
	private DictDao dictDao;

	@Override
	public List<Dict> selectByColumn(String column) {
		return dictDao.selectByColumn(column);
	}

	@Override
	public List<Dict> listPage(Pagination pagination, Dict dict) {
		return dictDao.listPage(pagination, dict);
	}

}
