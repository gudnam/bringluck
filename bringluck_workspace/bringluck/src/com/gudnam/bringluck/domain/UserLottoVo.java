package com.gudnam.bringluck.domain;


public class UserLottoVo {

	private String userId;
	private String lottoAge;
	private String lottoDate;
	private String lottoNumber;
	
	public UserLottoVo(){}
	public UserLottoVo(String userId, String lottoAge
			, String lottoDate, String lottoNumber){
		this.userId = userId;
		this.lottoAge = lottoAge;
		this.lottoDate = lottoDate;
		this.lottoNumber = lottoNumber;
	}
	
	public String getUserId() {
		return userId;
	}
	public String getLottoNumber() {
		return lottoNumber;
	}
	public void setLottoNumber(String lottoNumber) {
		this.lottoNumber = lottoNumber;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLottoAge() {
		return lottoAge;
	}
	public void setLottoAge(String lottoAge) {
		this.lottoAge = lottoAge;
	}
	public String getLottoDate() {
		return lottoDate;
	}
	public void setLottoDate(String lottoDate) {
		this.lottoDate = lottoDate;
	}
}
