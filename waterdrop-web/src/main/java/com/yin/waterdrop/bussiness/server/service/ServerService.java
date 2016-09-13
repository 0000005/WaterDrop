package com.yin.waterdrop.bussiness.server.service;
import java.util.List;
import java.util.Map;

import com.yin.waterdrop.bussiness.server.entity.Server;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.BaseService;


/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.service.Server
 */
public interface ServerService extends BaseService<Server>{
	
	/**
	 * 分页查找
	 * 
	 * @param server
	 * @param pagination
	 * @return
	 */
	List<Server> listPage(Server server, Pagination pagination);
	
	Map<String, Object> saveServer(Server server);
}
