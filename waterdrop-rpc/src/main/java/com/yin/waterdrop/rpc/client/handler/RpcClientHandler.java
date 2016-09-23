package com.yin.waterdrop.rpc.client.handler;

import com.yin.waterdrop.rpc.client.RpcFuture;
import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;
import com.yin.waterdrop.rpc.future.TaskPromise;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcClientHandler extends ChannelInboundHandlerAdapter
{

	private RpcRequest req;
	private TaskPromise promise ;
	
	public RpcClientHandler(RpcRequest req, TaskPromise promise) 
	{
		super();
		this.req = req;
		this.promise = promise;
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception 
	{
		ctx.writeAndFlush(req);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception 
	{
		RpcResponse response = (RpcResponse)msg;
		promise.setSuccess(response);
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception 
	{
		cause.printStackTrace();
		ctx.close();
	}
	
}
