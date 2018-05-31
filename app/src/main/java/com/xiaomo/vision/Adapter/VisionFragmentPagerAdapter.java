package com.xiaomo.vision.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class VisionFragmentPagerAdapter extends FragmentPagerAdapter {
    public VisionFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
