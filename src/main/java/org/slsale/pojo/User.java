package org.slsale.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 */
public class User extends Base{
	
	private String loginCode;//登录账号
	private String password;//登录密码
	private String password2;//登录的二次密码
	private String userName;//用户名称
	private String sex;//用户性别
	private Date birthday;//生日
	private String cardType;//证件类型id
	private String cardTypeName;//证件类型名称
	private String idCard;//证件号码
	private String country;//收货国家
	private String mobile;//手机
	private String email;//邮箱
	private String userAddress;//地址
	private String postCode;//邮政编码
	private Date createTime;//创建时间
	private Integer referId;//推荐人id（默认为当前登录用户id）
	private String referCode;//推荐人编码（默认为当前登录用户loginCode）
	private Integer roleId;//所属角色ID
	private String roleName;//所属角色名称
	private String userType;//用户类型id
	private String userTypeName;//用户类型名称
	private Integer isStart;//是否启用（1、启用2、未启用）
	private Date lastUpdateTime;//最新更新时间
	private Date lastLoginTime;//最后登录时间
	private String bankName;//开户卡号
	private String accountHolder;//开户行
	private String bankAccount;//开户人
	private String idCardPicPath;//身份证照片存放路径
	private String bankPicPath;//银行卡照片存放路径

	public User(String loginCode, String password, String password2, String userName, String sex, Date birthday, String cardType, String cardTypeName, String idCard, String country, String mobile, String email, String userAddress, String postCode, Date createTime, Integer referId, String referCode, Integer roleId, String roleName, String userType, String userTypeName, Integer isStart, Date lastUpdateTime, Date lastLoginTime, String bankName, String accountHolder, String bankAccount, String idCardPicPath, String bankPicPath) {
		this.loginCode = loginCode;
		this.password = password;
		this.password2 = password2;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.cardType = cardType;
		this.cardTypeName = cardTypeName;
		this.idCard = idCard;
		this.country = country;
		this.mobile = mobile;
		this.email = email;
		this.userAddress = userAddress;
		this.postCode = postCode;
		this.createTime = createTime;
		this.referId = referId;
		this.referCode = referCode;
		this.roleId = roleId;
		this.roleName = roleName;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.isStart = isStart;
		this.lastUpdateTime = lastUpdateTime;
		this.lastLoginTime = lastLoginTime;
		this.bankName = bankName;
		this.accountHolder = accountHolder;
		this.bankAccount = bankAccount;
		this.idCardPicPath = idCardPicPath;
		this.bankPicPath = bankPicPath;
	}

	public User() {
	}

	public String getIdCardPicPath() {
		return idCardPicPath;
	}
	public void setIdCardPicPath(String idCardPicPath) {
		this.idCardPicPath = idCardPicPath;
	}
	public String getBankPicPath() {
		return bankPicPath;
	}
	public void setBankPicPath(String bankPicPath) {
		this.bankPicPath = bankPicPath;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getReferId() {
		return referId;
	}
	public void setReferId(Integer referId) {
		this.referId = referId;
	}
	public String getReferCode() {
		return referCode;
	}
	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Integer getIsStart() {
		return isStart;
	}
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "User{" +
				"loginCode='" + loginCode + '\'' +
				", password='" + password + '\'' +
				", password2='" + password2 + '\'' +
				", userName='" + userName + '\'' +
				", sex='" + sex + '\'' +
				", birthday=" + birthday +
				", cardType='" + cardType + '\'' +
				", cardTypeName='" + cardTypeName + '\'' +
				", idCard='" + idCard + '\'' +
				", country='" + country + '\'' +
				", mobile='" + mobile + '\'' +
				", email='" + email + '\'' +
				", userAddress='" + userAddress + '\'' +
				", postCode='" + postCode + '\'' +
				", createTime=" + createTime +
				", referId=" + referId +
				", referCode='" + referCode + '\'' +
				", roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				", userType='" + userType + '\'' +
				", userTypeName='" + userTypeName + '\'' +
				", isStart=" + isStart +
				", lastUpdateTime=" + lastUpdateTime +
				", lastLoginTime=" + lastLoginTime +
				", bankName='" + bankName + '\'' +
				", accountHolder='" + accountHolder + '\'' +
				", bankAccount='" + bankAccount + '\'' +
				", idCardPicPath='" + idCardPicPath + '\'' +
				", bankPicPath='" + bankPicPath + '\'' +
				'}';
	}
}
