package com.gudnam.bringluck.qrcode.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;

import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.UserLottoVo;
import com.gudnam.bringluck.mypage.service.LottoResultService;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;
import com.gudnam.bringluck.util.BringluckUtil;


public class QRCodeService{
	
	private Activity activity = null;
	public UserLottoVo userLottoVo = new UserLottoVo();
	
	public QRCodeService(){}
	public QRCodeService(Activity activity){
		this.activity = activity;
	}
	
	public boolean setQRCodeContents(String contents){
		String host = contents.substring(0, contents.indexOf("/?"));
		String lottoAge = contents.substring(host.length()+4, host.length()+8);
		int nTemp = Integer.parseInt(lottoAge);
		lottoAge = String.valueOf(nTemp);
		String temp = host + "/?" + "v=" + "0000" + "m"; 
		String []lottoNum = new String[5];//contents.substring(temp.length(), temp.length() + 13);
		String strNowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		for( int cnt=0; cnt < 5; cnt++){
			int tempLen = temp.length();
			String buffer = contents.substring(tempLen, tempLen + 12);
			temp += buffer + "n";
			if( !BringluckUtil.isNumber(buffer)){
				return false;
			}
			if( buffer.equalsIgnoreCase(BLConfig.LOTTO_NULL)){
				continue;
			}
			
			lottoNum[cnt] = buffer.substring(0, 2) + "," + buffer.substring(2, 4) + "," + 
							buffer.substring(4, 6) + "," + buffer.substring(6, 8) + "," + 
							buffer.substring(8, 10) + "," + buffer.substring(10, 12); // GUDNAM END "," 삽입

			saveUserLottoInfo(lottoAge, lottoNum[cnt], strNowDate);
		}
		
		// 로또번호 저장 후 이미 당첨번호가 있다면 비교 
		int result = LottoResultService.resultMyLotto(activity, lottoAge);
		if( result >= BLConfig.LOTTO_THE_FAIL){
			// 로또 결과 화면에 출력 
		}
		
		return true;
	}
	
	public boolean saveUserLottoInfo(String lottoAge, String lottoNum, String nowDate){
		final Dialog dialog = BringluckUtil.setLoadingDialog(activity, "저장중...");
		dialog.show();
		
		BLSQLiteQuery.addUserLotto(new UserLottoVo(
										BLConfig.BL_ANDROID_ID, lottoAge, nowDate, lottoNum));
		// 유저 로또와 당첨번호 비교하여 저장 (당첨번호 없으면 무시)
		LottoResultService.resultMyLotto(activity, lottoAge);
		dialog.dismiss();
		
		return true;
	}
	
}
