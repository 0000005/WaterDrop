package com.yin.waterdrop.rpc.entity.itf;

import java.util.List;

public class StatusEntity 
{
	private String serverId;
	private List<CpuCoreEntity> cpuCroes;
	private MemoryEntity memory;
	private List<NetWorkEntity> NetWorkEntity;
	
	
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public List<CpuCoreEntity> getCpuCroes() {
		return cpuCroes;
	}
	public void setCpuCroes(List<CpuCoreEntity> cpuCroes) {
		this.cpuCroes = cpuCroes;
	}
	public MemoryEntity getMemory() {
		return memory;
	}
	public void setMemory(MemoryEntity memory) {
		this.memory = memory;
	}
	public List<NetWorkEntity> getNetWorkEntity() {
		return NetWorkEntity;
	}
	public void setNetWorkEntity(List<NetWorkEntity> netWorkEntity) {
		NetWorkEntity = netWorkEntity;
	}

	
}
