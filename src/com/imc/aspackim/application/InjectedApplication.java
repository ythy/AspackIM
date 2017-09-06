/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.imc.aspackim.application;

import com.imc.aspackim.module.LoginModule;
import com.imc.aspackim.module.MainModule;

import android.app.Application;
import android.util.Log;

import dagger.ObjectGraph;

/**
 * The Application class of the sample which holds the ObjectGraph in Dagger and enables
 * dependency injection.
 */
public class InjectedApplication extends Application {

    private static final String TAG = InjectedApplication.class.getSimpleName();

    private ObjectGraph mObjectGraphMain;
    private ObjectGraph mObjectGraphLogin;

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraphMain = initObjectGraph(new MainModule(this));
        mObjectGraphLogin = initObjectGraph(new LoginModule(this));
    }

    /**
     * Initialize the Dagger module. Passing null or mock modules can be used for testing.
     *
     * @param module for Dagger
     */
    public ObjectGraph initObjectGraph(Object module) {
        return module != null ? ObjectGraph.create(module) : null;
    }

    public void injectMainActivity(Object object) {
        if (mObjectGraphMain == null) {
            Log.i(TAG, "Object graph is not initialized.");
            return;
        }
        mObjectGraphMain.inject(object);
    }
    
    public void injectLoginActivity(Object object) {
        if (mObjectGraphLogin == null) {
            Log.i(TAG, "Object graph is not initialized.");
            return;
        }
        mObjectGraphLogin.inject(object);
    }
    
    
}
