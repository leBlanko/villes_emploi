package com.malfoy.leblanko.villes_emploi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malfoy.leblanko.villes_emploi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tribu extends Fragment {


    public Tribu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tribu, container, false);
    }

}
