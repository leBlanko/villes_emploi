package com.malfoy.leblanko.villes_emploi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by leBlanko on 13/04/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> tabTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addFragments(Fragment f,String s)
    {
        this.fragments.add(f);
        this.tabTitles.add(s);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles.get(position);
    }
}
