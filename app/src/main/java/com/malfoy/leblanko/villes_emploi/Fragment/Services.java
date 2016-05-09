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
import android.widget.ImageView;
import android.widget.ScrollView;

import com.malfoy.leblanko.villes_emploi.Activity.Coach;
import com.malfoy.leblanko.villes_emploi.Activity.MainMenu;
import com.malfoy.leblanko.villes_emploi.R;

/**
 * Created by leBlanko on 09/05/2016.
 */
public class Services extends Fragment {

    private Button coachSubmit;

    public Services() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_services,container,false);

        coachSubmit = (Button) view.findViewById(R.id.coachSubmit);

        coachSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCoach = new Intent(view.getContext(), Coach.class);
                startActivity(goToCoach);
            }
        });



        return view;
    }

}
