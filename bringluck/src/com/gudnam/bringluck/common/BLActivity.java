package com.gudnam.bringluck.common;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

import com.gudnam.bringluck.main.activity.MainActivity;
import com.gudnam.bringluck.mypage.activity.MypageActivity;

public class BLActivity extends Activity{

	public static ArrayList<Activity> actList = new ArrayList<Activity>();

	public static void intentMainActivity(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
	}
	public static void intentMypageActivity(Activity activity){
		Intent intent = new Intent(activity, MypageActivity.class);
		activity.startActivity(intent);
	}
}
