package com.app.doctorapp.view.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelDoctorDetails;
import com.app.doctorapp.databinding.FragmentDoctorInfoBinding;
import com.app.doctorapp.view.BaseFragment;
import com.app.doctorapp.view.adapter.AdapterDate;
import com.app.doctorapp.view.adapter.AdapterTimeSlot;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.transition.MaterialContainerTransform;

public class FragmentDoctorInfo extends BaseFragment implements OnMapReadyCallback {

    private FragmentDoctorInfoBinding mBinding;
    private FragViewModelDoctorDetails mViewModel;
    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFrag;
    private String UID;
    private AdapterDate adapterDate;
    private AdapterTimeSlot adapterTimeSlot;

    public FragmentDoctorInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UID = FragmentDoctorInfoArgs.fromBundle(getArguments()).getUID();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_doctor_info, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelDoctorDetails.class);
        mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        initToolbar();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void initToolbar() {

        mActivityMain.setSupportActionBar(mBinding.toolbar);

        mActivityMain.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        mActivityMain.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityMain.onBackPressed();
            }
        });
    }


    private GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {
            if (view.getId() == R.id.dateCC) {
                mViewModel.observeSelectedDate.set(position);
                adapterDate.setPoistion(position);
                adapterDate.notifyDataSetChanged();
            } else if (view.getId() == R.id.timeCC) {
                mViewModel.observeSelectedTime.set(position);
                adapterTimeSlot.setPoistion(position);
                adapterTimeSlot.notifyDataSetChanged();
            }

        }
    };

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (mViewModel.observeSelectedDate.get() == -1 || mViewModel.observeSelectedTime.get() == -1) {

                mViewModel.observerSnackBarString.set("Please select Date and Time Slot");

            } else {
                FragmentDoctorInfoDirections.ActionFragmentDoctorInfoToFragmentPrePayment action =
                        FragmentDoctorInfoDirections.actionFragmentDoctorInfoToFragmentPrePayment(
                                mViewModel.observeMainDetail.get(),
                                mViewModel.observeSecondaryDetail.get(),
                                mViewModel.observeDateList.get(mViewModel.observeSelectedDate.get()),
                                mViewModel.observeTimeSlot.get(mViewModel.observeSelectedTime.get()),
                                UID
                        );
//                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().addSharedElement(view, view.getTransitionName()).build();


                mActivityMain.navigatePrePayment(action);
            }

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialContainerTransform mt = new MaterialContainerTransform(mContext, true);
        mt.setContainerColor(Color.WHITE);
        mt.setScrimColor(Color.WHITE);
        mt.setStartContainerColor(Color.WHITE);
        mt.setAllContainerColors(Color.WHITE);
        setSharedElementEnterTransition(mt);
        ViewCompat.setTransitionName(mBinding.cc, "container" + UID);


        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mViewModel.loadData(UID);
        setDateAdapter();
        setTimeAdapter();

    }

    private void setDateAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        adapterDate = new AdapterDate(mViewModel.observeDateList, generalItemClickListener, mViewModel.observeSelectedDate.get());
        mBinding.recDate.setAdapter(adapterDate);
        mBinding.recDate.setLayoutManager(linearLayoutManager);
    }

    private void setTimeAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
        adapterTimeSlot = new AdapterTimeSlot(mViewModel.observeTimeSlot, generalItemClickListener, mViewModel.observeSelectedTime.get());
        mBinding.recTimeSlot.setAdapter(adapterTimeSlot);
        mBinding.recTimeSlot.setLayoutManager(layoutManager);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;
//        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        try {
            boolean success = mGoogleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mContext, R.raw.map));

            Log.e("TAG", "Style parsing " + success);

        } catch (Resources.NotFoundException e) {
            Log.e("TAG", "Can't find style. Error: ", e);
        }

        updater(new LatLng(23.021447, 72.554816));
    }

    public void updater(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("You are here");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mGoogleMap.addMarker(markerOptions);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Math.min(mGoogleMap.getMaxZoomLevel(), 14)));
        mGoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                mBinding.scrollView.requestDisallowInterceptTouchEvent(true);

            }
        });
        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mBinding.scrollView.requestDisallowInterceptTouchEvent(false);

            }
        });
    }
}