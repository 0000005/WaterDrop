package com.yin.waterdrop.rpc.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.Task;

import com.yin.waterdrop.rpc.client.handler.RpcClientHandler;
import com.yin.waterdrop.rpc.codec.RpcDecoder;
import com.yin.waterdrop.rpc.codec.RpcEncoder;
import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;
import com.yin.waterdrop.rpc.future.TaskPromise;
import com.yin.waterdrop.rpc.future.imp.DefaultTaskPromise;
import com.yin.waterdrop.rpc.server.handler.RpcServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class RpcClient 
{
	private static ExecutorService executor = Executors.newCachedThreadPool();
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public TaskPromise send(RpcRequest req) throws InterruptedException
	{
		TaskPromise promise = new DefaultTaskPromise();
		FutureTask futureTask =RpcClient.submit(new SendThread(req,promise));
		return promise;
	}
	
	
	//初始化handler
	class RpcClientInitializer extends ChannelInitializer<SocketChannel>
	{
		private RpcRequest req;
		private TaskPromise promise;
		
		public RpcClientInitializer(RpcRequest req, TaskPromise promise) {
			super();
			this.req = req;
			this.promise = promise;
		}

		@Override
		protected void initChannel(SocketChannel sc) throws Exception 
		{
			sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
			sc.pipeline().addLast(new RpcDecoder(RpcResponse.class));
			sc.pipeline().addLast(new RpcEncoder(RpcRequest.class));
			sc.pipeline().addLast(new RpcClientHandler(req,promise));
		}
	}
	
	class SendThread implements Callable<Object>
	{
		private RpcRequest req;
		private TaskPromise promise;
		
		public SendThread(RpcRequest req, TaskPromise promise) {
			super();
			this.req = req;
			this.promise = promise;
		}

		@Override
		public Object call() throws Exception 
		{
			EventLoopGroup worker = new NioEventLoopGroup();
			try
			{
				Bootstrap b = new Bootstrap();
				b.group(worker);
				b.channel(NioSocketChannel.class);
				b.handler(new RpcClientInitializer(req,promise));
				ChannelFuture future=b.connect(req.getHost(), req.getPort()).sync();
				future.channel().closeFuture().sync();
			}
			catch(Exception e)
			{
				promise.setFailure(e);
				logger.error("调用远程目标出错!",e);
			}
			finally
			{
				worker.shutdownGracefully();
			}
			return null;
		}
		
	}
	
	public static FutureTask<Object> submit(Callable<Object> call)
	{
		FutureTask<Object> task = new FutureTask<Object>(call);
		executor.execute(task);
		return task;
	}
}
