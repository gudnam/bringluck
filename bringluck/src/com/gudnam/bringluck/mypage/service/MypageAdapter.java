package com.gudnam.bringluck.mypage.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.domain.LottoResultVo;
import com.gudnam.bringluck.util.AnimatedExpandableListView.AnimatedExpandableListAdapter;

public class MypageAdapter extends AnimatedExpandableListAdapter {

	private List<List<LottoResultVo>> mMypageList = null;
	private LayoutInflater mInflater = null;

	public MypageAdapter(Context context) {
		super();
		mInflater = LayoutInflater.from(context);
		mMypageList = new ArrayList<List<LottoResultVo>>();
	}

	public void add(List<LottoResultVo> items) {
		mMypageList.add(items);
	}
	
	public void remove(int location, int position){
		mMypageList.get(location).remove(position);
	}
	
	public void clear(){
		mMypageList.clear();
	}
	
	public LottoResultVo getItem(int location, int position){
		return mMypageList.get(location).get(position);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mMypageList.size();
	}

	@Override
	public int getRealChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mMypageList.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mMypageList.get(groupPosition);
	}

	@Override
	public LottoResultVo getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mMypageList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewHolder holder;

		if (view == null) {
			view = mInflater.inflate(R.layout.adapter_mypage_win, parent, false);
			holder = new ViewHolder();
			holder.tv_lotto_age = (TextView) view.findViewById(R.id.tv_lotto_age);
			holder.tv_winning_year = (TextView) view.findViewById(R.id.tv_winning_year);
			holder.tv_winning_date = (TextView) view.findViewById(R.id.tv_winning_date);
			holder.iv_win_balls = new ImageView[7];
			holder.iv_win_balls[0] = (ImageView) view.findViewById(R.id.iv_win_ball1);
			holder.iv_win_balls[1] = (ImageView) view.findViewById(R.id.iv_win_ball2);
			holder.iv_win_balls[2] = (ImageView) view.findViewById(R.id.iv_win_ball3);
			holder.iv_win_balls[3] = (ImageView) view.findViewById(R.id.iv_win_ball4);
			holder.iv_win_balls[4] = (ImageView) view.findViewById(R.id.iv_win_ball5);
			holder.iv_win_balls[5] = (ImageView) view.findViewById(R.id.iv_win_ball6);
			holder.iv_win_balls[6] = (ImageView) view.findViewById(R.id.iv_win_ball7);
			holder.iv_lotto_top_rank = (ImageView) view.findViewById(R.id.iv_lotto_top_rank);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		// 초기화
		MypageService.initWinningLotto(holder.tv_lotto_age, holder.tv_winning_year, holder.tv_winning_date, holder.iv_win_balls);
		
		// lotto_age, 당천번호 표시 (로또 번호에 따라 이미지 변경)
		MypageService.setImageLottoTopRank(mMypageList.get(groupPosition), holder.iv_lotto_top_rank);
		if( mMypageList.get(groupPosition).size() < 1){
			return view;
		}
		LottoResultVo lottoResultVo = mMypageList.get(groupPosition).get(0);
		holder.tv_lotto_age.setText(lottoResultVo.getLottoAge());
		holder.tv_winning_year.setText("");
		holder.tv_winning_date.setText("");
		if (lottoResultVo.getWinningNumber() != null) {
			String date[] = lottoResultVo.getWinningDate().split("-");
			holder.tv_winning_year.setText(date[0]);
			holder.tv_winning_date.setText(date[1]+date[2]);
			
			String winningNums[] = lottoResultVo.getWinningNumber().split(",");
			for (int winCnt = 0; winCnt < winningNums.length; winCnt++) {
				MypageService.setImageWinningBall(holder.iv_win_balls[winCnt],
						winningNums[winCnt]);
			}
		}

		return view;
	}

	@Override
	public View getRealChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		ViewChildHolder holder = new ViewChildHolder();

		if (view == null) {
			view = mInflater.inflate(R.layout.adapter_mypage_lotto, parent, false);
			holder = new ViewChildHolder();
			holder.rl_lotto = (RelativeLayout) view.findViewById(R.id.rl_lotto);
			holder.ll_lotto_balls = new LinearLayout[6];
			holder.ll_lotto_balls[0] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball1);
			holder.ll_lotto_balls[1] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball2);
			holder.ll_lotto_balls[2] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball3);
			holder.ll_lotto_balls[3] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball4);
			holder.ll_lotto_balls[4] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball5);
			holder.ll_lotto_balls[5] = (LinearLayout) view.findViewById(R.id.ll_lotto_ball6);
			holder.iv_lotto_balls = new ImageView[6];
			holder.iv_lotto_balls[0] = (ImageView) view.findViewById(R.id.iv_lotto_ball1);
			holder.iv_lotto_balls[1] = (ImageView) view.findViewById(R.id.iv_lotto_ball2);
			holder.iv_lotto_balls[2] = (ImageView) view.findViewById(R.id.iv_lotto_ball3);
			holder.iv_lotto_balls[3] = (ImageView) view.findViewById(R.id.iv_lotto_ball4);
			holder.iv_lotto_balls[4] = (ImageView) view.findViewById(R.id.iv_lotto_ball5);
			holder.iv_lotto_balls[5] = (ImageView) view.findViewById(R.id.iv_lotto_ball6);
			holder.iv_lotto_rank = (ImageView) view.findViewById(R.id.iv_lotto_rank);
			view.setTag(holder);
		} else {
			holder = (ViewChildHolder) view.getTag();
		}

		LottoResultVo lottoResultVo = getChild(groupPosition, childPosition);
		if (lottoResultVo.getLottoNumber() == null) {
			holder.rl_lotto.setVisibility(View.GONE);
			
		} else {
			holder.rl_lotto.setVisibility(View.VISIBLE);
			// lotto_date, 로또번호 표시 (로또 번호에 따라 이미지 변경)
			MypageService.setImageLottoRank(lottoResultVo.getRank(), holder.iv_lotto_rank);
			String lottoNums[] = lottoResultVo.getLottoNumber().split(",");
    		holder.ll_lotto_balls[0].setBackgroundResource(R.drawable.mypage_1g);
    		holder.ll_lotto_balls[1].setBackgroundResource(R.drawable.mypage_2g);
    		holder.ll_lotto_balls[2].setBackgroundResource(R.drawable.mypage_3g);
    		holder.ll_lotto_balls[3].setBackgroundResource(R.drawable.mypage_4g);
    		holder.ll_lotto_balls[4].setBackgroundResource(R.drawable.mypage_5g);
    		holder.ll_lotto_balls[5].setBackgroundResource(R.drawable.mypage_6g);
			for (int lottoCnt = 0; lottoCnt < lottoNums.length; lottoCnt++) {
				String winningNums[] = null;
	    		if( lottoResultVo.getWinningNumber() != null){
	    			winningNums = lottoResultVo.getWinningNumber().split(",");
	    		}
				MypageService.setImageLottoBall(winningNums, holder.ll_lotto_balls[lottoCnt], holder.iv_lotto_balls[lottoCnt], lottoNums[lottoCnt], lottoCnt);
			}
		}

		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	private class ViewHolder {
		// Parent
    	TextView tv_lotto_age;
    	TextView tv_winning_year;
    	TextView tv_winning_date;
    	ImageView iv_win_balls[];
    	ImageView iv_lotto_top_rank;
	}

	private class ViewChildHolder {
		// Child
		RelativeLayout rl_lotto;
    	LinearLayout ll_lotto_balls[];
    	ImageView iv_lotto_balls[];
    	ImageView iv_lotto_top_rank;
    	ImageView iv_lotto_rank;
	}
}
