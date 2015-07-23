package com.gudnam.bringluck.common;

import java.util.ArrayList;

import com.gudnam.bringluck.sqlite.BLSQLiteHelper;

import android.app.Activity;

public class BLConfig {
	public static boolean isFirst = false;
	public static ArrayList<Activity> actList = new ArrayList<Activity>();
	public static ArrayList<Activity> popupList = new ArrayList<Activity>();
	
	public static BLSQLiteHelper helper = null;
	
	public static String BL_ANDROID_ID = "";
	public static String BL_GCM_ID = "";
	
	public static String BL_IP = "bringluck.cafe24app.com";
	public static String BL_PORT = "80";
	public static String BL_API_NAME = "BringLuck/api";
	
	public static int LOTTO_THE_FIRST = 1;
	public static int LOTTO_THE_SECOND = 2;
	public static int LOTTO_THE_THIRD = 3;
	public static int LOTTO_THE_FORTH = 4;
	public static int LOTTO_THE_FIFTH = 5;
	public static int LOTTO_THE_FAIL = 0;
	
	public static final String LOTTO_NULL = "000000000000";
}
