package com.malfoy.leblanko.villes_emploi.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.malfoy.leblanko.villes_emploi.R;

public class Coach extends AppCompatActivity {

    private Button submitAbo;
    private ScrollView scrollView;
    private ImageView toBottom;

    public Coach() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        submitAbo = (Button) findViewById(R.id.submit_coach);
        scrollView = (ScrollView) findViewById(R.id.scrollView1);
        toBottom = (ImageView) findViewById(R.id.back_page);

        scrollView.scrollTo(0, 0);

        submitAbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.villes-emploi.com/abonnement";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        toBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CountDownTimer(2000, 10) {

                    public void onTick(long millisUntilFinished) {

                        scrollView.scrollTo(scrollView.getScrollY(), (int) (1500 - millisUntilFinished));
                    }

                    public void onFinish() {
                    }

                }.start();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(-1); //On previent qu'on a fait un back
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
