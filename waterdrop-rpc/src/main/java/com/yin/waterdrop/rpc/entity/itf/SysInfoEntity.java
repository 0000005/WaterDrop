package com.yin.waterdrop.rpc.entity.itf;

import java.util.ArrayList;

public class SysInfoEntity 
{
	/**
	 * cpu相关
	 */
	private String cpuVendor;
	private String cpuModel;
	private int cpuMhz;
	private int cpuTotal;
	
	/**
	 * 内存相关
	 */
	private long RAMSize;
	
	/**
	 * 硬盘相关
	 */
	private ArrayList<HarddiskPartitionEntity> Hardisk = new ArrayList<HarddiskPartitionEntity>();
	
	
	/**
	 * 操作系统相关
	 */
	private String osName;
	private String jdkInfo;
	
	
	public String getCpuVendor() {
		return cpuVendor;
	}
	public void setCpuVendor(String cpuVendor) {
		this.cpuVendor = cpuVendor;
	}
	public String getCpuModel() {
		return cpuModel;
	}
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}
	public int getCpuMhz() {
		return cpuMhz;
	}
	public void setCpuMhz(int cpuMhz) {
		this.cpuMhz = cpuMhz;
	}
	public int getCpuTotal() {
		return cpuTotal;
	}
	public void setCpuTotal(int cpuTotal) {
		this.cpuTotal = cpuTotal;
	}
	public long getRAMSize() {
		return RAMSize;
	}
	public void setRAMSize(long rAMSize) {
		RAMSize = rAMSize;
	}
	public ArrayList<HarddiskPartitionEntity> getHardisk() {
		return Hardisk;
	}
	public void setHardisk(ArrayList<HarddiskPartitionEntity> hardisk) {
		Hardisk = hardisk;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getJdkInfo() {
		return jdkInfo;
	}
	public void setJdkInfo(String jdkInfo) {
		this.jdkInfo = jdkInfo;
	}
	
}
