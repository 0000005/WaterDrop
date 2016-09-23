package com.yin.waterdrop.rpc.entity.itf;

public class HarddiskPartitionEntity 
{
	//总大小
	private long totalSize;
	//已用大小
	private long usedSize;
	//挂载点
	private String mounted;
	//分区类型
	private String type;
	
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public long getUsedSize() {
		return usedSize;
	}
	public void setUsedSize(long usedSize) {
		this.usedSize = usedSize;
	}
	public String getMounted() {
		return mounted;
	}
	public void setMounted(String mounted) {
		this.mounted = mounted;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
