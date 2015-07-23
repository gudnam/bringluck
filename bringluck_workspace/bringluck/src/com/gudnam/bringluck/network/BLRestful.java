package com.gudnam.bringluck.network;

import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.SparseArray;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.mypage.service.LottoResultService;
import com.gudnam.bringluck.sqlite.BLSQLiteHelper;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;
import com.gudnam.bringluck.util.BringluckUtil;
import com.gudnam.bringluck.util.JsonParserUtil;

public class BLRestful {

	ServerConnect connection;
	Activity activity;
	
	public BLRestful(Activity activity){
		this.activity = activity;
		connection = new ServerConnect(activity);
	}
	
	public void sendHttpMessageByGetLotto(String url, List<NameValuePair> param){
		connection.execute(url, param);
		connection.setOnEndUpSerVerConncect(new onEndUpServerConnectCallBack() {
			@Override
			public void onEndUpServerConnect(SparseArray<String> result) {
				
				SharedPreferences preference = activity.getSharedPreferences("launch", 0);
				SharedPreferences.Editor edit = preference.edit();
				edit.putBoolean("isStart", false);
				edit.commit();
				
				if( result == null){
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.con_fail));
					return;
				}
				int resultStatus = result.keyAt(0);	
				// 성공
				if( resultStatus == ServerConnect.SERVER_SUCCESS){
				}
				// DB 연결 실패
				else if (resultStatus == ServerConnect.SERVER_DB_CONN_FAIL) {
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.db_con_fail));
					return;
				}
				// 인터넷 연결 실패
				else if (resultStatus == ServerConnect.SERVER_NETWORK_CONN_FAIL) {
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.con_fail));
					return;
				}
				else {
					BringluckUtil.makeToastMsg(activity, "기타 : " + result);
					return;
				}
				
				String value = result.get(resultStatus);		
				List<LottoVo> lottoVoList = JsonParserUtil.parsingLottoAll(value);
				if( lottoVoList == null)
					return;
				if( lottoVoList.isEmpty())
					return;
				
				for(LottoVo lottoVo : lottoVoList){
					if( BLSQLiteQuery.addLotto(lottoVo) == BLSQLiteHelper.SQLITE_RESULT_ADD){
						LottoResultService.resultMyLotto(activity, lottoVo.getLottoAge());
					}
				}
			}
		});
	}

	public void sendHttpMessageByAddUser(String url, List<NameValuePair> param){
		connection.execute(url, param);
		connection.setOnEndUpSerVerConncect(new onEndUpServerConnectCallBack() {
			@Override
			public void onEndUpServerConnect(SparseArray<String> result) {
				if( result == null){
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.con_fail));
					return;
				}
				int resultStatus = result.keyAt(0);				
				// 성공
				if( resultStatus == ServerConnect.SERVER_SUCCESS){
				}
				// DB 연결 실패
				else if (resultStatus == ServerConnect.SERVER_DB_CONN_FAIL) {
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.db_con_fail));
				}
				// 인터넷 연결 실패
				else if (resultStatus == ServerConnect.SERVER_NETWORK_CONN_FAIL) {
					BringluckUtil.makeToastMsg(activity, activity.getString(R.string.con_fail));
				}
				else {
					BringluckUtil.makeToastMsg(activity, "기타 : " + result);
				}
			}
		});
	}
}
