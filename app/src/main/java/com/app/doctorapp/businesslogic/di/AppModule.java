package com.app.doctorapp.businesslogic.di;

import android.content.Context;


import com.app.doctorapp.MyApplication;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    Context provideContext(@ApplicationContext Context context) {
        return context;
    }


    @Provides
    MyApplication provideApplication(Context context) {
        return ((MyApplication) context);
    }

}
