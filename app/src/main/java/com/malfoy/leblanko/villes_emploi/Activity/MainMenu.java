package com.malfoy.leblanko.villes_emploi.Activity;

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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.background_color1));



        drawerLayout.bringToFront();
        drawerLayout.requestLayout();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Ceci va faire apparaitre le menu quand on slide sur le bord gauche de l ecran ou quand on clique sur le menu hamburger
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        //On desactive l icon par default
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        toolbar.setNavigationIcon(R.drawable.ic_profil);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        profil = new Profil();
        offres = new Offres();
        tribu = new Tribu();
        services = new Services();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //On ajoute les differents fragments
        viewPagerAdapter.addFragments(profil, ""); //Mes talents
        viewPagerAdapter.addFragments(offres, ""); //Mes offres
        viewPagerAdapter.addFragments(tribu, ""); // Ma ville
        viewPagerAdapter.addFragments(services, ""); //Mes services

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

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
                    case R.id.show_cvmap:
                        Intent showCVMap = new Intent(MainMenu.this, ShowCVMap.class);
                        startActivity(showCVMap);
                        break;
                    case R.id.update_cvmap:
                        Intent updateCVMap = new Intent(MainMenu.this, UpdateCVMap.class);
                        startActivity(updateCVMap);
                        break;
                }
                return true;
            }
        });

        TextView tv= (TextView) findViewById(R.id.search_settings);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(MainMenu.this, SearchSettings.class);
                startActivity(goToSettings);
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

    public void perform_action(View v)
    {

    }

    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Mes talents");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mestalents, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Mes offres");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_mesoffres, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Ma carte");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_maville, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Mes services");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_messervices, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }


}
