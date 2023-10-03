package com.app.doctorapp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.rx.SchedulerProvider;
import com.app.doctorapp.utils.preference.UtilsSharedPreferences;
import com.app.doctorapp.view.activity.MainActivity;
import com.app.doctorapp.view.activity.WelcomeActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseFragment extends Fragment {
    @Inject
    protected MyApplication mApplication;
    @Inject
    protected UtilsSharedPreferences preferences;
    @Inject
    protected SchedulerProvider mSchedulers;
    protected BaseActivity mActivity;
    protected MainActivity mActivityMain;
    protected WelcomeActivity mActivityWelcome;
    protected Context mContext;
    protected boolean reLoaded = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity = (BaseActivity) getActivity();
        try {
            mActivityMain = (MainActivity) getActivity();

        } catch (Exception e) {

        }
        try {
            mActivityWelcome = (WelcomeActivity) getActivity();

        } catch (Exception e) {

        }
        mContext = BaseFragment.this.getContext();
    }

}
