package com.gudnam.bringluck.domain;


public class LottoVo {

	private String lottoAge;
	private String winningNumber;
	private String winningDate;
	
	public LottoVo(){}
	public LottoVo(String lottoAge, String winningNumber, String winningDate){
		this.lottoAge = lottoAge;
		this.winningNumber = winningNumber;
		this.winningDate = winningDate;
	}
	
	public String getLottoAge() {
		return lottoAge;
	}
	public void setLottoAge(String lottoAge) {
		this.lottoAge = lottoAge;
	}
	public String getWinningNumber() {
		return winningNumber;
	}
	public void setWinningNumber(String winningNumber) {
		this.winningNumber = winningNumber;
	}
	public String getWinningDate() {
		return winningDate;
	}
	public void setWinningDate(String winningDate) {
		this.winningDate = winningDate;
	}
}
