package com.imc.aspackim.fragment;

import javax.inject.Inject;

import com.imc.aspackim.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageListFragment extends Fragment {
	
	@Inject
    public MessageListFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_messagelist, container,
				false);
		return rootView;
	}

}
