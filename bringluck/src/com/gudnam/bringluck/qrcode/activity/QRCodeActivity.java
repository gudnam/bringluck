package com.gudnam.bringluck.qrcode.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLFragmentActivity;
import com.gudnam.bringluck.common.event.BLEvent;
import com.gudnam.bringluck.main.fragment.MainFragment;
import com.gudnam.bringluck.qrcode.service.QRCodeService;
import com.trhura.android.zbar.scanner.ZBarConstants;
import com.trhura.android.zbar.scanner.ZBarFragment;
import com.trhura.android.zbar.scanner.ZBarScanner;

public class QRCodeActivity extends BLFragmentActivity implements ZBarFragment.ResultListener {

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private static final String TAG = "ZBarFragment";
    private GestureDetector gestureDetector;
    private ZBarScanner scanner;
    private BLEvent blEvent = null;
    private AdView ad_qrcode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        BLFragmentActivity.frgActList.clear();
        BLFragmentActivity.frgActList.add(this);
        setContentView(R.layout.layout_main);
        
        blEvent = new BLEvent(this);
        ad_qrcode = (AdView) findViewById(R.id.ad_qrcode);
        
        AdRequest adRequest = new AdRequest.Builder().build();
        if( ad_qrcode != null)
        	ad_qrcode.loadAd(adRequest);
        
        if( savedInstanceState == null){
            ZBarFragment zBarFragment = 
            		(ZBarFragment) getSupportFragmentManager().findFragmentById(R.id.scan_fragment);
            scanner = zBarFragment.getScanner();
            int[] modes = new int[] {ZBarConstants.CODE128, ZBarConstants.QRCODE};
            scanner.setModes(modes);
            
            getSupportFragmentManager().beginTransaction()
            		.add(R.id.ll_main, new MainFragment()).commit();
        }    
        Log.d("INFO", "[QRCodeActivity] OnCreate End");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("INFO", "[QRCodeActivity] OnResume End");
    }

    @Override
    public void onPause()
    {
        super.onResume();

        if (scanner != null && scanner.isScanning()) {
            scanner.stopScanning();
        }
    }

    @Override
    public void onResult(String result) {
        Handler handler = new Handler();
        handler.post((new Runnable() {
            @Override
            public void run() {
            	if (scanner == null || scanner.isScanning())
            		return;

            	scanner.startScanning();
            }
        }));
        
    	String contents = result;
        
        if( contents == null || contents == ""){
     	   Toast.makeText(this, getResources().getString(R.string.noti_invalid_qrcode), Toast.LENGTH_SHORT).show();
     	   return;
        }
        if( !new QRCodeService(this).setQRCodeContents(contents)){
        	Toast.makeText(this, getResources().getString(R.string.noti_invalid_qrcode).toString(), Toast.LENGTH_SHORT).show();
        } else {
        	Toast.makeText(this, getResources().getString(R.string.noti_qrcode_success).toString(), Toast.LENGTH_SHORT).show();
        }
 	    
    	Log.d("INFO", "Scan Result : " + contents);
        
    	Log.d("INFO", "Scan End");
    }

    @Override
    public boolean onTouchEvent (MotionEvent e)
    {
//        if (gestureDetector != null)
//            return gestureDetector.onTouchEvent (e);

        return super.onTouchEvent(e);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed (MotionEvent e)
        {
//            Log.d(TAG, "Single Tap Confirmed.");
//            if (scanner == null || scanner.isScanning())
//                return false;
//
//            scanner.startScanning();
//            Log.d(TAG, "Ok, scanning now.");

            return true;
        }
    }

	@Override
	public void onBackPressed() {
		blEvent.backButtonPressed(this, this);
	}
}
