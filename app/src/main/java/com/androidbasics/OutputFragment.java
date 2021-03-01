package com.androidbasics;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class OutputFragment extends Fragment {
    private TextView mTxtOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_output, container, false);
        mTxtOutput = view.findViewById(R.id.tv_output);
        return view;
    }

    public void displayPerson(PersonBean personBean) {
        mTxtOutput.setText(personBean.toString());
    }

}
