package com.objectedge.store.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.objectedge.store.R;
import com.objectedge.store.ui.adapters.LoginRegisterPagerAdapter;

public class LoginRegisterActivity extends BaseActivity {

    public final static String TAG = "LoginRegisterActivity";

    public static final String START_ACTIVITY_AFTER = "start_activity";

    public static void start(Context context, String quitValue){
        Intent startActivityIntent = new Intent(context, LoginRegisterActivity.class);
        startActivityIntent.putExtra(TAG, quitValue);
        context.startActivity(startActivityIntent);
    }

    public static void startForResult(Activity activity, String quitValue, int requestCode){
        Intent startActivityIntent = new Intent(activity, LoginRegisterActivity.class);
        startActivityIntent.putExtra(TAG, quitValue);
        activity.startActivityForResult(startActivityIntent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new LoginRegisterPagerAdapter(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
