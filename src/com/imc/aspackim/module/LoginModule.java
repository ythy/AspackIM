package com.imc.aspackim.module;

import com.imc.aspackim.activity.LoginActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;

 
@Module(
        library = true,
        injects = {LoginActivity.class}
)

public class LoginModule {

    private final Context mContext;

    public LoginModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context providesContext() {
        return mContext;
    }
    
    @Provides
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
