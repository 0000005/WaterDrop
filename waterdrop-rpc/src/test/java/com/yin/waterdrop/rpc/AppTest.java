package com.yin.waterdrop.rpc;

import java.util.concurrent.ExecutionException;

import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.client.Proxy.ObjectProxy;
import com.yin.waterdrop.rpc.future.TaskPromise;
import com.yin.waterdrop.rpc.rpcInterface.StatusInfoService;

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
    	
    	
    	ObjectProxy<StatusInfoService> objectProxy = new ObjectProxy<StatusInfoService>(StatusInfoService.class);
    	TaskPromise promise =objectProxy.AsyncCall("reportSysInfo", "time2");
    	System.out.println("result:"+promise.getResponseResult());
	}
    
}
