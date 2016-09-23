package com.yin.waterdrop.rpc.entity.itf;
/**
 * 具体每个cpu核心的信息
 * @author JerryYin
 *
 */
public class CpuCoreEntity 
{
	//第几个核心
	private int index;
	//用户态使用的cpu时间比
	private double userTime;
	// 系统使用率
	private double sysTime;
	// 当前空闲率
	private double idleTime;
	// 当前等待率
	private double waitTime;
	//用做nice加权的进程分配的用户态cpu时间比
	private double niceTime;
	// 总的使用率
	private double combined;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getUserTime() {
		return userTime;
	}
	public void setUserTime(double userTime) {
		this.userTime = userTime;
	}
	public double getSysTime() {
		return sysTime;
	}
	public void setSysTime(double sysTime) {
		this.sysTime = sysTime;
	}
	public double getIdleTime() {
		return idleTime;
	}
	public void setIdleTime(double idleTime) {
		this.idleTime = idleTime;
	}
	public double getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}
	public double getNiceTime() {
		return niceTime;
	}
	public void setNiceTime(double niceTime) {
		this.niceTime = niceTime;
	}
	public double getCombined() {
		return combined;
	}
	public void setCombined(double combined) {
		this.combined = combined;
	}
	
}
