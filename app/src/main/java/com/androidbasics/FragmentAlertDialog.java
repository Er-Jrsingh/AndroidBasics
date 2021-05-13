package com.androidbasics;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FragmentAlertDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Confirmation Box");
        alertBuilder.setMessage("Are You Sure??");

        // Add User Actions On AlertDialog
        alertBuilder.setPositiveButton("Yup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Ohh Yesssss!!...", Toast.LENGTH_LONG).show();
            }
        });
        alertBuilder.setNegativeButton("Nooop", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "So Sorry !!...", Toast.LENGTH_LONG).show();
            }
        });
        alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Thik Hai Bhai", Toast.LENGTH_LONG).show();
                alertBuilder.create().dismiss();
            }
        });

        return alertBuilder.create();
    }
}
