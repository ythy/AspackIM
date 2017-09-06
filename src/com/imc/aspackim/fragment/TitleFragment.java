package com.imc.aspackim.fragment;

import com.imc.aspackim.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TitleFragment extends Fragment {
	
	private TextView mTitle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_title, container,
				false);
		
		mTitle = (TextView) rootView.findViewById(R.id.tv_title);
		
		return rootView;
	}
	
	public void setTitle(String value)
	{
		mTitle.setText(value);
	}
}
