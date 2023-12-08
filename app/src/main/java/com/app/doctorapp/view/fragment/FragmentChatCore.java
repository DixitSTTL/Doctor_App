package com.app.doctorapp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.ArcMotion;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelChatCore;
import com.app.doctorapp.databinding.FragmentChatCoreBinding;
import com.app.doctorapp.models.ChatInSide;
import com.app.doctorapp.view.BaseFragment;
import com.app.doctorapp.view.adapter.AdapterChatsCore;
import com.app.doctorapp.view.fragment.FragmentChatCoreArgs;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialElevationScale;

public class FragmentChatCore extends BaseFragment {

    private FragViewModelChatCore mViewModel;
    private FragmentChatCoreBinding mBinding;
    private AdapterChatsCore adapterChatsCore;
    private String doctor_uid;
    private ObservableList.OnListChangedCallback<ObservableList<ChatInSide>> onListChangedCallback;


    public FragmentChatCore() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSharedElementEnterTransition(TransitionInflater.from(mContext)
                .inflateTransition(R.transition.shared_element_transition));
        if (getArguments() != null) {
            doctor_uid = FragmentChatCoreArgs.fromBundle(getArguments()).getUID();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_chat_core, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelChatCore.class);
        // Inflate the layout for this fragment
        initToolbar();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.imageView5.setTransitionName("Day");
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setGeneralListener(generalClickListener);
        setObserver();
        mViewModel.loadChatOuter(doctor_uid, getActivity());
        startPostponedEnterTransition();


        MaterialContainerTransform mt = new MaterialContainerTransform(mContext, true);
        mt.setScrimColor(Color.TRANSPARENT);
        mt.setDuration(600);
        mt.setPathMotion(new ArcMotion());
        setSharedElementEnterTransition(mt);
        ViewCompat.setTransitionName(mBinding.cc, "container" + doctor_uid);
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

    private void setObserver() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        manager.setSmoothScrollbarEnabled(true);
        manager.setStackFromEnd(true);
        adapterChatsCore = new AdapterChatsCore(mViewModel.observeChatList, generalItemClickListener, doctor_uid);
        mBinding.recChats.setAdapter(adapterChatsCore);
        mBinding.recChats.setLayoutManager(manager);

        onListChangedCallback = new ObservableList.OnListChangedCallback<ObservableList<ChatInSide>>() {
            @Override
            public void onChanged(ObservableList<ChatInSide> sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList<ChatInSide> sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList<ChatInSide> sender, int positionStart, int itemCount) {
                adapterChatsCore.notifyItemInserted(itemCount);
                manager.smoothScrollToPosition(mBinding.recChats, null, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<ChatInSide> sender, int fromPosition, int toPosition, int itemCount) {

            }

            @Override
            public void onItemRangeRemoved(ObservableList<ChatInSide> sender, int positionStart, int itemCount) {

            }
        };

        mViewModel.observeChatList.addOnListChangedCallback(onListChangedCallback);

    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

        }
    };

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.sendBtn) {
                mViewModel.sendMessage();
            } else {


            }

        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.observeChatList.removeOnListChangedCallback(onListChangedCallback);
        }
    }
}