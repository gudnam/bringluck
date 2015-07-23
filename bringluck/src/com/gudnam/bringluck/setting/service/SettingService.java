package com.gudnam.bringluck.setting.service;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;

import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.UserLottoVo;
import com.gudnam.bringluck.util.BringluckUtil;


public class SettingService{
	
	private Activity activity = null;
	public UserLottoVo userLottoVo = new UserLottoVo();
	
	public SettingService(){}
	public SettingService(Activity activity){
		this.activity = activity;
	}
	
	public boolean saveLottoBackupData(String lottoNum, int lottoCount){
		final Dialog dialog = BringluckUtil.setLoadingDialog(activity, "���� ��...");
		// dialog.setCancelable(false);

		try {
			userLottoVo.setUserId(java.net.URLEncoder.encode(
					"gudnam", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( Exception ex){
			ex.printStackTrace();
		}

		DateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		String strNowDate = dtFormat.format(new Date());
		String api_url = "http://"
				+ BLConfig.BL_IP + ":"
				+ BLConfig.BL_PORT + "/"
				+ BLConfig.BL_API_NAME + "/"
				+ "addUserLotto?"
				+ "userId=" + userLottoVo.getUserId() + "&"
				+ "lottoCount=" + lottoCount + "&"
				+ "lottoAge=" + userLottoVo.getLottoAge() + "&"
				+ "lottoDate=" + strNowDate + "&"
				+ "lottoNumber=" + lottoNum; 
		
		Log.i("INFO", "api url : " + api_url);

//		if( !restful.sendHttpMessage_get(api_url)){
//			BringluckUtil.makeToastMsg(activity,
//					"�������ῡ ���� �Ͽ����ϴ�.(���ͳ� ������ Ȯ�� ���ּ���)");
//		}

		dialog.show();
		
		return true;
	}
	
}
