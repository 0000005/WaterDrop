package com.yin.waterdrop.rpc.rpcInterface;

import com.yin.waterdrop.rpc.entity.itf.StatusEntity;

public interface StatusInfoService 
{
	/**
	 * 向主服务器上报服务器当前信息
	 * @param status
	 * @return
	 */
	boolean reportStatus(StatusEntity status);
}
