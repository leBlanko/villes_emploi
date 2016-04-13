package com.malfoy.leblanko.villes_emploi.Slider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malfoy.leblanko.villes_emploi.R;

/**
 * Created by leBlanko on 13/04/16.
 */
public class PageFragment extends Fragment {
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstance)
    {
        if(container == null)
        {
            return null;
        }
        View view = inflater.inflate(R.layout.page_fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textViewC1);
        Bundle bundle = getArguments();
        String countS = Integer.toString(bundle.getInt("count"));
        textView.setText(countS);
        return view;
    }
}
