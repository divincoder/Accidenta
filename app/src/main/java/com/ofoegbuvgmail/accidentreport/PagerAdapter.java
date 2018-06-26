package com.ofoegbuvgmail.accidentreport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ofoegbuvgmail.accidentreport.fragments.AlertsFragment;
import com.ofoegbuvgmail.accidentreport.fragments.ReportsFragment;

/**
 * Created by Ofoegbu Valentine on 27/04/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    Bundle fragBundle = null;

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ReportsFragment.newInstance(fragBundle);
            case 1:
                return AlertsFragment.newInstance(fragBundle);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
