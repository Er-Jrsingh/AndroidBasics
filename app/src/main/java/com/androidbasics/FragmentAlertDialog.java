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

    private final String[] mColors = {"Red", "Green", "Blue", "Yellow"};

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Please Pick Color");
//        alertBuilder.setMessage("Are You Sure??");

        alertBuilder.setMultiChoiceItems(mColors, new boolean[]{true, false, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getActivity(), "Color Added " + mColors[which], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Color Removed " + mColors[which], Toast.LENGTH_SHORT).show();
                }
            }
        });

//        alertBuilder.setSingleChoiceItems(mColors, 2, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), "You Selected " + mColors[which], Toast.LENGTH_SHORT).show();
//            }
//        });

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

    //    Called When Clicked at Outside the Model Style Window(Pop Up Window) Not in Cancel(setNeutralButton) Button, So setCancelable Must Not Be false

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        Toast.makeText(getActivity(), "onCancel Method Called", Toast.LENGTH_SHORT).show();
    }

    //    Always Called Mean Every Button Pressed (yes , no , cancel , outside pop up)
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Toast.makeText(getActivity(), "onDismiss Method Called", Toast.LENGTH_SHORT).show();
    }
}
