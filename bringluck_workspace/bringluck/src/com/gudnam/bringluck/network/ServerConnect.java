package com.gudnam.bringluck.network;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

public class ServerConnect extends AsyncTask<Object, Integer, SparseArray<String>> {

	private Activity activity;
	public Object mObject = null;

	public int mType = -1;

	public static int SERVER_SUCCESS = 200;
	public static int SERVER_NETWORK_CONN_FAIL = 500;
	public static int SERVER_DB_CONN_FAIL = 400;

	public static String ServerConnect_ERROR_THREAD_CANCEL = "thread_cancel";

	private boolean running = true;

	public onEndUpServerConnectCallBack mCallBack;
	
	public ServerConnect(Activity activity){
		this.activity = activity;
	}

	public onEndUpServerConnectCallBack getmCallBack() {
		return mCallBack;
	}

	public void setOnEndUpSerVerConncect(onEndUpServerConnectCallBack callBack) {
		this.mCallBack = callBack;
	}

	public onEndupServerConncetCallBackWithObject _callbackWithObject;

	public onEndupServerConncetCallBackWithObject getCallbackWithObject;

	public void setOnEndupServerConnectWithObject(
			onEndupServerConncetCallBackWithObject callback) {
		this._callbackWithObject = callback;
	}

	private boolean mCaches = true;

	private boolean mChangeHTMLstring = true;

	private String mInputStreamEncodingType = "utf-8";

	@Override
	protected void onCancelled() {
		super.onCancelled();
		running = false;

	}

	@Override
	protected void onPostExecute(SparseArray<String> result) {
		super.onPostExecute(result);
		if (mCallBack != null) {
			mCallBack.onEndUpServerConnect(result);

		}
		if (_callbackWithObject != null) {
			_callbackWithObject.onEndupServerConnect(result, mObject);
		}

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected SparseArray<String> doInBackground(Object... param) {
		try {
			HttpResponse response = postData((String)param[0], (List<NameValuePair>) param[1]);
			if( response == null)
				return null;
			HttpEntity entity = response.getEntity();
			String resultValue = "";
			if( entity != null)
				resultValue = EntityUtils.toString(entity);
			int resultStatus = response.getStatusLine().getStatusCode();
			
			SparseArray<String> resultMap = new SparseArray<String>();
			resultMap.put(resultStatus, resultValue);
			Log.i("INFO", "Login Post Result status : " + resultStatus + ", value : " + resultValue);
			
			return resultMap;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public HttpResponse postData(String url, List<NameValuePair> nameValuePairs) {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(url);
	    try {
	        // Add your data
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        return response;
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
			return null;
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
			return null;
	    } catch( Exception ex){
			return null;
		}
	} 
	/**
	 * @param mCaches
	 *            the mCaches to set
	 */
	public void setCaches(boolean caches) {
		this.mCaches = caches;
	}

	/**
	 * @return the mCaches
	 */
	public boolean isCaches() {
		return mCaches;
	}

	/**
	 * @param default="utf-8" , other ="euc-kr"
	 */
	public void setInputStreamEncodingType(String encoding) {
		this.mInputStreamEncodingType = encoding;
	}

	/**
	 * @return the mInputStreamEncodingType
	 */
	public String getInputStreamEncodingType() {
		return mInputStreamEncodingType;
	}

	/**
	 * �⺻���� true &nbsp; ���� ������ ����, ���ڸ� �������� ġȯ ����
	 * 
	 * @param mChangeHTMLstring
	 *            the mChangeHTMLstring to set
	 */
	public void setChangeHTMLstring(boolean change) {
		this.mChangeHTMLstring = change;
	}

	/**
	 * @return the mChangeHTMLstring
	 */
	public boolean isChangeHTMLstring() {
		return mChangeHTMLstring;
	}
}