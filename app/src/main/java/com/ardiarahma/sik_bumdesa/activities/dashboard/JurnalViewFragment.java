package com.ardiarahma.sik_bumdesa.activities.dashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ardiarahma.sik_bumdesa.R;

public class JurnalViewFragment extends Fragment {

    private TextView textView;

    public JurnalViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_jurnal_view, container, false);
        textView = v.findViewById(R.id.view_date);
        String message = getArguments().getString("message");
        textView.setText(message);

        return v;
    }

}
