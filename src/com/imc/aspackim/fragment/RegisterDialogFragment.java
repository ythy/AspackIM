package com.imc.aspackim.fragment;

import javax.inject.Inject;
import org.jivesoftware.smack.XMPPConnection;
import com.imc.aspackim.R;
import com.imc.aspackim.activity.LoginActivity;
import com.imc.aspackim.activity.MainActivity;
import com.imc.aspackim.service.XmppManager;
import com.imc.aspackim.service.XmppUtil;
import com.imc.aspackim.util.UIHelper;
import com.imc.aspackim.vo.ReturnData;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterDialogFragment extends DialogFragment {
	
	private LoginActivity mActivity;
	
	@Inject
    public RegisterDialogFragment() {}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Do not create a new Fragment when the Activity is re-created such as orientation changes.
        setRetainInstance(true);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog);
    }
	
	
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.action_register));
        View v = inflater.inflate(R.layout.dialog_register, container, false);
        
        final EditText mUser = (EditText) v.findViewById(R.id.et_user);
        final EditText mPassword = (EditText) v.findViewById(R.id.et_password);
        EditText mRePassword = (EditText) v.findViewById(R.id.et_repassword);
        Button mConfirm = (Button) v.findViewById(R.id.btn_register_confirm);
        mConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UIHelper.showProgress(mActivity, true);
				new Thread(){    
		            public void run() {
		            	ReturnData login = XmppManager.register(mUser.getText().toString().trim(), mPassword.getText().toString().trim());
		    			Message msg = handler.obtainMessage();
		                msg.what = 1;  
		                msg.obj = login;
		                handler.sendMessage(msg); 
		            }  
		        }.start();
				
			}
		});
        
        return v;
	 }
	        
	 Handler handler = new Handler(){  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg);  
            if(msg.what == 1){  
            	UIHelper.showProgress(mActivity, false);
            	ReturnData result = (ReturnData) msg.obj;
            	Toast.makeText(mActivity, result.getMessage(), Toast.LENGTH_SHORT).show();
            	if(!result.isError())
            	{
            		mActivity.closeRegisterDialog();
            	}
            }  
        }  

    };  
	    
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (LoginActivity) activity;
    }
}
