package com.app.sid.funwithflags.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.app.sid.funwithflags.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by sidhanshu.udawat
 */

public class AppUtils {

    public interface AlertOK {
        void alertOkClick();
    }

    public static void showSnackBar(View view,
                                    String msg,
                                    int duration,
                                    final AlertOK ok) {
        Snackbar snackbar = Snackbar
                .make(view, msg, duration)
                .setAction(view.getContext().getString(R.string.OK), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ok != null)
                            ok.alertOkClick();
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(Color.WHITE);

        // Changing action button text color
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(sbView.getResources().getColor(R.color.colorAccent));
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }

    public static void restartApp(Context context) {
        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage(context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
        System.exit(0);
    }
}
