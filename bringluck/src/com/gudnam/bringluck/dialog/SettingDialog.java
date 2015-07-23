package com.gudnam.bringluck.dialog;

import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gudnam.bringluck.R;

public class SettingDialog extends DialogFragment{

	public static SettingDialog newInstance(){
		SettingDialog frag = new SettingDialog();
		Bundle args = new Bundle();
		
		return frag;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_setting, container);
		return view;
	}
}

