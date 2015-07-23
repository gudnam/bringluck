package com.gudnam.bringluck.main.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.gudnam.bringluck.R;
import com.gudnam.bringluck.domain.LottoVo;
import com.gudnam.bringluck.main.event.MainEvent;
import com.gudnam.bringluck.qrcode.service.QRCodeService;
import com.gudnam.bringluck.sqlite.BLSQLiteQuery;

public class MainFragment extends Fragment {

	private Spinner sp_lotto = null;
	private ImageButton btn_mypage = null;
	private ImageButton btn_save = null;
	private EditText et_num1, et_num2, et_num3, et_num4, et_num5, et_num6 = null;
	private ImageView iv_check = null;
	private RelativeLayout rl_alarm_check = null;
	private String mSelectedValue = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_main, container, false);
		
		sp_lotto = (Spinner) view.findViewById(R.id.sp_lotto);
		et_num1 = (EditText) view.findViewById(R.id.main_01);
		et_num2 = (EditText) view.findViewById(R.id.main_02);
		et_num3 = (EditText) view.findViewById(R.id.main_03);
		et_num4 = (EditText) view.findViewById(R.id.main_04);
		et_num5 = (EditText) view.findViewById(R.id.main_05);
		et_num6 = (EditText) view.findViewById(R.id.main_06);
		btn_mypage = (ImageButton) view.findViewById(R.id.btn_mypage);
		btn_save = (ImageButton) view.findViewById(R.id.btn_go);
		rl_alarm_check = (RelativeLayout) view.findViewById(R.id.rl_alarm_check);
		iv_check = (ImageView) view.findViewById(R.id.iv_check);

		SharedPreferences preference = getActivity().getSharedPreferences("setting", 0);
		boolean isAlarm = preference.getBoolean("isAlarm", true);
		if( isAlarm){
			iv_check.setVisibility(View.VISIBLE);
		} else {
			iv_check.setVisibility(View.INVISIBLE);
		}
		
		btn_mypage.setOnClickListener(new MainEvent(getActivity()));
		rl_alarm_check.setOnClickListener(new MainEvent(getActivity()));
		
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if( !isInvalidCheck()){
					Toast.makeText(getActivity(), getActivity().getString(R.string.invalid_lotto_num), Toast.LENGTH_SHORT).show();
					return;
				}
				String lottoNum = String.format("%s,%s,%s,%s,%s,%s", 
								et_num1.getText().toString(), et_num2.getText().toString(),
								et_num3.getText().toString(), et_num4.getText().toString(),
								et_num5.getText().toString(), et_num6.getText().toString());
				
				QRCodeService service = new QRCodeService(getActivity());
				
				// 사용자 로또 정보에 저장
				if( service.saveUserLottoInfo(mSelectedValue, lottoNum, new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
					Toast.makeText(getActivity(), getActivity().getString(R.string.lotto_num_enter_success), Toast.LENGTH_SHORT).show();
					clearEditText();
				}
				else{
					Toast.makeText(getActivity(), getActivity().getString(R.string.lotto_num_enter_fail), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		List<LottoVo> lottoVoList = BLSQLiteQuery.getLottoAll();
		
		String[] items = new String[lottoVoList.size()+1]; // 다음 회차까지 저장되어야 함으로
		if( items.length > 1){
			// 다음 회차부터 저장
			int nextAge = Integer.parseInt(lottoVoList.get(0).getLottoAge())+1;
			items[0] = String.valueOf(nextAge);
			for(int lottoCnt=0; lottoCnt<lottoVoList.size(); lottoCnt++){
				items[lottoCnt+1] = lottoVoList.get(lottoCnt).getLottoAge();
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getActivity(), R.layout.layout_spinner_item, items);
			adapter.setDropDownViewResource(R.layout.layout_spinner_dropdown_item);
			sp_lotto.setPrompt(getActivity().getText(R.string.select_lotto_age));
			sp_lotto.setSelection(0);
			sp_lotto.setAdapter(adapter);
			mSelectedValue = (String) sp_lotto.getAdapter().getItem(0);
			sp_lotto.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					mSelectedValue = (String) sp_lotto.getAdapter().getItem(position);
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
			});
		}

		return view;
	}
	
	private boolean isInvalidCheck(){
		try{
			int nums[] = new int[6];
			nums[0] = Integer.parseInt(et_num1.getText().toString());
			nums[1] = Integer.parseInt(et_num2.getText().toString());
			nums[2] = Integer.parseInt(et_num3.getText().toString());
			nums[3] = Integer.parseInt(et_num4.getText().toString());
			nums[4] = Integer.parseInt(et_num5.getText().toString());
			nums[5] = Integer.parseInt(et_num6.getText().toString());
			
			// 중복 검사
			for(int numCnt=0; numCnt<nums.length; numCnt++){
				for(int compareCnt=0; compareCnt<nums.length; compareCnt++){
					if( nums[numCnt] == nums[compareCnt]){
						if( numCnt != compareCnt){
							return false;
						}
					}
				}
			}
			
			// 1~45 검사
			if( nums[0] > 45 || nums[0] < 1){
				return false;
			}
			else if( nums[1] > 45 || nums[1] < 1){
				return false;
			}
			else if( nums[2] > 45 || nums[2] < 1){
				return false;
			}
			else if( nums[3] > 45 || nums[3] < 1){
				return false;
			}
			else if( nums[4] > 45 || nums[4] < 1){
				return false;
			}
			else if( nums[5] > 45 || nums[5] < 1){
				return false;
			}
		}
		catch(Exception ex){
			return false;
		}
		
		return true;
	}

	private void clearEditText(){
		et_num1.setText("");
		et_num2.setText("");
		et_num3.setText("");
		et_num4.setText("");
		et_num5.setText("");
		et_num6.setText("");
	}
}
