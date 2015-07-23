package com.gudnam.bringluck.domain;


public class LottoResultVo {

	private String userId;
	private String lottoAge;
	private String lottoNumber;
	private String lottoDate;
	private String rank;
	private String winningNumber;
	private String winningDate;
	private String sameNumber;
	
	public LottoResultVo(){}
	public LottoResultVo(String userId, String lottoAge, String lottoNumber, String lottoDate, 
					String rank, String winningNumber, String winningDate, String sameNumber){
		this.userId = userId;
		this.lottoAge = lottoAge;
		this.lottoNumber = lottoNumber;
		this.lottoDate = lottoDate;
		this.rank = rank;
		this.winningNumber = winningNumber;
		this.winningDate = winningDate;
		this.sameNumber = sameNumber;
	}
	
	public String getUserId() {
		return userId;
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
	public String getLottoNumber() {
		return lottoNumber;
	}
	public void setLottoNumber(String lottoNumber) {
		this.lottoNumber = lottoNumber;
	}
	public String getLottoDate() {
		return lottoDate;
	}
	public void setLottoDate(String lottoDate) {
		this.lottoDate = lottoDate;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getWinningNumber() {
		return winningNumber;
	}
	public void setWinningNumber(String winningNumber) {
		this.rank = winningNumber;
	}
	public String getWinningDate() {
		return winningDate;
	}
	public void setWinningDate(String winningDate) {
		this.rank = winningDate;
	}
	public String getSameNumber() {
		return sameNumber;
	}
	public void setSameNumber(String sameNumber) {
		this.sameNumber = sameNumber;
	}
}
