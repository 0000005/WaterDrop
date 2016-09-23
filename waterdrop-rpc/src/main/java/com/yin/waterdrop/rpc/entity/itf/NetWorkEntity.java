package com.yin.waterdrop.rpc.entity.itf;

public class NetWorkEntity 
{
	private int index;
	//上行速度（每秒）
	private long upSpeed;
	//下行速度（每秒）
	private long downSpeed;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public long getUpSpeed() {
		return upSpeed;
	}
	public void setUpSpeed(long upSpeed) {
		this.upSpeed = upSpeed;
	}
	public long getDownSpeed() {
		return downSpeed;
	}
	public void setDownSpeed(long downSpeed) {
		this.downSpeed = downSpeed;
	}
}
