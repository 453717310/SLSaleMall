package org.slsale.pojo;

import java.util.Date;

/**
 * 权限表
 * @author dll
 */
public class Authority extends Base{
	/**
	 * 角色id
	 */
	private int roleId;
	/**
	 * 功能id
	 */
	private int functionId;
	/**
	 * 用户类型id
	 */
	private int userTypeId;
	/**
	 * 创建时间
	 */
	private Date creationTime;
	/**
	 * 创建者
	 */
	private String createdBy;

	public Authority() {
	}

	public Authority(int roleId, int functionId, int userTypeId, Date creationTime, String createdBy) {
		this.roleId = roleId;
		this.functionId = functionId;
		this.userTypeId = userTypeId;
		this.creationTime = creationTime;
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"roleId=" + roleId +
				", functionId=" + functionId +
				", userTypeId=" + userTypeId +
				", creationTime=" + creationTime +
				", createdBy='" + createdBy + '\'' +
				'}';
	}
}
