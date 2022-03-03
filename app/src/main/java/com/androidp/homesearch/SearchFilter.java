package com.androidp.homesearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SearchFilter extends Fragment {


    Button fragmentBackbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_filter, container, false);

        fragmentBackbtn = v.findViewById(R.id.fragmentBackbtn);
        fragmentBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), HomeScreen.class);
                startActivity(i);
            }
        });

        return v;
    }



}