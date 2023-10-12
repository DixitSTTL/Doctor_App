package com.app.doctorapp.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;

import com.app.doctorapp.R;
import com.google.android.material.snackbar.Snackbar;

public class Utils {
    public static void showSnackBar(View viewLayout, String toastMessage) {
        try {
            Snackbar snackbar;

            snackbar = Snackbar.make(viewLayout, toastMessage, Snackbar.LENGTH_LONG);
//            snackbar.getView().setBackgroundColor(R.color.white);
            snackbar.setBackgroundTint(ContextCompat.getColor(viewLayout.getContext(), R.color.black));
            snackbar.setTextColor(ContextCompat.getColor(viewLayout.getContext(), R.color.white));

            snackbar.show();
        } catch (Exception exception) {
        }


    }


    public static String generateAppointmentUID(String patient_uid, String doctor_uid) {

        return patient_uid + "_" + doctor_uid;
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null) {
                IBinder iBinder = activity.getCurrentFocus().getWindowToken();
                if (iBinder != null && inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(iBinder, 0);
                }
            }
        } catch (Exception exception) {
//            Logger.log(exception.toString());
        }
    }
}
