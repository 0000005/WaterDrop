package com.yin.waterdrop.bussiness.system.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yin.waterdrop.bussiness.system.entity.entity.Resources
 */
@Table(name="t_resources")
public class Resources implements Serializable {

	// columns START
	/**
	 * id
	 */
	@Id
	private Integer id;
	/**
	 * 父节点名称
	 */
	private Integer pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型:菜单or功能
	 */
	private Integer type;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * url
	 */
	private String url;
	/**
	 * 菜单编码
	 */
	private String permission;
	/**
	 * icon
	 */
	private String icon;
	/**
	 * state
	 */
	private Integer disabled;
	/**
	 * description
	 */
	private String description;

	@Transient
	private Boolean checked;// 是否被选中



	// columns END 数据库字段结束

	// get and set

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("pid=").append(getPid()).append(",").append("name=")
				.append(getName()).append(",").append("type=")
				.append(getType()).append(",").append("sort=")
				.append(getSort()).append(",").append("url=").append(getUrl())
				.append(",").append("permission=").append(getPermission())
				.append(",").append("icon=").append(getIcon()).append(",")
				.append("disabled=").append(getDisabled()).append(",")
				.append("description=").append(getDescription()).append(",")
				.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
