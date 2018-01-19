package com.app.sid.funwithflags.view.wikiview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.app.sid.funwithflags.R;
import com.app.sid.funwithflags.utils.ActivityUtils;

public class WikiActivity extends AppCompatActivity {

    public static String C_NAME = "country_name";

    private WikiContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);

        String c_name = getIntent().getStringExtra(C_NAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(c_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        WikiFragment wikiFragment =
                (WikiFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (wikiFragment == null) {
            // Create the fragment
            wikiFragment = WikiFragment.newInstance(c_name);
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), wikiFragment, R.id.contentFrame);
        }

        // Create the presenter
        mPresenter = new WikiPresenter(wikiFragment);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this.supportFinishAfterTransition();
                } else {
                    this.finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mPresenter.isGoBack()) {
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }

}
