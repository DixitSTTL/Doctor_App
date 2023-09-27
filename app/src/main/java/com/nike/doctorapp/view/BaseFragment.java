package com.nike.doctorapp.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nike.doctorapp.MyApplication;
import com.nike.doctorapp.businesslogic.rx.SchedulerProvider;
import com.nike.doctorapp.utils.preference.UtilsSharedPreferences;
import com.nike.doctorapp.view.activity.MainActivity;
import com.nike.doctorapp.view.activity.WelcomeActivity;

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity = (BaseActivity) getActivity();
//        mActivityMain = (MainActivity) getActivity();
        mActivityWelcome = (WelcomeActivity) getActivity();
        mContext = BaseFragment.this.getContext();
    }

}
