package com.yin.waterdrop.rpc.client.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import com.yin.waterdrop.rpc.client.RpcClient;
import com.yin.waterdrop.rpc.client.RpcFuture;
import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;
import com.yin.waterdrop.rpc.future.TaskFuture;
import com.yin.waterdrop.rpc.future.TaskPromise;

public class ObjectProxy<T> implements InvocationHandler {

	private Class<T> clazz;

    public ObjectProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    //同步方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{
		if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
		
		RpcRequest request = new RpcRequest();
	    request.setRequestId(UUID.randomUUID().toString());
	    request.setClassName(method.getDeclaringClass().getName());
	    request.setMethodName(method.getName());
	    request.setParameterTypes(method.getParameterTypes());
	    request.setParameters(args);
		
	    TaskPromise promise =new RpcClient().send(request);
	    TaskFuture future =promise.getFuture();
	    RpcResponse reps =(RpcResponse) future.get();
		return reps.getResult();
	}
	
	
	/**
	 * 异步调用rpc
	 * @param funcName 要调用的方法
	 * @param args 传入的参数
	 * @return
	 * @throws InterruptedException 
	 */
	public TaskPromise AsyncCall(String funcName, Object... args) throws InterruptedException
	{
		RpcRequest request = new RpcRequest();
	    request.setRequestId(UUID.randomUUID().toString());
	    request.setClassName(this.clazz.getName());
	    request.setMethodName(funcName);
	    request.setParameters(args);
	    
	    Class[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) 
        {
            parameterTypes[i] = getClassType(args[i]);
        }
        request.setParameterTypes(parameterTypes);
        
        TaskPromise promise =new RpcClient().send(request);
		return promise;
	}
	
	
	private Class<?> getClassType(Object obj){
        Class<?> classType = obj.getClass();
        String typeName = classType.getName();
        if(typeName.equals("java.lang.Integer"))
        {
        	return Integer.TYPE;
        }
        else if(typeName.equals("java.lang.Long"))
        {
        	return Long.TYPE;
        }
        else if(typeName.equals("java.lang.Float"))
        {
        	return Float.TYPE;
        }
        else if(typeName.equals("java.lang.Double"))
        {
        	return Double.TYPE;
        }
        else if(typeName.equals("java.lang.Character"))
        {
        	return Character.TYPE;
        }
        else if(typeName.equals("java.lang.Boolean"))
        {
        	return Boolean.TYPE;
        }
        else if(typeName.equals("java.lang.Short"))
        {
        	return Short.TYPE;
        }
        else if(typeName.equals("java.lang.Byte"))
        {
        	return Byte.TYPE;
        }
        else
        {
        	return classType;
        }
        	
    }
	
	
	@SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new ObjectProxy<T>(interfaceClass)
        );
    }

}
