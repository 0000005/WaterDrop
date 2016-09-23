package com.yin.waterdrop.rpc.codec;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<Object> 
{
	private Class<?> processClass;
	
	public RpcEncoder(Class<?> processClass) 
	{
		this.processClass = processClass;
	}


	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception 
	{
		if(processClass.isInstance(msg))
		{
			//序列化要发送的对象
			byte[] data = SerializationUtil.serializer(msg);
			//先写4个字节的长度
			out.writeInt(data.length);
			//再写主体内容
			out.writeBytes(data);
		}
	}

}
