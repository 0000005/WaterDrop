package com.yin.waterdrop.bussiness.server.service;
import java.util.List;

import com.yin.waterdrop.bussiness.server.entity.Servers;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.BaseService;


/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.service.Servers
 */
public interface ServersService extends BaseService<Servers>{
	
	/**
	 * 分页查找
	 * 
	 * @param servers
	 * @param pagination
	 * @return
	 */
	List<Servers> listPage(Servers servers, Pagination pagination);
}
