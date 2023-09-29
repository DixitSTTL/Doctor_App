package com.app.doctorapp.businesslogic.di;



import android.content.Context;

import com.app.doctorapp.businesslogic.rx.AppSchedulerProvider;
import com.app.doctorapp.businesslogic.rx.SchedulerProvider;
import com.app.doctorapp.utils.preference.UtilsSharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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

    @Provides
    @Singleton
    FirebaseAuth providesFireAuth() {
        return  FirebaseAuth.getInstance();
    }
    @Provides
    @Singleton
    FirebaseFirestore providesFireStore() {
        return  FirebaseFirestore.getInstance();
    }
}
