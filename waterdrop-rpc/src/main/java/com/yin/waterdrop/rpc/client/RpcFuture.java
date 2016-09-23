package com.yin.waterdrop.rpc.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import com.yin.waterdrop.rpc.entity.rpc.RpcRequest;
import com.yin.waterdrop.rpc.entity.rpc.RpcResponse;

public class RpcFuture extends FutureTask<Object> {

    public RpcFuture(Callable<Object> callable) {
		super(callable);
	}

	private RpcResponse response;

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public void done(RpcResponse reponse) {
        this.response = reponse;
        done();
     }

   


}
