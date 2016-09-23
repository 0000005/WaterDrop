package com.yin.waterdrop.bussiness.rpcImp;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yin.waterdrop.common.init.ReportDataHandler;
import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.entity.itf.StatusEntity;
import com.yin.waterdrop.rpc.rpcInterface.StatusInfoService;

@RpcService(StatusInfoService.class)
public class StatusInfoServiceImp implements StatusInfoService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public boolean reportStatus(StatusEntity status)
	{
		boolean isSuccess=true;
		try 
		{
			ReportDataHandler.statusList.put(status);
		}
		catch (Exception e) 
		{
			logger.error("上报服务器状态出错",e);
			isSuccess=false;
		}
		return isSuccess;
	}

}
