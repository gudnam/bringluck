package com.gudnam.bringluck.mypage.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.gudnam.bringluck.R;
import com.gudnam.bringluck.common.BLActivity;
import com.gudnam.bringluck.domain.LottoResultVo;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.domain.UserLottoVo;
import com.gudnam.bringluck.mypage.service.MypageAdapter;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;
import com.gudnam.bringluck.util.AnimatedExpandableListView;

public class MypageActivity extends Activity {

	private AnimatedExpandableListView mListView = null;
	private MypageAdapter mAdapter = null;
    private AdView ad_mypage;
    private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_mypage);
		BLActivity.actList.add(this);

		mContext = this;
		ad_mypage = (AdView) findViewById(R.id.ad_mypage);
		AdRequest adRequest = new AdRequest.Builder().build();
        if( ad_mypage != null)
        	ad_mypage.loadAd(adRequest);
        
		mListView = (AnimatedExpandableListView) findViewById(R.id.lv_mypage);
		mAdapter = new MypageAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group 
                // expansion/collapse.
                if (mListView.isGroupExpanded(groupPosition)) {
                	mListView.collapseGroupWithAnimation(groupPosition);
                } else {
                	mListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
            
        });

		//mListView.setOnChildClickListener(onChildClickListener);
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				final int groupPosition = ExpandableListView.getPackedPositionGroup(id);
				final int childPosition = ExpandableListView.getPackedPositionChild(id);
				
				if( childPosition < 0){
					return false;
				}
				PopupMenu popup = new PopupMenu(view.getContext(), view);
				popup.getMenuInflater().inflate(R.menu.del_popup_menu, popup.getMenu());
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.del_menu:
							LottoResultVo lottoResultVo = mAdapter.getItem(groupPosition, childPosition);
							BLSQLiteQuery.removeUserLotto(lottoResultVo.getUserId(), lottoResultVo.getLottoAge(), lottoResultVo.getLottoNumber());
							BLSQLiteQuery.updateLottoResult(lottoResultVo.getLottoAge(), lottoResultVo.getLottoNumber());
							//mAdapter.remove(groupPosition, childPosition);
							initialize();
							Toast.makeText(mContext, "삭제 하였습니다.", Toast.LENGTH_SHORT).show();
							return true;
						default:
							return false;
						}
					}
				});
				// Show the menu
				popup.show();
				return true;
			}
		});
		initialize();
		
	}
	
	private void initialize(){
		// 로또 정보 리스트 가져오기
		mAdapter.clear();
		List<LottoVo> lottoVoList = BLSQLiteQuery.getLottoAll();
		if( lottoVoList.size() < 1)
			return;
		// 로또 회차를 기준으로 로또 결과 가져오기
		// 다음 회차 로또 결과 먼저 가져오기
		int nextAge = Integer.parseInt(lottoVoList.get(0).getLottoAge())+1;
		List<LottoResultVo> lottoResultVoList = new ArrayList<LottoResultVo>();
		lottoResultVoList = getLottoResultByUserLotto(String.valueOf(nextAge));
		if( !lottoResultVoList.isEmpty())
			mAdapter.add(lottoResultVoList);
		
		// 현재까지 있는 회차 로또 결과 가져오기
		for(LottoVo lottoVo : lottoVoList){
			String lottoAge = lottoVo.getLottoAge();
			lottoResultVoList = BLSQLiteQuery.getLottoResultByLottoAge(lottoAge);
			if( lottoResultVoList.isEmpty()){
				lottoResultVoList = getLottoResultByUserLotto(lottoAge);
			}
			if( lottoResultVoList.isEmpty()){
				// 로또 당첨번호만
				lottoResultVoList.add(new LottoResultVo(null, lottoVo.getLottoAge(), 
										null, null, null, lottoVo.getWinningNumber(), 
										lottoVo.getWinningDate(), null));
			}
			mAdapter.add(lottoResultVoList);
		}
		mAdapter.notifyDataSetChanged();
	}
	
	private List<LottoResultVo> getLottoResultByUserLotto(String lottoAge){
		List<LottoResultVo> lottoResultVoList = new ArrayList<LottoResultVo>();
		List<UserLottoVo> userLottoVoList = BLSQLiteQuery.getUserLottoByLottoAge(lottoAge);
		for(UserLottoVo vo : userLottoVoList){
			lottoResultVoList.add(new LottoResultVo(vo.getUserId(), vo.getLottoAge(), 
											vo.getLottoNumber(), vo.getLottoDate(), 
											null, null, null, null));
		}
		
		return lottoResultVoList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void test(String lottoAge){
		
//		BLSQLiteQuery.addUserLotto(new UserLottoVo(BLType.BL_ANDROID_ID,
//				lottoAge, "2014-11-08", "07,13,30,39,41,25"));
//		BLSQLiteQuery.addUserLotto(new UserLottoVo(BLType.BL_ANDROID_ID,
//				lottoAge, "2014-11-08", "03,13,31,39,41,25"));
//		BLSQLiteQuery.addUserLotto(new UserLottoVo(BLType.BL_ANDROID_ID,
//				lottoAge, "2014-11-08", "07,12,30,39,41,25"));
//		BLSQLiteQuery.addLotto(new LottoVo(lottoAge, "07,13,30,39,41,45,25",
//				"2012-08-19"));
//		
		
	}
}
