package com.gudnam.bringluck.domain;

public class UserVo {
	private String userId;
	private String userPhoneNumber;
	private String gcmId;
	private String gcmUseFlag;
	
	public UserVo(){}
	public UserVo(String userId, String userPhoneNumber, 
					String gcmId, String gcmUseFlag){
		this.userId = userId;
		this.userPhoneNumber = userPhoneNumber;
		this.gcmId = gcmId;
		this.gcmUseFlag = gcmUseFlag;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public String getGcmUseFlag() {
		return gcmUseFlag;
	}
	public void setGcmUseFlag(String gcmUseFlag) {
		this.gcmUseFlag = gcmUseFlag;
	}
}
