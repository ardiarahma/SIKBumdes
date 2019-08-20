package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ardiarahma.sik_bumdesa.R;
import com.ardiarahma.sik_bumdesa.activities.MainActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.BukuBesarActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.EkuitasActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.JurnalActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.LabaRugiActivity;
import com.ardiarahma.sik_bumdesa.activities.dashboard.NeracaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RelativeLayout v1, v2, v3, v4, v5;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        v1 = v.findViewById(R.id.menu1);
        v2 = v.findViewById(R.id.menu2);
        v3 = v.findViewById(R.id.menu3);
        v4 = v.findViewById(R.id.menu4);
        v5 = v.findViewById(R.id.menu5);

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JurnalActivity.class);
                startActivity(intent);
            }
        });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BukuBesarActivity.class);
                startActivity(intent);
            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NeracaActivity.class);
                startActivity(intent);
            }
        });

        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LabaRugiActivity.class);
                startActivity(intent);
            }
        });

        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EkuitasActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
