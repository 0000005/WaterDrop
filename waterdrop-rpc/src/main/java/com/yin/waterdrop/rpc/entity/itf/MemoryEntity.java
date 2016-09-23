package com.yin.waterdrop.rpc.entity.itf;

public class MemoryEntity 
{
	private long freeSize;
	private long usedSize;
	
	
	public long getFreeSize() {
		return freeSize;
	}
	public void setFreeSize(long freeSize) {
		this.freeSize = freeSize;
	}
	public long getUsedSize() {
		return usedSize;
	}
	public void setUsedSize(long usedSize)
	{
		this.usedSize = usedSize;
	}
	
}
