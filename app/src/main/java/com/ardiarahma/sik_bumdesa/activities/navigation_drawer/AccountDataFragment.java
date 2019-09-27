package com.ardiarahma.sik_bumdesa.activities.navigation_drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardiarahma.sik_bumdesa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDataFragment extends Fragment {


    public AccountDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_data, container, false);
    }

}
