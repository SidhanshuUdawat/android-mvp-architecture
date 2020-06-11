package com.app.sid.funwithflags.view.wikiview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.utils.ProgressHelper;
import com.app.sid.funwithflags.utils.AppUtils;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WikiFragment extends Fragment implements WikiContract.View {

    @BindView(R.id.web_view)
    public WebView mWebView;

    @BindView(R.id.progressBar)
    public ProgressBar mProgress;

    private String mCountryName;

    private String URL = "https://en.m.wikipedia.org/wiki/";

    private WikiContract.Presenter mPresenter;

    private Unbinder unbinder;

    public WikiFragment() {
    }

    public static WikiFragment newInstance(String c_name) {
        WikiFragment fragment = new WikiFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WikiActivity.C_NAME, c_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mCountryName = getArguments().getString(WikiActivity.C_NAME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_wiki, container, false);
        unbinder = ButterKnife.bind(this, root);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (mProgress != null)
                    mProgress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgress != null)
                    mProgress.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

        mWebView.loadUrl(URL + mCountryName);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        if (!ProgressHelper.getInstance().isRunning())
            ProgressHelper.getInstance().showProgress(getContext(),
                    getString(R.string.please_wait), null);
    }

    @Override
    public void hideProgress() {
        ProgressHelper.getInstance().hideProgress();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public boolean isGoBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            return false;
        }
    }

    public void dispatchError(int resId) {
        hideProgress();
        showMessage(getString(resId));
    }

    private void showMessage(String message) {
        AppUtils.showSnackBar(getView(),
                message, Snackbar.LENGTH_LONG, null);
    }
}
