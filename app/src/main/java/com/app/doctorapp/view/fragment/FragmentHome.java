package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.FragmentNavigator;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelHome;
import com.app.doctorapp.databinding.FragmentHomeBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentHome extends BaseFragment {

    private FragmentHomeBinding mBinding;
    private FragViewModelHome mViewModel;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mViewModel != null) {
            reLoaded = true;
            return mBinding.getRoot();
        }
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_home, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelHome.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (reLoaded) {
            return;
        }
        initToolbar();
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mBinding.setGeneralItemListener(generalItemClickListener);
        mViewModel.loadCategories();
        mViewModel.loadDoctors();
    }

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {


        }
    };

    private GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {
            FragmentHomeDirections.ActionFragmentHomeToFragmentDoctorInfo action = FragmentHomeDirections.actionFragmentHomeToFragmentDoctorInfo(mViewModel.observeDoctorList.get(position).getId());
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(view, view.getTransitionName()).build();
            mActivityMain.navigateDoctorDetails(action, extras);

        }
    };

    private void initToolbar() {
        mActivityMain.setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setTitle("Welcome Back, " + preferences.getString(R.string.user_name) + "!");
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityMain.onBackPressed();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.notiBtn) {
            mActivityMain.navigateToNotification();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}