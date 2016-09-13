package com.yin.waterdrop.bussiness.server.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.yin.waterdrop.bussiness.server.entity.Servers;
import com.yin.waterdrop.frame.dao.MyMapper;
import com.yin.waterdrop.frame.pagePlugin.Pagination;

/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.dao.Servers
 */
public interface ServersDao extends MyMapper<Servers> {
	
	/**
	 * 分页查找
	 * 
	 * @param servers
	 * @param pagination
	 * @return
	 */
	List<Servers> listPage(@Param("servers") Servers servers,
			@Param("pagination") Pagination pagination);
}
