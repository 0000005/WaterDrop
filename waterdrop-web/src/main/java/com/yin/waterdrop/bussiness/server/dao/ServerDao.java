package com.yin.waterdrop.bussiness.server.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.yin.waterdrop.bussiness.server.entity.Server;
import com.yin.waterdrop.bussiness.system.entity.Account;
import com.yin.waterdrop.frame.dao.MyMapper;
import com.yin.waterdrop.frame.pagePlugin.Pagination;

/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.dao.Server
 */
public interface ServerDao extends MyMapper<Server> {
	
	/**
	 * 分页查找
	 * 
	 * @param server
	 * @param pagination
	 * @return
	 */
	List<Server> listPage(@Param("server") Server server,@Param("pagination") Pagination pagination);
	
	/**
	 * 根据ip地址来查
	 * 
	 * @param serverIp
	 * @return
	 */
	Server selectByIp(@Param("serverIp")String serverIp);
}
