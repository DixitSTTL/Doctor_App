package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelOTP;
import com.app.doctorapp.databinding.FragmentOTPBinding;
import com.app.doctorapp.view.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

public class FragmentOTP extends BaseFragment {
    FragmentOTPBinding mBinding;
    FragViewModelOTP mViewmodel;
    String smsCode;

    public FragmentOTP() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            smsCode = getArguments().getString("smsCode");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_o_t_p, container, false);
        mViewmodel = new ViewModelProvider(mActivityWelcome).get(FragViewModelOTP.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBinding.button) {

                verifyOTP();

            }


        }
    };

    private void verifyOTP() {

        String otp1 = mBinding.editText.getText().toString();
        String otp2 = mBinding.editText2.getText().toString();
        String otp3 = mBinding.editText3.getText().toString();
        String otp4 = mBinding.editText4.getText().toString();
        String otp5 = mBinding.editText5.getText().toString();
        String otp6 = mBinding.editText6.getText().toString();
        if (TextUtils.isEmpty(otp1) || TextUtils.isEmpty(otp2) || TextUtils.isEmpty(otp3) || TextUtils.isEmpty(otp4) || TextUtils.isEmpty(otp5) || TextUtils.isEmpty(otp6)) {

            Toast.makeText(mApplication, "Please enter OTP", Toast.LENGTH_SHORT).show();

        } else {
            String finalOTP = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
            mViewmodel.verify(mActivityWelcome,finalOTP, smsCode);



        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setGeneralClickListener(generalClickListener);
        mBinding.setMViewmodel(mViewmodel);
        initListeners();

    }

    private void initListeners() {


        mBinding.editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    mBinding.editText.setText("");
                }
                return false;
            }
        });
        mBinding.editText2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {

                    mBinding.editText2.setText("");
                    mBinding.editText.requestFocus();

                }
                return false;
            }
        });
        mBinding.editText3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    mBinding.editText3.setText("");
                    mBinding.editText2.requestFocus();
                }
                return false;
            }
        });
        mBinding.editText4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {

                    mBinding.editText4.setText("");
                    mBinding.editText3.requestFocus();

                }
                return false;
            }
        });
        mBinding.editText5.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {

                    mBinding.editText5.setText("");
                    mBinding.editText4.requestFocus();

                }
                return false;
            }
        });
        mBinding.editText6.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {

                    mBinding.editText6.setText("");
                    mBinding.editText5.requestFocus();

                }
                return false;
            }
        });


        mBinding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mBinding.editText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mBinding.editText3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mBinding.editText4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mBinding.editText5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    mBinding.editText6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}