package com.app.doctorapp.view.fragment.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.doctor.FragViewModelAppointments;
import com.app.doctorapp.databinding.FragmentAppointmentsBinding;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.utils.Logger;
import com.app.doctorapp.view.BaseFragment;
import com.app.doctorapp.view.adapter.AdapterAppointments;

public class FragmentAppointments extends BaseFragment {

    private FragViewModelAppointments mViewModel;
    private FragmentAppointmentsBinding mBinding;
    private AdapterAppointments adapterAppointments;

    public FragmentAppointments() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(mActivityDoc).get(FragViewModelAppointments.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViewModel.isPageLoaded.get()) {
            return mBinding.getRoot();
        }

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_appointments, container, false);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mBinding.setGeneralItemListener(generalItemClickListener);
        if (!mViewModel.isPageLoaded.get()) {
            mViewModel.loadMyAppointments();
            Logger.e("load", "loading appointments");
            setObserver();
        }


    }

    private void setObserver() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        manager.setSmoothScrollbarEnabled(true);
        adapterAppointments = new AdapterAppointments(mViewModel.observeAppoList, generalItemClickListener);
        mBinding.recAppo.setAdapter(adapterAppointments);
        mBinding.recAppo.setLayoutManager(manager);

        mViewModel.observeAppoList.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                adapterAppointments.notifyDataSetChanged();
//                manager.scrollToPosition(0);
//                manager.smoothScrollToPosition(mBinding.recChats, null, 0);
            }

            @Override
            public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {

            }
        });
    }

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

            FragmentAppointmentsDirections.ActionFragmentAppointmentsToFragmentPrescription action =
                    FragmentAppointmentsDirections.actionFragmentAppointmentsToFragmentPrescription(
                            ((AppointmentModel) item).getPatient_uid()
                    );

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(view,view.getTransitionName()).build();

            mActivityDoc.navigatePrescription(action,extras);

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.registration.remove();
        }
    }
}