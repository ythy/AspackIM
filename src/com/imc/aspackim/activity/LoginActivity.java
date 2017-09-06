package com.imc.aspackim.activity;

import javax.inject.Inject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.imc.aspackim.R;
import com.imc.aspackim.application.InjectedApplication;
import com.imc.aspackim.fragment.RegisterDialogFragment;
import com.imc.aspackim.service.XmppManager;
import com.imc.aspackim.util.UIHelper;
import com.imc.aspackim.vo.ReturnData;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

	private static final String DIALOG_FRAGMENT_TAG = "registerFragment";
	
	// UI references.
	private AutoCompleteTextView mUserView;
	private EditText mPasswordView;
	
	@Inject RegisterDialogFragment mRegisterDialogFragment;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((InjectedApplication) getApplication()).injectLoginActivity(this);
		setContentView(R.layout.activity_login);

		mUserView = (AutoCompleteTextView) findViewById(R.id.et_user);

		mPasswordView = (EditText) findViewById(R.id.et_password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		Button mEmailSignInButton = (Button) findViewById(R.id.btn_sign_in);
		mEmailSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();
			}
		});
		
		TextView mRegister = (TextView) findViewById(R.id.btn_register);
		mRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptRegister();
			}
		});
		
	}
	
	private void attemptRegister()
	{
		mRegisterDialogFragment.show(getFragmentManager(), DIALOG_FRAGMENT_TAG);
	}
	
	public void closeRegisterDialog()
	{
		mRegisterDialogFragment.dismiss();
	}
	
	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		UIHelper.showProgress(this, true);
		new Thread(){    
            public void run() {
            	ReturnData login = XmppManager.login(mUserView.getText().toString().trim(), mPasswordView.getText().toString().trim());
    			Message msg = handler.obtainMessage();
                msg.what = 1;  
                msg.obj = login;
                handler.sendMessage(msg); 
            }  
        }.start();
	}
	
	
	Handler handler = new Handler(){  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            if(msg.what == 1){  
            	UIHelper.showProgress(LoginActivity.this, false);
            	ReturnData result = (ReturnData) msg.obj;
            	Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            	if(!result.isError())
            	{
            		Intent intent = new Intent();
        			intent.setClass(LoginActivity.this, MainActivity.class);
        			startActivity(intent);
            	}
            }  
        }  

    };  
 
}
