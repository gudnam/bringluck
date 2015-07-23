package com.gudnam.bringluck.main.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.gudnam.bringluck.common.BLApiList;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.network.BLRestful;

public class MainService {
	
	// DB 서버에서 가져온 Lotto 정보를 로컬 DB 에 저장
	public static void getLotto(Activity activity){
		BLRestful restful = new BLRestful(activity);
		
		String url = "http://"
				+ BLConfig.BL_IP + "/"
				+ BLConfig.BL_API_NAME + "/"
				+ BLApiList.BL_API_GET_LOTTO_ALL	;
		
		Log.i("INFO", "api url : " + url);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("key", "bringluck"));
        
		restful.sendHttpMessageByGetLotto(url, nameValuePairs);		
	}
	
	public static void loginUser(Activity activity, String deviceId, String regId){
		BLRestful restful = new BLRestful(activity);
		
		String url = "http://"
				+ BLConfig.BL_IP + "/"
				+ BLConfig.BL_API_NAME + "/"
				+ BLApiList.BL_API_ADD_USER;
		
		Log.i("INFO", "api url : " + url);
		Log.i("INFO", "post data : user_id[" + deviceId +"] user_phone_number[] gcm_id[" + regId + "] gcm_use_flag[N]");
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
        nameValuePairs.add(new BasicNameValuePair("userId", deviceId));
        nameValuePairs.add(new BasicNameValuePair("userPhoneNumber", "01011112222"));
        nameValuePairs.add(new BasicNameValuePair("gcmId", regId));
		nameValuePairs.add(new BasicNameValuePair("gcmUseFlag", "Y"));
        
		restful.sendHttpMessageByAddUser(url, nameValuePairs);
	}
	
//	public static void settingAlarm(Activity activity, String deviceId, boolean isAlarm){
//		BLRestful restful = new BLRestful(activity);
//		String url = "http://"
//				+ BLType.BL_IP + "/"
//				+ BLType.BL_API_NAME + "/"
//				+ BLApiList.BL_API_UPDATE_ALARM;
//		
//		
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
//        nameValuePairs.add(new BasicNameValuePair("userId", deviceId));
//		if( isAlarm)
//			nameValuePairs.add(new BasicNameValuePair("gcmUseFlag", "Y"));
//		else
//			nameValuePairs.add(new BasicNameValuePair("gcmUseFlag", "N"));
//        
//		restful.sendHttpMessageByAddUser(url, nameValuePairs);
//	}
}
