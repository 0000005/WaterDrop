package com.yin.waterdrop.bussiness.server.service;

import java.util.List;
import com.yin.waterdrop.bussiness.server.entity.Servers;
import com.yin.waterdrop.bussiness.server.service.ServersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;


import com.yin.waterdrop.bussiness.server.dao.ServersDao;



/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.service.impl.Servers
 */
@Service("serversService")
public class ServersServiceImpl  extends BaseServiceImpl<Servers> implements ServersService {
	@Autowired
    private ServersDao serversDao;
   
    public List<Servers> listPage(Servers servers, Pagination pagination){
		return serversDao.listPage(servers,pagination);
	}
}
