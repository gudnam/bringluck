package com.gudnam.bringluck.gcm;

import java.util.HashMap;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.mypage.activity.MypageActivity;
import com.gudnam.bringluck.mypage.service.LottoResultService;
import com.gudnam.bringluck.sqlite.BLSQLiteHelper;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;
import com.gudnam.bringluck.util.BringluckUtil;
import com.gudnam.bringluck.util.NotificationUtil;

public class GcmIntentService extends IntentService
{
	public static final int NOTIFICATION_ID = 1;

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
   protected void onHandleIntent(Intent intent)
   {
		SharedPreferences preference = getSharedPreferences("setting", 0);
		boolean isAlarm = preference.getBoolean("isAlarm", true);
		if (!isAlarm)
			return;
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { // has effect of unparcelling Bundle
			Log.i("GcmBroadcastReceiver", "Received: " + extras.toString());
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				// sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				// sendNotification("Deleted messages on server: " +
				// extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {
				LottoVo lottoVo = new LottoVo();
				lottoVo.setLottoAge(intent.getStringExtra("lotto_age"));
				lottoVo.setWinningNumber(intent
						.getStringExtra("winning_number"));
				lottoVo.setWinningDate(intent.getStringExtra("winning_date"));

				// 당첨 번호 전송 및 로또 결과
				if( BLConfig.helper == null){
					BLConfig.helper = new BLSQLiteHelper(this);
				}
				BLSQLiteQuery.addLotto(lottoVo);
				int rank = LottoResultService.resultMyLotto(
						getApplicationContext(), lottoVo.getLottoAge());
				if (rank >= BLConfig.LOTTO_THE_FAIL) {
					sendResultToUser(getApplicationContext(), lottoVo, rank);
				}
			}
		}

		// Release the wake lock provided by the WakefulBroadcastReceiver.
		WakefulBroadcastReceiver.completeWakefulIntent(intent);
   }

	private void sendResultToUser(Context context, LottoVo lottoVo, int rank) {
		Log.i("INFO", "[sendResultToUser] start");

		String title = String.format("%s%s!", lottoVo.getLottoAge(),
				context.getString(R.string.lotto_result_title));
		String content = "";
		if (rank == BLConfig.LOTTO_THE_FIRST)
			content = String.format("%s",
					context.getString(R.string.lotto_result_1rank_content));
		else if (rank == BLConfig.LOTTO_THE_SECOND)
			content = String.format("%s",
					context.getString(R.string.lotto_result_2rank_content));
		else if (rank == BLConfig.LOTTO_THE_THIRD)
			content = String.format("%s",
					context.getString(R.string.lotto_result_3rank_content));
		else if (rank == BLConfig.LOTTO_THE_FORTH)
			content = String.format("%s",
					context.getString(R.string.lotto_result_4rank_content));
		else if (rank == BLConfig.LOTTO_THE_FIFTH)
			content = String.format("%s",
					context.getString(R.string.lotto_result_5rank_content));
		else if (rank == BLConfig.LOTTO_THE_FAIL)
			content = String.format("%s",
					context.getString(R.string.lotto_result_fail_content));
		HashMap<String, String> extraMap = new HashMap<String, String>();
		extraMap.put("lotto_age", lottoVo.getLottoAge());
		extraMap.put("winning_number", lottoVo.getWinningNumber());
		NotificationUtil.sendNotification(context, MypageActivity.class, title,
				content, extraMap);

		Log.i("INFO", "[sendResultToUser] end");
	}
	
	private void test(){

		SharedPreferences preference = getSharedPreferences("test", 0);
		SharedPreferences.Editor edit = preference.edit();
		edit.putBoolean("isGCM", true);
		edit.commit();
	}
}