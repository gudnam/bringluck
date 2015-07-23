package com.gudnam.bringluck.main.event;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLActivity;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.login.activity.LoginActivity;
import com.gudnam.bringluck.main.service.MainService;
import com.gudnam.bringluck.qrcode.activity.QRCodeActivity;

public class MainEvent implements OnClickListener, OnCheckedChangeListener{

	private Activity activity = null;
	
	public MainEvent(){}
	public MainEvent(Activity activity){
		this.activity = activity;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_mypage : 
			BLActivity.intentMypageActivity(activity);
			break;
		case R.id.rl_alarm_check :
			SharedPreferences preference = activity.getSharedPreferences("setting", 0);
			SharedPreferences.Editor edit = preference.edit();
			View checkView = v.findViewById(R.id.iv_check);
			if( checkView.getVisibility() == View.VISIBLE){
				checkView.setVisibility(View.INVISIBLE);
				edit.putBoolean("isAlarm", false);
			} else {
				checkView.setVisibility(View.VISIBLE);
				edit.putBoolean("isAlarm", true);
			}
			edit.commit();
			break;
		}
	}
	
	public void doExecuteQRCodeReader(){
		Intent intent = new Intent(activity, QRCodeActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}
	
	public void doLogin(){
		Intent intent = new Intent(activity, LoginActivity.class);
		activity.startActivity(intent);
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		SharedPreferences preference = activity.getSharedPreferences("setting", 0);
		SharedPreferences.Editor edit = preference.edit();
		edit.putBoolean("isAlarm", isChecked);
		edit.commit();
		
		//MainService.settingAlarm(activity, BLType.BL_ANDROID_ID, isChecked);
	}
}
