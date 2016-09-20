package com.yin.waterdrop.rpc.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.codec.RpcDecoder;
import com.yin.waterdrop.rpc.codec.RpcEncoder;
import com.yin.waterdrop.rpc.entity.RpcRequest;
import com.yin.waterdrop.rpc.entity.RpcResponse;
import com.yin.waterdrop.rpc.server.handler.RpcServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

@Component
public class RpcServer implements ApplicationContextAware, InitializingBean 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String,Object> handlerMap = new HashMap<String,Object> ();
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		System.out.println("启动rpc服务器！");
		logger.info("启动rpc服务器！");
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try
		{
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(boss, worker);
			bootstrap.channel(ServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() 
			{
				@Override
				protected void initChannel(SocketChannel sc) throws Exception 
				{
					sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
					sc.pipeline().addLast(new RpcDecoder(RpcResponse.class));
					sc.pipeline().addLast(new RpcEncoder(RpcRequest.class));
					sc.pipeline().addLast(new RpcServerHandler(handlerMap));
				}
			});
			ChannelFuture f =bootstrap.bind(8088).sync();
			f.channel().closeFuture().sync();
		}
		finally
		{
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException 
	{
		  Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
	        if (MapUtils.isNotEmpty(serviceBeanMap)) 
	        {
	        	Set<String> beanKey =serviceBeanMap.keySet();
	        	for (Iterator <String> iterator = beanKey.iterator(); iterator.hasNext();) 
	        	{
					String key = iterator.next();
					Object serviceBean = serviceBeanMap.get(key);
	                RpcService annotation =applicationContext.findAnnotationOnBean(key, RpcService.class);
	                String beanClassName =annotation.value().getName();
	                handlerMap.put(beanClassName, serviceBean);
				}
	        }
		System.out.println("扫描RPC Service 完毕！ count:"+serviceBeanMap.size());
		logger.info("扫描RPC Service 完毕！ count:"+serviceBeanMap.size());
	}
	
}
