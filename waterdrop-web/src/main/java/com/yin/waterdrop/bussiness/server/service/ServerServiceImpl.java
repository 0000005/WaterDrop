package com.yin.waterdrop.bussiness.server.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yin.waterdrop.bussiness.server.entity.Server;
import com.yin.waterdrop.bussiness.server.service.ServerService;
import com.yin.waterdrop.common.utils.Const;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;
import com.yin.waterdrop.frame.utils.ValidatorUtils;
import com.yin.waterdrop.bussiness.server.dao.ServerDao;



/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.service.Server.Servers
 */
@Service("serversService")
public class ServerServiceImpl  extends BaseServiceImpl<Server> implements ServerService {
	@Autowired
    private ServerDao serversDao;
   
    public List<Server> listPage(Server server, Pagination pagination)
    {
		return serversDao.listPage(server,pagination);
	}

	@Override
	public Map<String, Object> saveServer(Server server) 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Const.STATUS, -1);
		try
		{
			Server oldServer =serversDao.selectByIp(server.getServerIp());
			String message = ValidatorUtils.validate(server);
			if(!StringUtils.isBlank(message))
			{
				map.put(Const.MSG, message);
			}
			else if(oldServer!=null)
			{
				map.put(Const.MSG, "保存失败，此ip已经在库，不能重复添加！");
			}
			else
			{
				server.setCreateTime(new Timestamp(new Date().getTime()));
				server.setUpdateTime(new Timestamp(new Date().getTime()));
				int effectNum =save(server);
				if(effectNum>0)
				{
					map.put(Const.STATUS, 0);
					map.put(Const.MSG, "保存成功");
				}
				else
				{
					map.put(Const.MSG, "保存失败");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			map.put(Const.MSG, "保存失败，原因是:"+e.getMessage());
		}
		
		return map;
	}
}
