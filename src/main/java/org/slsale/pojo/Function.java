package org.slsale.pojo;

import java.util.Date;

/**
 * 功能类
 */
public class Function extends Base{
	
	private String functionCode;//功能编码
	private String functionName;//功能名称
	private String funcUrl;//功能的url
	private int parentId;//功能的父id
	private Date creationTime;//创建时间
	private Integer roleId;//角色id

	public Function(String functionCode, String functionName, String funcUrl, int parentId, Date creationTime, Integer roleId) {
		this.functionCode = functionCode;
		this.functionName = functionName;
		this.funcUrl = funcUrl;
		this.parentId = parentId;
		this.creationTime = creationTime;
		this.roleId = roleId;
	}

	public Function() {
	}

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Function{" +
				"functionCode='" + functionCode + '\'' +
				", functionName='" + functionName + '\'' +
				", funcUrl='" + funcUrl + '\'' +
				", parentId=" + parentId +
				", creationTime=" + creationTime +
				", roleId=" + roleId +
				'}';
	}
}
