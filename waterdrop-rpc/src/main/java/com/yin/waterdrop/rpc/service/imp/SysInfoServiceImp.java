package com.yin.waterdrop.rpc.service.imp;

import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.service.SysInfoService;

@RpcService(SysInfoService.class)
public class SysInfoServiceImp implements SysInfoService {

	@Override
	public String reportSysInfo(String info) 
	{
		return null;
	}

}
