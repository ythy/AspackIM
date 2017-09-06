package com.imc.aspackim.util;

import android.app.FragmentTransaction;

import com.imc.aspackim.activity.BaseActivity;
import com.imc.aspackim.fragment.ProgressDialogFragment;

public class UIHelper {
	
	private static ProgressDialogFragment dialog;
	
	public static void showProgress(BaseActivity activity, boolean show)
	{
		if(show)
		{
			 FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
			 dialog = new ProgressDialogFragment();
		     dialog.show(transaction, "progress");
		}
		else if(dialog != null && !show)
		{
			dialog.dismiss();
		}
		
	}
	 

}
