package com.yin.waterdrop.rpc;

import javax.print.DocFlavor.READER;

import com.yin.waterdrop.rpc.codec.SerializationUtil;
import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;

public class SeriTest 
{

	public static void main(String[] args) 
	{
		RpcRequest request = new RpcRequest();
		request.setRequestId("11111");
		
		byte [] data =SerializationUtil.serializer(request);
		
		request=SerializationUtil.deserializer(data, RpcRequest.class);
		System.out.println(request.getRequestId());
	}

}
