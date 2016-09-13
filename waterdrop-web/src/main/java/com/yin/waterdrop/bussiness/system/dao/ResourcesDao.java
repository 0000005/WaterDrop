package com.yin.waterdrop.bussiness.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yin.waterdrop.bussiness.system.entity.Resources;
import com.yin.waterdrop.frame.dao.MyMapper;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yin.waterdrop.bussiness.system.entity.dao.Resources
 */
public interface ResourcesDao extends MyMapper<Resources> {

	List<Resources> findByAccountId(Long id);

	List<Resources> list(@Param("resource") Resources resource);

	List<Resources> findByRoleId(Long id);
}
