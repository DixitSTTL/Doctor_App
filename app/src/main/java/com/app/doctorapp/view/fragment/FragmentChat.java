package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelChat;
import com.app.doctorapp.databinding.FragmentChatBinding;
import com.app.doctorapp.models.ChatOuter;
import com.app.doctorapp.view.BaseFragment;
import com.app.doctorapp.view.adapter.AdapterChatsHome;
import com.app.doctorapp.view.fragment.FragmentChatDirections;

public class FragmentChat extends BaseFragment {

   private FragViewModelChat mViewModel;
    private FragmentChatBinding mBinding;
    private AdapterChatsHome adapterChatsHome;

    public FragmentChat() {
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

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_chat, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelChat.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (reLoaded) {
            return;
        }
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mBinding.setGeneralItemListener(generalItemClickListener);
        mViewModel.loadMyChat();
        setObserver();
    }

    private void setObserver() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        manager.setSmoothScrollbarEnabled(true);
        adapterChatsHome = new AdapterChatsHome(mViewModel.observeChatList, generalItemClickListener);
        mBinding.recChats.setAdapter(adapterChatsHome);
        mBinding.recChats.setLayoutManager(manager);

        mViewModel.observeChatList.addOnListChangedCallback(new ObservableList.OnListChangedCallback() {
            @Override
            public void onChanged(ObservableList sender) {

            }

            @Override
            public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            }

            @Override
            public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
                adapterChatsHome.notifyDataSetChanged();
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

    private final GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private final GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {
            FragmentChatDirections.ActionFragmentChatToFragmentChatCore action =
                    FragmentChatDirections.actionFragmentChatToFragmentChatCore(((ChatOuter) item).getDoctor_uid());
            FragmentNavigator.Extras extras =
                    new FragmentNavigator.Extras.Builder()
                            .addSharedElement(view, view.getTransitionName())
                            .build();

            mActivityMain.navigateChatCore(action,extras);

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