package com.yin.waterdrop.rpc.server.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

public class RpcServerHandler extends ChannelInboundHandlerAdapter 
{
	private static final Logger logger = LoggerFactory.getLogger(RpcServerHandler.class);
	private final Map<String, Object> handlerMap;

    public RpcServerHandler(Map<String, Object> handlerMap) 
    {
        this.handlerMap = handlerMap;
    }
    
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception 
	{
		RpcRequest request = (RpcRequest)msg;
		RpcResponse response = new RpcResponse();
		response.setRequestId(request.getRequestId());
		
		try
		{
			Object result = handle(request);
			response.setResult(result);
		}
		catch(InvocationTargetException e)
		{
			response.setError(e.getMessage());
			logger.error("rpc error!!!",e);
		}
		
		ctx.writeAndFlush(response);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		ctx.close();
		logger.error("rpc error!!!",cause);
	}
	
	//采用cglib动态代理 调用服务器方法
	private Object handle(RpcRequest request) throws InvocationTargetException
	{
		String className = request.getClassName();
		Object serviceBean = handlerMap.get(className);
		
		FastClass serviceClass = FastClass.create(serviceBean.getClass());
		FastMethod serviceMethod =serviceClass.getMethod(request.getMethodName(), request.getParameterTypes());
		return serviceMethod.invoke(serviceBean, request.getParameters());
	}
	
}
