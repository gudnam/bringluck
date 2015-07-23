package com.gudnam.bringluck.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class BringluckUtil {
	
	private static final String TAG = "BringLuckUtil";

	public static Dialog setLoadingDialog(Activity activity, String message) {
		ProgressDialog progressDialog = new ProgressDialog(activity);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage(message);
		return progressDialog;
	}

	public static void makeToastMsg(Context context, String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	public static int getPxToDp(Context con, int px) {
        float density = 0.0f;
        density  = con.getResources().getDisplayMetrics().density;
        Log.d(TAG, "density = " + density);
        return (int)(px / density);
    }
	
	public static boolean isNumber(String str) {  
        boolean check = true;
        for(int i = 0; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                check = false;
                break; 
            }
        }
    return check;  
   }
}