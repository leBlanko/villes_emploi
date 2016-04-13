package com.malfoy.leblanko.villes_emploi.Slider;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.malfoy.leblanko.villes_emploi.R;

public class MainSwipe extends AppCompatActivity {

    private PagerAdapter pagerAdapter;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_swipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = (ViewPager) findViewById(R.id.viewPager1);
        pagerAdapter = new SwipeAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }
}
