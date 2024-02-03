package com.example.devilapplication.Fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devilapplication.R;

public class upperFragment extends Fragment {

    public upperFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.upper_fragment, container, false);
        TextView txtUpperFrag = view.findViewById(R.id.txtUpperFrag);
        return view;
    }
}
