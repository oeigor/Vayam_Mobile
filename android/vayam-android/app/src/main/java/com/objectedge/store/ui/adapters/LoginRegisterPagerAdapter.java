package com.objectedge.store.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.objectedge.store.ui.fragments.LoginFragment;
import com.objectedge.store.ui.fragments.RegisterFragment;


/**
 * Created by "Michael Katkov" on 9/18/2015.
 */
public class LoginRegisterPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Register", "Login" };

    public LoginRegisterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return RegisterFragment.newInstance();
        } else if(position == 1){
            return LoginFragment.newInstance();
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
