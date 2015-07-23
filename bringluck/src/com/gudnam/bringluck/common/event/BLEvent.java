package com.gudnam.bringluck.common.event;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLActivity;
import com.gudnam.bringluck.common.BLFragmentActivity;
import com.gudnam.bringluck.common.BLConfig;

public class BLEvent implements OnClickListener {
	
	private BLActivity activity = null;
	private BLFragmentActivity frag_activity = null;
	
	public BLEvent(BLActivity activity){
		this.activity = activity;
	}
	
	public BLEvent(BLFragmentActivity frag_activity){
		this.frag_activity = frag_activity;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		}
	}
	
	public void clearActivity(){
		for (int actCnt = 0; actCnt < BLActivity.actList.size(); actCnt++)
			BLActivity.actList.get(actCnt).finish();
		BLActivity.actList.clear();
	}
	public void clearFragActivity(){
		for (int actCnt = 0; actCnt < BLFragmentActivity.frgActList.size(); actCnt++)
			BLFragmentActivity.frgActList.get(actCnt).finish();
		BLFragmentActivity.frgActList.clear();
	}
	public void clearPopup() {
		for (int actCnt = 0; actCnt < BLConfig.popupList.size(); actCnt++)
			BLConfig.popupList.get(actCnt).finish();
		BLConfig.popupList.clear();
	}
	
	// Back�� ���°��� �����ϱ� ���� ����
	private boolean m_close_flag = false;

	// ���� �ð� �� ���°��� �ʱ�ȭ�ϱ� ���� �ڵ鷯
	Handler m_close_handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			m_close_flag = false;
		}
	};
	
//	public void backButtonEvent(){
//
//		Fragment fragment = Fragment.instantiate(activity, BLFragment.frgList.get(BLFragment.frgList.size()-1).getClass().getName());
//		BLFragment.frgList.remove(BLFragment.frgList.size()-1);
//
//		activity.getSupportFragmentManager().beginTransaction()
//				.replace(R.id.frag_main, fragment).commit();
//	}
	
	public void backButtonPressed(Activity activity, Activity superActivity){
		// m_close_flag �� false �̸� ù��°�� Ű�� ���� ���̴�.
		if (m_close_flag == false) { // Back Ű�� ù��°�� ���� ���

			// �ȳ� �޼����� �佺Ʈ�� ����Ѵ�.
			Toast.makeText(activity, activity.getString(R.string.app_finish), Toast.LENGTH_LONG)
					.show();

			// ���°� ����
			m_close_flag = true;

			// �ڵ鷯�� �̿��Ͽ� 3�� �Ŀ� 0�� �޼����� �����ϵ��� �����Ѵ�.
			m_close_handler.sendEmptyMessageDelayed(0, 3000);

		} else { // Back Ű�� 3�� ���� ���޾Ƽ� �ι� ���� ���

			// ��Ƽ��Ƽ�� �����ϴ� ���� Ŭ������ onBackPressed �޼ҵ带 ȣ���Ѵ�.
			clearActivity();
			clearFragActivity();
			superActivity.finish();
			android.os.Process.killProcess(android.os.Process.myPid());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
}
