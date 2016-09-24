package com.yin.waterdrop.rpc.rpcInterface;

import com.yin.waterdrop.rpc.entity.itf.SysInfoEntity;

/**
 * 此接口主要用作 中控服务端掉探针获取 探针服务器的硬件配置信息
 * @author Administrator
 *
 */
public interface SysInfoService 
{
	SysInfoEntity getSysInfo();
}
