package com.example.zhao.smart_doors.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhao on 2016/4/18.
 */
public class PageAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;
    public PageAdapter(FragmentManager fm,List<Fragment> fragments){
        super(fm);
        fragmentList=fragments;
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return fragmentList.size();
    }

}
