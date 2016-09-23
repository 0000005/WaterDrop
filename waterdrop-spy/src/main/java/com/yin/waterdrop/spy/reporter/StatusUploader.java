package com.yin.waterdrop.spy.reporter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yin.waterdrop.rpc.client.Proxy.ObjectProxy;
import com.yin.waterdrop.rpc.entity.itf.StatusEntity;
import com.yin.waterdrop.rpc.rpcInterface.StatusInfoService;

public class StatusUploader implements Runnable
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static StatusEntity createStatusEntity() throws SigarException, InterruptedException
	{
		Sigar sigar = new Sigar();
		StatusEntity status =new StatusEntity();
		status.setServerId("123456789");
		status.setMemory(MemoryGeter.getCurrMemory(sigar));
		status.setCpuCroes(CpuGeter.getCurrCpuStatus(sigar));
		status.setNetWorkEntity(NetworkGeter.getCurrNetworkStatus(sigar));
		
		return status;
	}
	
	//30秒上报一次服务器信息
	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				long startTime = System.currentTimeMillis();
				StatusInfoService proxyService=(StatusInfoService) ObjectProxy.newProxyInstance(StatusInfoService.class);
				boolean result=proxyService.reportStatus(StatusUploader.createStatusEntity());
				long endTime = System.currentTimeMillis();
				if((endTime-startTime)<30000)
				{
					Thread.sleep(30000-(endTime-startTime));
				}
			}
			catch(Exception e)
			{
				logger.error("上报服务器状态失败!",e);
			}
		}
	}
}
