package com.gudnam.bringluck.gcm;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.main.service.MainService;
import com.gudnam.bringluck.util.PreferenceUtil;

public class GcmService {

	public final static String SENDER_ID = "716515473989";
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	private GoogleCloudMessaging gcm;
	private String regId;

	Activity activity;
	
	public GcmService(Activity activity){
		this.activity = activity;
	}

	public String registGCM() {

		if (checkPlayServices()) {
			gcm = GoogleCloudMessaging.getInstance(activity);
			regId = getRegistrationId();

			if (TextUtils.isEmpty(regId))
				registerInBackground();
		} else {
			Log.i("GCMService",
					"|No valid Google Play Services APK found.|");
		}

		return regId;
	}

	// google play service가 사용가능한가
	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(activity);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.i("GCMService",
						"|This device is not supported.|");
				activity.finish();
			}
			return false;
		}
		return true;
	}

	// registration id를 가져온다.
	private String getRegistrationId() {
		String registrationId = PreferenceUtil
				.instance(activity.getApplicationContext()).regId();
		if (TextUtils.isEmpty(registrationId)) {
			Log.i("GCMService",
					"|Registration not found.|");
			return "";
		}
		int registeredVersion = PreferenceUtil
				.instance(activity.getApplicationContext()).appVersion();
		int currentVersion = getAppVersion();
		if (registeredVersion != currentVersion) {
			Log.i("GCMService",
					"|App version changed.|");
			return "";
		}
		return registrationId;
	}

	// app version을 가져온다. 뭐에 쓰는건지는 모르겠다.
	private int getAppVersion() {
		try {
			PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(
					activity.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			// should never happen
			throw new RuntimeException("Could not get package name: " + e);
		}
	}

	// gcm 서버에 접속해서 registration id를 발급받는다.
	private void registerInBackground() {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging
								.getInstance(activity.getApplicationContext());
					}
					regId = gcm.register(SENDER_ID);
					msg = "Device registered, registration ID=" + regId;

					// For this demo: we don't need to send it because the
					// device
					// will send upstream messages to a server that echo back
					// the
					// message using the 'from' address in the message.

					// Persist the regID - no need to register again.
					storeRegistrationId(regId);
				} catch (IOException ex) {
					ex.printStackTrace();
					msg = "Error :" + ex.getMessage();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
				} catch( Exception ex){
					ex.printStackTrace();
					msg = "Error :" + ex.getMessage();
				}

				return msg;
			}

			@Override
			protected void onPostExecute(String msg) {
				Log.i("GCMService", "|" + msg + "|");
			}
		}.execute(null, null, null);
	}

	// registraion id를 preference에 저장한다.
	private void storeRegistrationId(String regId) {
		int appVersion = getAppVersion();
		Log.i("GCMService", "|"
				+ "Saving regId on app version " + appVersion + "|");
		PreferenceUtil.instance(activity.getApplicationContext()).putRedId(regId);
		PreferenceUtil.instance(activity.getApplicationContext()).putAppVersion(
				appVersion);
		BLConfig.BL_GCM_ID = regId;
		MainService.loginUser(activity, BLConfig.BL_ANDROID_ID, BLConfig.BL_GCM_ID);
	}
}
