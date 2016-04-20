package com.malfoy.leblanko.villes_emploi.Activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.malfoy.leblanko.villes_emploi.ExpandAdapter;
import com.malfoy.leblanko.villes_emploi.Fragment.Offres;
import com.malfoy.leblanko.villes_emploi.Fragment.Profil;
import com.malfoy.leblanko.villes_emploi.Fragment.Services;
import com.malfoy.leblanko.villes_emploi.Fragment.Tribu;
import com.malfoy.leblanko.villes_emploi.R;
import com.malfoy.leblanko.villes_emploi.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentTransaction fragmentTransaction;
    private NavigationView navigationView;
    private Profil profil;
    private Offres offres;
    private Tribu tribu;
    private Services services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //Recuperations des données de la vue
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerLayout.bringToFront();
        drawerLayout.requestLayout();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Ceci va faire apparaitre le menu quand on slide sur le bord gauche de l ecran ou quand on clique sur le menu hamburger
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        profil = new Profil();
        offres = new Offres();
        tribu = new Tribu();
        services = new Services();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //On ajoute les differents fragments
        viewPagerAdapter.addFragments(profil, "Profil");
        viewPagerAdapter.addFragments(offres, "Offres");
        viewPagerAdapter.addFragments(tribu, "Tribu");
        viewPagerAdapter.addFragments(services, "Services");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //On recupere le navigation view
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        navigationView.requestLayout();
        //On lui creer son listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeDrawer:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.offresDrawer:
                        Intent mesOffresIntent = new Intent(MainMenu.this, MesOffres.class);
                        startActivity(mesOffresIntent);
                        break;
                    case R.id.infosDrawer:
                        Intent updateDataIntent = new Intent(MainMenu.this, UpdateData.class);
                        startActivity(updateDataIntent);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode != -1) //BACK TO
        {
            drawerLayout.closeDrawers();
        }
    }



}
