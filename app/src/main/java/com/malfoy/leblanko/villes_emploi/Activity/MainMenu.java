package com.malfoy.leblanko.villes_emploi.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.malfoy.leblanko.villes_emploi.Fragment.Offres;
import com.malfoy.leblanko.villes_emploi.Fragment.Profil;
import com.malfoy.leblanko.villes_emploi.Fragment.Services;
import com.malfoy.leblanko.villes_emploi.Fragment.Tribu;
import com.malfoy.leblanko.villes_emploi.R;
import com.malfoy.leblanko.villes_emploi.ViewPagerAdapter;

public class MainMenu extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new Profil(), "Profil");
        viewPagerAdapter.addFragments(new Offres(), "Offres");
        viewPagerAdapter.addFragments(new Tribu(), "Tribu");
        viewPagerAdapter.addFragments(new Services(), "Services");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
