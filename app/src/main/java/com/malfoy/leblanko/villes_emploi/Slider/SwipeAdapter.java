package com.malfoy.leblanko.villes_emploi.Slider;

/**
 * Created by leBlanko on 13/04/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmentP = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("count",position+1);
        fragmentP.setArguments(bundle);
        return fragmentP;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
