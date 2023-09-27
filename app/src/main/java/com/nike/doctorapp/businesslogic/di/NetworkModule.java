package com.nike.doctorapp.businesslogic.di;



import android.content.Context;

import com.nike.doctorapp.businesslogic.rx.AppSchedulerProvider;
import com.nike.doctorapp.businesslogic.rx.SchedulerProvider;
import com.nike.doctorapp.utils.preference.UtilsSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

/*
    @Provides
    @Singleton
    ApiHelper providesRetrofitInterface() {
        return ApiCallFactory.create(getBaseURL());
    }
*/

    @Provides
    SchedulerProvider providesScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    UtilsSharedPreferences providesPreferences(Context context) {
        return new UtilsSharedPreferences(context);
    }
}
