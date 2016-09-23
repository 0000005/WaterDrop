package com.yin.waterdrop.rpc;

import java.util.concurrent.ExecutionException;

import com.yin.waterdrop.rpc.future.TaskPromise;
import com.yin.waterdrop.rpc.future.imp.DefaultTaskPromise;

public class FutrueTest 
{
	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		final TaskPromise promise = new DefaultTaskPromise();
		
		new Thread(new Runnable() {
			public void run() 
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				promise.setSuccess("ok");
			}
		}).start();
		System.out.println("等待结果....");
		System.out.println(promise.get());
	}
}
