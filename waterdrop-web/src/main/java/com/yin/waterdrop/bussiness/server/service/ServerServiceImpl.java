package com.yin.waterdrop.bussiness.server.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yin.waterdrop.bussiness.server.entity.Server;
import com.yin.waterdrop.bussiness.server.service.ServerService;
import com.yin.waterdrop.common.utils.Const;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yin.waterdrop.frame.pagePlugin.Pagination;
import com.yin.waterdrop.frame.service.impl.BaseServiceImpl;
import com.yin.waterdrop.frame.utils.ValidatorUtils;
import com.yin.waterdrop.rpc.client.Proxy.ObjectProxy;
import com.yin.waterdrop.rpc.entity.itf.SysInfoEntity;
import com.yin.waterdrop.rpc.rpcInterface.StatusInfoService;
import com.yin.waterdrop.rpc.rpcInterface.SysInfoService;
import com.yin.waterdrop.bussiness.server.dao.ServerDao;



/**
 * 
 * @author JerryYin <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.service.Server.Servers
 */
@Service("serversService")
public class ServerServiceImpl  extends BaseServiceImpl<Server> implements ServerService {
	private Logger logger = LoggerFactory.getLogger(getClass());
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
			int effectNum =0;
			String message = ValidatorUtils.validate(server);
			if(!StringUtils.isBlank(message))
			{
				map.put(Const.MSG, message);
				return map;
			}
			else
			{
				try
				{
					//链接探针检测服务器有效性
					SysInfoService sysInfoService=ObjectProxy.newProxyInstance(SysInfoService.class,server.getServerIp(), Integer.valueOf(server.getServerPort()));
					SysInfoEntity sysInfoEntity=sysInfoService.getSysInfo();
					if(sysInfoEntity==null)
					{
						map.put(Const.MSG, "保存失败，无法链接到主机！");
						return map;
					}
				}
				catch(Exception e)
				{
					map.put(Const.MSG, "保存失败，无法链接到主机！");
					return map;
				}
				
				
				if( StringUtils.isNotBlank(server.getId()))
				{
					//更新
					effectNum = updateByPrimaryKey(server);
				}
				else
				{
					//插入
					Server oldServer =serversDao.selectByIp(server.getServerIp(),server.getServerPort());
					if(oldServer!=null)
					{
						map.put(Const.MSG, "保存失败，此IP和端口的组合（"+oldServer.getServerName()+"）已经在库，不能重复添加！");
						return map;
					}
					else
					{
						server.setId(UUID.randomUUID().toString());
						server.setCreateTime(new Timestamp(new Date().getTime()));
						server.setUpdateTime(new Timestamp(new Date().getTime()));
						effectNum =save(server);
					}
				}
			}
			
			//判断成功
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
		catch(Exception e)
		{
			e.printStackTrace();
			map.put(Const.MSG, "保存失败，原因是:"+e.getMessage());
		}
		
		return map;
	}
}
