package com.app.sid.funwithflags.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

public class ProgressHelper {

    private ProgressDialog progressDialog;
    private boolean isRunning;
    private static ProgressHelper ourInstance = new ProgressHelper();

    public static ProgressHelper getInstance() {
        return ourInstance;
    }

    private ProgressHelper() {
    }

    public void showProgress(Context context, String msg, String mTitle) {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            if (!TextUtils.isEmpty(mTitle))
                progressDialog.setTitle(mTitle);
            progressDialog.setCancelable(false);
            progressDialog.show();
            isRunning = true;
        } catch (Exception e) {
            progressDialog = null;
        }
    }

    public void hideProgress() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
                isRunning = false;
            }
        } catch (Exception e) {
            progressDialog = null;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

}
