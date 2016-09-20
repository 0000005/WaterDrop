package com.yin.waterdrop.rpc.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RpcDecoder extends ByteToMessageDecoder {

	private Class<?> processClass;
	
	public RpcDecoder(Class<?> processClass) 
	{
		this.processClass = processClass;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception 
	{
		if(in.readableBytes()<4)
		{
			//没有可读信息
			return;
		}
		in.markReaderIndex();
		//先读取  数据长度
		int dataLength =in.readInt();
		if(in.readableBytes()<dataLength)
		{
			//没有可读信息
			return ;
		}
		byte [] data =new byte[dataLength];
		in.readBytes(data);
		//反序列化
		Object obj=SerializationUtil.deserialize(data, processClass);
		out.add(obj);
	}

}
