package com.yin.waterdrop.common.init;

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.yin.waterdrop.bussiness.server.dao.ServerDao;
import com.yin.waterdrop.rpc.entity.itf.StatusEntity;
import com.yin.waterdrop.rpc.server.RpcServer;

//上报数据处理
@Component("reportDataHandler")
public class ReportDataHandler 
{
	public static BlockingQueue<StatusEntity> statusList= new LinkedBlockingQueue<StatusEntity>();
	
	@Autowired
    private DataSource dataSource;
	
	@PostConstruct
	public void process()
	{
		new Thread(new Runnable() 
		{
			private Logger logger = LoggerFactory.getLogger(getClass());
			@Override
			public void run() 
			{
				String sql="INSERT INTO `t_server_status` (`id`, `server_id`, `memory`, `cpu_cores`, `network`, `create_time`) VALUES (?, ?, ?, ?, ?, now());";
				while(true)
				{
					try 
					{
						StatusEntity status =statusList.take();
						
						QueryRunner run = new QueryRunner( dataSource );
						String id =UUID.randomUUID().toString();
						String memory =JSON.toJSONString(status.getMemory());
						String cpuCores =JSON.toJSONString(status.getCpuCroes());
						String network =JSON.toJSONString(status.getNetWorkEntity());
						int affectNum =run.update(sql, id,status.getServerId(),memory,cpuCores,network);
						if(affectNum==0)
						{
							new Exception("保存数据失败  serverId:"+status.getServerId());
						}
						Thread.sleep(300);
					} 
					catch (Exception e) 
					{
						logger.error("处理上报数据时发生异常!",e);
					} 
				}
			}
		}).start();
	}

	public DataSource getDataSource() 
	{
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) 
	{
		this.dataSource = dataSource;
	}
	
}
