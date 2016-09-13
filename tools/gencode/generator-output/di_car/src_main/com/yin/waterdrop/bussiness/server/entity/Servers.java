package com.yin.waterdrop.bussiness.server.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.commons.lang.StringUtils;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 
 * @author yang <Auto generate>
 * @version  2016-09-13 13:20:06
 * @see com.yin.waterdrop.bussiness.server.entity.Servers
 */
@Table(name = "t_servers")
public class Servers  implements Serializable{
	
	//columns START
	/**
	 * id
	 */
	@Id
	private Integer id;
	/**
	 * æœ�åŠ¡å™¨å��ç§°
	 */
	private String serverName;
	/**
	 * æœ�åŠ¡å™¨ip
	 */
	private String serverIp;
	/**
	 * æœ�åŠ¡å™¨ç«¯å�£
	 */
	private String serverPort;
	/**
	 * æœ�åŠ¡å™¨æ��è¿°
	 */
	private String serverDescription;
	/**
	 * æ˜¯å�¦æ˜¯ç”Ÿæˆ�çŽ¯å¢ƒï¼ˆ0å�¦ï¼Œ1æ˜¯ï¼‰
	 */
	private Integer isProductEnv;
	/**
	 * æ˜¯å�¦å�¯ç”¨(0:å�¯ç”¨ 1:ä¸�å�¯ç”¨)
	 */
	private Integer disabled;
	/**
	 * createTime
	 */
	private Timestamp createTime;
	/**
	 * updateTime
	 */
	private Timestamp updateTime;
	//columns END 数据库字段结束
	
	

	//get and set
	public void setId(Integer id) {
	    
		this.id = id;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	public void setServerName(String serverName) {
	    
		    if(StringUtils.isNotBlank(serverName)){
			 serverName=serverName.trim();
			}
		this.serverName = serverName;
	}
	
	
	public String getServerName() {
		return this.serverName;
	}
	public void setServerIp(String serverIp) {
	    
		    if(StringUtils.isNotBlank(serverIp)){
			 serverIp=serverIp.trim();
			}
		this.serverIp = serverIp;
	}
	
	
	public String getServerIp() {
		return this.serverIp;
	}
	public void setServerPort(String serverPort) {
	    
		    if(StringUtils.isNotBlank(serverPort)){
			 serverPort=serverPort.trim();
			}
		this.serverPort = serverPort;
	}
	
	
	public String getServerPort() {
		return this.serverPort;
	}
	public void setServerDescription(String serverDescription) {
	    
		    if(StringUtils.isNotBlank(serverDescription)){
			 serverDescription=serverDescription.trim();
			}
		this.serverDescription = serverDescription;
	}
	
	
	public String getServerDescription() {
		return this.serverDescription;
	}
	public void setIsProductEnv(Integer isProductEnv) {
	    
		this.isProductEnv = isProductEnv;
	}
	
	
	public Integer getIsProductEnv() {
		return this.isProductEnv;
	}
	public void setDisabled(Integer disabled) {
	    
		this.disabled = disabled;
	}
	
	
	public Integer getDisabled() {
		return this.disabled;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
	public void setCreateTime(Timestamp createTime) {
	    
		this.createTime = createTime;
	}
	
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}
		/*
	public String getupdateTimeString() {
		return DateUtils.convertDate2String(FORMAT_UPDATETIME, getupdateTime());
	}
	public void setupdateTimeString(String value) throws ParseException{
		setupdateTime(DateUtils.convertString2Date(FORMAT_UPDATETIME,value));
	}*/
	
	public void setUpdateTime(Timestamp updateTime) {
	    
		this.updateTime = updateTime;
	}
	
	
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id=").append(getId()).append(",")
			.append("serverName=").append(getServerName()).append(",")
			.append("serverIp=").append(getServerIp()).append(",")
			.append("serverPort=").append(getServerPort()).append(",")
			.append("serverDescription=").append(getServerDescription()).append(",")
			.append("isProductEnv=").append(getIsProductEnv()).append(",")
			.append("disabled=").append(getDisabled()).append(",")
			.append("createTime=").append(getCreateTime()).append(",")
			.append("updateTime=").append(getUpdateTime()).append(",")
			.toString();
	}
	
	
}

	
