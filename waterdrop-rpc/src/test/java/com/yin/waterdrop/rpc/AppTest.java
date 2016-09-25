package com.yin.waterdrop.rpc;

import java.util.concurrent.ExecutionException;

import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.client.Proxy.ObjectProxy;
import com.yin.waterdrop.rpc.entity.itf.SysInfoEntity;
import com.yin.waterdrop.rpc.future.TaskPromise;
import com.yin.waterdrop.rpc.rpcInterface.StatusInfoService;
import com.yin.waterdrop.rpc.rpcInterface.SysInfoService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase
{
    public static void main(String[] args) throws InterruptedException, ExecutionException 
    {
		//SysInfoService proxyService=(SysInfoService) ObjectProxy.newProxyInstance(SysInfoService.class);
		//String result=proxyService.reportSysInfo("time");
		//System.out.println("rpc call return :"+result);
    	
    	//链接探针检测服务器有效性
		SysInfoService sysInfoService=ObjectProxy.newProxyInstance(SysInfoService.class,"127.011.1", 8088);
		SysInfoEntity sysInfoEntity=sysInfoService.getSysInfo();
		System.out.println("sysInfoEntity:"+sysInfoEntity);
    	
    	
    	//ObjectProxy<StatusInfoService> objectProxy = new ObjectProxy<StatusInfoService>(StatusInfoService.class,"127.0.0.1",8088);
    	//TaskPromise promise =objectProxy.AsyncCall("reportSysInfo", "time2");
    	//System.out.println("result:"+promise.getResponseResult());
	}
    
}
