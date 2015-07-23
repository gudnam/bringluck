package com.gudnam.bringluck.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gudnam.bringluck.common.BLConfig;

public class BLSQLiteHelper extends SQLiteOpenHelper {

	// All Static variables	
	// Database Version
	private static final int DATABASE_VERSION = 14;

	// Database Name
	private static final String DATABASE_NAME = "bringluck";

	// Contacts table name
	public static final String TB_LOTTO = "tb_lotto";
	public static final String TB_LOTTO_RESULT = "tb_lotto_result";
	public static final String TB_USER_LOTTO = "tb_user_lotto";

	// Contacts Table Columns names
	public static final String COL_LOTTO_AGE = "lotto_age";
	public static final String COL_LOTTO_NUMBER = "lotto_number";
	public static final String COL_LOTTO_DATE = "lotto_date";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_NUMBER = "number";
	public static final String COL_WINNING_NUMBER = "winning_number";
	public static final String COL_WINNING_DATE = "winning_date";
	public static final String COL_RANK = "rank";
	public static final String COL_SAME_NUMBER = "same_number";
	
	// Result Number
	public static final int SQLITE_RESULT_ADD = 1;
	public static final int SQLITE_RESULT_UPDATE = 2;

	public BLSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	// Creating Tables
	@Override
    public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_LOTTO = 
      		  "CREATE TABLE " + TB_LOTTO + " (" +
          		  COL_LOTTO_AGE + " VARCHAR(10) NOT NULL, " +
          		  COL_WINNING_NUMBER + " CHAR(20) NOT NULL, " +
          		  COL_WINNING_DATE + " TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00', " +
          		  "PRIMARY KEY (" + 
	            		  COL_LOTTO_AGE + "" +
	            	  ")" + 
          	   ")";
		String CREATE_TABLE_LOTTO_RESULT = 
        		  "CREATE TABLE " + TB_LOTTO_RESULT + " (" +
            		  COL_USER_ID + " VARCHAR(50) NOT NULL, " +
            		  COL_LOTTO_AGE + " VARCHAR(10) NOT NULL, " +
            		  COL_LOTTO_NUMBER + " CHAR(20) NOT NULL, " +
	        		  COL_LOTTO_DATE + " TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00', " +
            		  COL_RANK + " VARCHAR(2) NOT NULL, " +
            		  COL_WINNING_NUMBER + " CHAR(20) NOT NULL, " +
            		  COL_WINNING_DATE + " TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00', " +
            		  COL_SAME_NUMBER + " CHAR(20) NOT NULL, " +
            		  "PRIMARY KEY (" + 
	            		  COL_USER_ID + ", " +
	            		  COL_LOTTO_AGE + ", " +
	            		  COL_LOTTO_NUMBER + "" + 
	            	  ")" + 
            	   ")";
		
		String CREATE_TABLE_USER_LOTTO = 
	      		  "CREATE TABLE " + TB_USER_LOTTO + " (" +
	          		  COL_USER_ID + " VARCHAR(50) NOT NULL, " +
	          		  COL_LOTTO_AGE + " VARCHAR(10) NOT NULL, " +
	        		  COL_LOTTO_DATE + " TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00', " +
	        		  COL_LOTTO_NUMBER + " CHAR(20) NOT NULL, " +
	          		  "PRIMARY KEY (" + 
	          		  		COL_USER_ID + ", " +
	          		  		COL_LOTTO_AGE + ", " +
	          		  		COL_LOTTO_NUMBER + "" +
		            	  ")" + 
	          	   ")";

		db.execSQL(CREATE_TABLE_LOTTO);
        db.execSQL(CREATE_TABLE_LOTTO_RESULT);
        db.execSQL(CREATE_TABLE_USER_LOTTO);
        
        BLConfig.isFirst = true;
    }

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TB_LOTTO);
		db.execSQL("DROP TABLE IF EXISTS " + TB_LOTTO_RESULT);
		db.execSQL("DROP TABLE IF EXISTS " + TB_USER_LOTTO);

		// Create tables again
		onCreate(db);
	}
}
