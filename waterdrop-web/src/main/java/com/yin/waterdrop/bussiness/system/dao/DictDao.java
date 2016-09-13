package com.yin.waterdrop.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yin.waterdrop.bussiness.system.entity.Dict;
import com.yin.waterdrop.frame.dao.MyMapper;
import com.yin.waterdrop.frame.pagePlugin.Pagination;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-31 15:46:09
 * @see com.yin.waterdrop.bussiness.system.entity.dao.Dict
 */
public interface DictDao extends MyMapper<Dict> {

	List<Dict> selectByColumn(String column);

	List<Dict> listPage(@Param("pagination") Pagination pagination,
			@Param("dict") Dict dict);

}
