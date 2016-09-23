package com.yin.waterdrop.rpc.server;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.yin.waterdrop.rpc.annotation.RpcService;
import com.yin.waterdrop.rpc.codec.RpcDecoder;
import com.yin.waterdrop.rpc.codec.RpcEncoder;
import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;
import com.yin.waterdrop.rpc.server.handler.RpcServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

@Component
public class RpcServer implements ApplicationContextAware, InitializingBean 
{
	private static ApplicationContext appCtx;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Map<String,Object> handlerMap = new HashMap<String,Object> ();
	
	
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		logger.info("启动rpc服务器！");
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		cachedThreadPool.execute
		(
			new Runnable() 
			{
				@Override
				public void run() 
				{
					EventLoopGroup boss = new NioEventLoopGroup();
					EventLoopGroup worker = new NioEventLoopGroup();
					try
					{
						ServerBootstrap bootstrap = new ServerBootstrap();
						bootstrap.group(boss, worker);
						bootstrap.channel(NioServerSocketChannel.class);
						bootstrap.childHandler(new ChannelInitializer<SocketChannel>() 
						{
							@Override
							protected void initChannel(SocketChannel sc) throws Exception 
							{
								sc.pipeline().addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0));
								sc.pipeline().addLast(new RpcDecoder(RpcRequest.class));
								sc.pipeline().addLast(new RpcEncoder(RpcResponse.class));
								sc.pipeline().addLast(new RpcServerHandler(handlerMap));
							}
						});
						ChannelFuture f =bootstrap.bind(8088).sync();
						f.channel().closeFuture().sync();
					}
					catch (InterruptedException e) 
					{
						logger.error("rpc server is down!!!",e);
					}
					finally
					{
						boss.shutdownGracefully();
						worker.shutdownGracefully();
					}
				}
			}
		);
		
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
		appCtx=applicationContext;
		logger.info("扫描RPC Service 完毕！ count:"+serviceBeanMap.size());
	}
	
	
}
