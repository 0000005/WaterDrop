package com.yin.waterdrop.bussiness.system.service;

import java.util.List;

import com.yin.waterdrop.bussiness.system.entity.Dict;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.BaseService;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yin.waterdrop.bussiness.system.entity.service.Dict
 */
public interface DictService extends BaseService<Dict>{

	/**
	 * 根据对应表字段获取
	 * 
	 * @param column
	 * @return
	 */
	List<Dict> selectByColumn(String column);

	List<Dict> listPage(Pagination pagination, Dict dict);

}
