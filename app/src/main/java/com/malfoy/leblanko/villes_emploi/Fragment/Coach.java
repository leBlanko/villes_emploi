package com.malfoy.leblanko.villes_emploi.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.malfoy.leblanko.villes_emploi.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by leBlanko on 21/04/2016.
 */
public class Coach extends Fragment {


    private Button submitAbo;
    private ScrollView scrollView;
    private ImageView toBottom;

    public Coach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coach,container,false);

        submitAbo = (Button) view.findViewById(R.id.submit_coach);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView1);
        toBottom = (ImageView) view.findViewById(R.id.back_page);

        scrollView.scrollTo(0,0);

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

                        scrollView.scrollTo(scrollView.getScrollY(),(int) (1500 - millisUntilFinished));
                    }

                    public void onFinish() {
                    }

                }.start();

            }
        });

        return view;
    }

}
