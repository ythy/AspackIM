package com.imc.aspackim.fragment;

import javax.inject.Inject;

import com.imc.aspackim.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FriendListFragment extends Fragment {
	@Inject
    public FriendListFragment() {}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_friendlist, container,
				false);
		return rootView;
	}
}
