package com.gudnam.bringluck.qrcode.event;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLActivity;
import com.gudnam.bringluck.dialog.SettingDialog;

public class QRCodeEvent implements OnClickListener{

	private Activity activity = null;
	
	public QRCodeEvent(){}
	public QRCodeEvent(Activity activity){
		this.activity = activity;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		}
	}

}
