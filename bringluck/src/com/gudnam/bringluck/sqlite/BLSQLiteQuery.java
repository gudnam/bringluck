package com.gudnam.bringluck.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.gudnam.bringluck.common.BLConfig;
import com.gudnam.bringluck.domain.LottoResultVo;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.domain.UserLottoVo;

public class BLSQLiteQuery {


	public static int addLotto(LottoVo vo){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			  
	        ContentValues values = new ContentValues();
	        values.put(BLSQLiteHelper.COL_LOTTO_AGE, vo.getLottoAge()); 
	        values.put(BLSQLiteHelper.COL_WINNING_NUMBER, vo.getWinningNumber()); 
	        values.put(BLSQLiteHelper.COL_WINNING_DATE, vo.getWinningDate()); 
	
	        // Select Row
	        String query = 
	    		"SELECT * FROM " + BLSQLiteHelper.TB_LOTTO + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + vo.getLottoAge() + "\" " +
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        if( !cursor.moveToFirst()){
	            // Inserting Row
	            db.insert(BLSQLiteHelper.TB_LOTTO, null, values);
	            cursor.close();
	            db.close();
	            return BLSQLiteHelper.SQLITE_RESULT_ADD;
	        }
	        else{
	        	// Updating Row
	        	db.update(BLSQLiteHelper.TB_LOTTO, values, 
		        			BLSQLiteHelper.COL_LOTTO_AGE + " = ? ",
		        			new String[]{vo.getLottoAge()});
	            cursor.close();
	            db.close();
	            return BLSQLiteHelper.SQLITE_RESULT_UPDATE;
	        }
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return -1;
	    } catch( Exception ex){
			ex.printStackTrace();
			return -1;
		}
	}
	
	public static boolean addLottoResult(LottoResultVo vo){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			  
	        ContentValues values = new ContentValues();
	        values.put(BLSQLiteHelper.COL_USER_ID, vo.getUserId()); 
	        values.put(BLSQLiteHelper.COL_LOTTO_AGE, vo.getLottoAge()); 
	        values.put(BLSQLiteHelper.COL_LOTTO_NUMBER, vo.getLottoNumber()); 
	        values.put(BLSQLiteHelper.COL_LOTTO_DATE, vo.getLottoDate()); 
	        values.put(BLSQLiteHelper.COL_RANK, vo.getRank()); 
	        values.put(BLSQLiteHelper.COL_WINNING_NUMBER, vo.getWinningNumber()); 
	        values.put(BLSQLiteHelper.COL_WINNING_DATE, vo.getWinningDate()); 
	        values.put(BLSQLiteHelper.COL_SAME_NUMBER, vo.getSameNumber()); 
	
	        // Select Row
	        String query = 
	    		"SELECT * FROM " + BLSQLiteHelper.TB_LOTTO_RESULT + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_USER_ID + " = \"" + vo.getUserId() + "\" " + 
	    		"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + vo.getLottoAge() + "\" " +
	    		"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = \"" + vo.getLottoNumber() + "\";";
	        Cursor cursor = db.rawQuery(query, null);
	        if( !cursor.moveToFirst()){
	            // Inserting Row
	            db.insert(BLSQLiteHelper.TB_LOTTO_RESULT, null, values);
	        }
	        else{
	        	// Updating Row
	        	db.update(BLSQLiteHelper.TB_LOTTO_RESULT, values, 
	        			BLSQLiteHelper.COL_USER_ID + " = ? " + 
	        			"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = ? " + 
	        			"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = ? ",
	        			new String[]{vo.getUserId(), vo.getLottoAge(), vo.getLottoNumber()});
	        }
	        cursor.close();
	        db.close(); // Closing database connection
	        
	        return true;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return false;
	    } catch( Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public static boolean addUserLotto(UserLottoVo vo){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			  
	        ContentValues values = new ContentValues();
	        values.put(BLSQLiteHelper.COL_USER_ID, vo.getUserId()); 
	        values.put(BLSQLiteHelper.COL_LOTTO_AGE, vo.getLottoAge());  
	        values.put(BLSQLiteHelper.COL_LOTTO_DATE, vo.getLottoDate()); 
	        values.put(BLSQLiteHelper.COL_LOTTO_NUMBER, vo.getLottoNumber());  
	
	        // Select Row
	        String query = 
	    		"SELECT * FROM " + BLSQLiteHelper.TB_USER_LOTTO + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_USER_ID + " = \"" + vo.getUserId() + "\" " + 
	    		"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + vo.getLottoAge() + "\" " +
	    		"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = \"" + vo.getLottoNumber() + "\";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if( !cursor.moveToFirst()){
		        // Inserting Row
		        db.insert(BLSQLiteHelper.TB_USER_LOTTO, null, values);
	        }
	        else{
	        	// Updating Row
	        	db.update(BLSQLiteHelper.TB_USER_LOTTO, values, 
	        			BLSQLiteHelper.COL_USER_ID + " = ? " + 
	        			"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = ? " + 
	        			"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = ? ",
	        			new String[]{vo.getUserId(), vo.getLottoAge(), vo.getLottoNumber()});
	        } 
	        cursor.close();
	    	db.close();
	        return true;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return false;
	    } catch( Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public static List<UserLottoVo> getUserLottoAll(){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT user_id, lotto_age, lotto_date, lotto_number FROM " + 
	    		BLSQLiteHelper.TB_USER_LOTTO + " " + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC, " + BLSQLiteHelper.COL_LOTTO_DATE + " DESC " +
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        cursor.moveToFirst();
	        
	        List<UserLottoVo> voList = new ArrayList<UserLottoVo>();
	        
	        while(!cursor.isAfterLast()){
	        	UserLottoVo vo = new UserLottoVo(
		        		cursor.getString(0), cursor.getString(1), 
		        		cursor.getString(2), cursor.getString(3));
	        	voList.add(vo);
	            cursor.moveToNext();
	        }
	        cursor.close();
	        
			return voList;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static List<UserLottoVo> getUserLottoByLottoAge(String lottoAge){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT user_id, lotto_age, lotto_date, lotto_number FROM " + 
	    		BLSQLiteHelper.TB_USER_LOTTO + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + lottoAge + "\"" + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC, " + BLSQLiteHelper.COL_LOTTO_DATE + " DESC " +
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        cursor.moveToFirst();
	        
	        List<UserLottoVo> voList = new ArrayList<UserLottoVo>();
	        
	        while(!cursor.isAfterLast()){
	        	UserLottoVo vo = new UserLottoVo(
		        		cursor.getString(0), cursor.getString(1), 
		        		cursor.getString(2), cursor.getString(3));
	        	voList.add(vo);
	            cursor.moveToNext();
	        }
	        cursor.close();
	        
			return voList;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static List<LottoVo> getLottoAll(){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT lotto_age, winning_number, winning_date FROM " + 
	    		BLSQLiteHelper.TB_LOTTO + " " + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC " +
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        cursor.moveToFirst();
	        
	        List<LottoVo> voList = new ArrayList<LottoVo>();
	        
	        while(!cursor.isAfterLast()){
	        	LottoVo vo = new LottoVo(
		        		cursor.getString(0), cursor.getString(1), cursor.getString(2));
	        	voList.add(vo);
	            cursor.moveToNext();
	        }
	        cursor.close();
	        
			return voList;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	
	public static LottoVo getLottoByLottoAge(String lottoAge){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT lotto_age, winning_number, winning_date FROM " + 
	    		BLSQLiteHelper.TB_LOTTO + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + lottoAge + "\"" + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC " + 
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        if( !cursor.moveToFirst()){
	        	return null;
	        }
	        
	    	LottoVo vo = new LottoVo(cursor.getString(0), cursor.getString(1), cursor.getString(2));
	        	
	        cursor.close();
	        
			return vo;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public static List<LottoResultVo> getLottoResultAll(){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT user_id, lotto_age, lotto_number, lotto_date, rank, winning_number, winning_date, same_number " + 
	    		"FROM " + BLSQLiteHelper.TB_LOTTO_RESULT + " " + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC, " + BLSQLiteHelper.COL_LOTTO_DATE + " DESC " + 
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        cursor.moveToFirst();
	        
	        List<LottoResultVo> voList = new ArrayList<LottoResultVo>();
	        
	        while(!cursor.isAfterLast()){
	        	LottoResultVo vo = new LottoResultVo(
		        		cursor.getString(0), cursor.getString(1), 
		        		cursor.getString(2), cursor.getString(3),
		        		cursor.getString(4), cursor.getString(5),
		        		cursor.getString(6), cursor.getString(7));
	        	voList.add(vo);
	            cursor.moveToNext();
	        }
	        cursor.close();
	        
			return voList;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static List<LottoResultVo> getLottoResultByLottoAge(String lottoAge){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"SELECT user_id, lotto_age, lotto_number, lotto_date, rank, winning_number, winning_date, same_number " + 
	    		"FROM " + BLSQLiteHelper.TB_LOTTO_RESULT + " " + 
	    		"WHERE " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + lottoAge + "\"" + 
	    		"ORDER BY " + BLSQLiteHelper.COL_LOTTO_AGE + " DESC, " + BLSQLiteHelper.COL_LOTTO_DATE + " DESC " + 
	    		";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return null;
	
	        cursor.moveToFirst();
	        
	        List<LottoResultVo> voList = new ArrayList<LottoResultVo>();
	        
	        while(!cursor.isAfterLast()){
	        	LottoResultVo vo = new LottoResultVo(
		        		cursor.getString(0), cursor.getString(1), 
		        		cursor.getString(2), cursor.getString(3),
		        		cursor.getString(4), cursor.getString(5),
		        		cursor.getString(6), cursor.getString(7));
	        	voList.add(vo);
	            cursor.moveToNext();
	        }
	        cursor.close();
			return voList;
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    	return null;
	    } catch( Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void removeLottoAll(){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"DELETE FROM " + BLSQLiteHelper.TB_LOTTO + ";";
	        Cursor cursor = db.rawQuery(query, null);
	        
	        if (cursor == null)
	        	return;
	
	        cursor.moveToFirst();
	        cursor.close();
	    } catch( SQLiteException se){
	    	se.printStackTrace();
	    } catch( Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static void removeLottoByLottoAge(int lottoAge){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
	        String query = 
	    		"DELETE FROM " + BLSQLiteHelper.TB_LOTTO + " WHERE " + BLSQLiteHelper.COL_LOTTO_AGE + " = " + lottoAge + ";";
	        Cursor cursor = db.rawQuery(query, null);

	        if (cursor == null)
	        	return;
	
	        cursor.moveToFirst();
	        cursor.close();
        } catch( SQLiteException se){
        	se.printStackTrace();
        } catch( Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static boolean updateLottoResult(String lottoAge, String lottoNumber){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
			List<LottoResultVo> lottoResultVoList = getLottoResultByLottoAge(lottoAge);
			if( lottoResultVoList == null){
				return false;
			}
			if( lottoResultVoList.size() < 1){
				return false;
			}
			LottoResultVo lottoResultVo = null;
			for(LottoResultVo vo : lottoResultVoList){
				if( vo.getLottoNumber().equalsIgnoreCase(lottoNumber)){
					lottoResultVo = vo;
				}
			}
			if( lottoResultVo == null){
				return false;
			}
	        
			// Delete Row
	        String delete_query = 
	    		"DELETE FROM " + BLSQLiteHelper.TB_LOTTO_RESULT + " " +
	    		"WHERE " + BLSQLiteHelper.COL_USER_ID + " = \"" + lottoResultVo.getUserId() + "\" " + 
	    		"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + lottoResultVo.getLottoAge() + "\" " +
	    		"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = \"" + lottoResultVo.getLottoNumber() + "\" " +
	    		";";
        
	        Cursor cursor = db.rawQuery(delete_query, null);
	        
	        if (cursor == null)
	        	return false;
	
	        cursor.moveToFirst();
	        cursor.close();
	        
	        return true;
        } catch( SQLiteException se){
        	se.printStackTrace();
        	return false;
        } catch( Exception ex){
			ex.printStackTrace();
			return false;
		}
        
	}
	
	public static boolean removeUserLotto(String  userId, String lottoAge, String lottoNumber){
        try{
			SQLiteDatabase db = BLConfig.helper.getWritableDatabase();
			
			// Select Row
			List<UserLottoVo> userLottoVoList = getUserLottoByLottoAge(lottoAge);
			if( userLottoVoList == null){
				return false;
			}
			if( userLottoVoList.size() < 1){
				return false;
			}
			UserLottoVo userLottoVo = null;
			for(UserLottoVo vo : userLottoVoList){
				if( vo.getLottoNumber().equalsIgnoreCase(lottoNumber)){
					userLottoVo = vo;
				}
			}
			if( userLottoVo == null){
				return false;
			}
	        
			// Delete Row
	        String delete_query = 
	    		"DELETE FROM " + BLSQLiteHelper.TB_USER_LOTTO + " " +
	    		"WHERE " + BLSQLiteHelper.COL_USER_ID + " = \"" + userLottoVo.getUserId() + "\" " + 
	    		"AND " + BLSQLiteHelper.COL_LOTTO_AGE + " = \"" + userLottoVo.getLottoAge() + "\" " +
	    		"AND " + BLSQLiteHelper.COL_LOTTO_NUMBER + " = \"" + userLottoVo.getLottoNumber() + "\" " +
	    		";";
        
	        Cursor cursor = db.rawQuery(delete_query, null);
	        
	        if (cursor == null)
	        	return false;
	
	        cursor.moveToFirst();
	        cursor.close();
	        
	        return true;
        } catch( SQLiteException se){
        	se.printStackTrace();
        	return false;
        } catch( Exception ex){
			ex.printStackTrace();
			return false;
		}
        
	}
}
