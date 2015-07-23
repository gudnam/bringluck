package com.gudnam.bringluck.main.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLActivity;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.gcm.GcmService;
import com.gudnam.bringluck.main.event.MainEvent;
import com.gudnam.bringluck.main.service.MainService;
import com.gudnam.bringluck.qrcode.service.QRCodeService;
import com.gudnam.bringluck.sqlite.BLSQLiteHelper;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;

public class MainActivity extends BLActivity {

	private MainEvent event;
	private GcmService gcmService;
	private ProgressDialog mDlg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_intro);
		
		BLActivity.actList.clear();
		BLActivity.actList.add(this);
		BLConfig.helper = new BLSQLiteHelper(this);

		// test();
		initialize();
		
		if( !isStart()){
			handler.sendEmptyMessage(0);
			return;
		}
		
		mDlg.show();
		MainService.getLotto(this);
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					while(isStart()){
						
						Thread.sleep(500);
					}
					handler.sendEmptyMessage(0);
				}
				catch(Exception e){
					
				}
			}
		});
		thread.start();
	}
	
	final Handler handler = new Handler()
    {
        @Override
		public void handleMessage(Message msg)
        {
        	switch(msg.what)
        	{
        	case 0:
        		mDlg.dismiss();
        		event.doExecuteQRCodeReader();
        		break;
        	}
        }
    };
	
	private void initialize(){
		// 첫실행 체크
		first();
		SharedPreferences preference = getSharedPreferences("launch", 0);
		SharedPreferences.Editor edit = preference.edit();
		edit.putBoolean("isStart", true);
		edit.commit();
		
		event = new MainEvent(this);
		gcmService = new GcmService(this);
		
		BLConfig.BL_ANDROID_ID = Secure.getString(getContentResolver(), Secure.ANDROID_ID);
		BLConfig.BL_GCM_ID = gcmService.registGCM();
		
		if( BLConfig.BL_ANDROID_ID != "" && BLConfig.BL_GCM_ID != ""){
			MainService.loginUser(this, BLConfig.BL_ANDROID_ID, BLConfig.BL_GCM_ID);
		}

		mDlg = new ProgressDialog(this);
		mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mDlg.setMessage("로또 정보 받아오는중");
		mDlg.setCancelable(false);
		mDlg.setIndeterminate(true);
	}
	
	private void first(){
		SharedPreferences preference = getSharedPreferences("launch", 0);
		boolean isFirst = preference.getBoolean("isFirst", false);
		
		if( isFirst){
			preference = getSharedPreferences("setting", 0);
			SharedPreferences.Editor edit = preference.edit();
			edit.putBoolean("isAlarm", true);
			edit.commit();
			

			preference = getSharedPreferences("launch", 0);
			edit = preference.edit();
			edit.putBoolean("isFirst", false);
			edit.commit();
		}
	}
	
	private boolean isStart(){
		SharedPreferences preference = getSharedPreferences("launch", 0);
		boolean isStart = preference.getBoolean("isStart", true);
		return isStart;
	}
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                
               String contents = intent.getStringExtra("SCAN_RESULT");
               String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
               
               if( contents == null || contents == ""){
            	   Toast.makeText(this, getResources().getString(R.string.noti_invalid_qrcode), Toast.LENGTH_SHORT).show();
            	   return;
               }
        	   Toast.makeText(this, getResources().getString(R.string.noti_qrcode_success).toString(), Toast.LENGTH_SHORT).show();
               new QRCodeService(this).setQRCodeContents(contents);
               
            
               // Handle successful scan
                                         
            } else if (resultCode == RESULT_CANCELED) {
               // Handle cancel
               Log.i("App","Scan unsuccessful");
            }
       }
	}
	
	private void test(){
		//BLSQLiteQuery.removeLottoByLottoAge(630);
		
		SharedPreferences preference = getSharedPreferences("test", 0);
		boolean isGCM = preference.getBoolean("isGCM", false);
	}
}
