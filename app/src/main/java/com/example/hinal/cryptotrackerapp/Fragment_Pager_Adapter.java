package com.example.hinal.cryptotrackerapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
public class Fragment_Pager_Adapter extends FragmentPagerAdapter
{

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragment_titles_list = new ArrayList<>();

    public Fragment_Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragment_titles_list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragment_titles_list.get(position);
    }

    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        fragment_titles_list.add(title);
    }

}
