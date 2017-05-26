package com.app.sid.funwithflags.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.app.sid.funwithflags.R;

/**
 * Created by sidhanshu.udawat
 */

public class Utils {

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
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);

        snackbar.show();
    }
}
