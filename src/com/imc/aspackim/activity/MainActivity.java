package com.imc.aspackim.activity;

import javax.inject.Inject;
import com.imc.aspackim.R;
import com.imc.aspackim.application.InjectedApplication;
import com.imc.aspackim.fragment.FriendListFragment;
import com.imc.aspackim.fragment.MessageListFragment;
import com.imc.aspackim.fragment.TitleFragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends BaseActivity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int FRAGMENT_MESSAGE = 1; 
	private static final int FRAGMENT_FRIEND = 2; 
	private FragmentManager mFragmentManager;
	private Button btnMessage;
	private Button btnFriend;
	
	@Inject MessageListFragment mMessageListFragment;
	@Inject FriendListFragment mFriendListFragment;
	@Inject SharedPreferences mSharedPreferences;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		((InjectedApplication) getApplication()).injectMainActivity(this);
		setContentView(R.layout.activity_main);
		mFragmentManager = getFragmentManager();
		if (savedInstanceState == null) {
			setFragment(FRAGMENT_MESSAGE);
		}
		
		btnMessage = (Button) findViewById(R.id.button_bar_message);
		btnMessage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFragment(FRAGMENT_MESSAGE);
			}
			
		});
		
		btnFriend = (Button) findViewById(R.id.button_bar_friend);
		btnFriend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setFragment(FRAGMENT_FRIEND);
			}
			
		});
	}
	
	private void setFragment(int id)
	{
		TitleFragment title = (TitleFragment) mFragmentManager.findFragmentById(R.id.fragment_title);
		switch(id)
		{
			case  FRAGMENT_MESSAGE :
				mFragmentManager.beginTransaction()
				.replace(R.id.frame_content, mMessageListFragment)
				.addToBackStack(null).commit();
				title.setTitle("messages");
				break;
			case  FRAGMENT_FRIEND :
				mFragmentManager.beginTransaction()
				.replace(R.id.frame_content, mFriendListFragment)
				.addToBackStack(null).commit();
				title.setTitle("friends");
				break;	
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
 
}
