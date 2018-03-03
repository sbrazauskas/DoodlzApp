package com.example.sarahbrazauskas.deiteldoodlz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by SBrazauskas on 3/1/2018.
 * Allows user to select from 3 background images in alert dialog.
 * Enhancement for USCD Android I assignment.
 */

public class BackGroundImageDialogFragment extends DialogFragment {

    // create an AlertDialog and return it
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        // create dialog
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        final View backGroundImageDialogView = getActivity().getLayoutInflater().inflate(
                R.layout.fragment_background_image, null);
        builder.setView(backGroundImageDialogView); // add GUI to dialog

        // set the AlertDialog's message
        builder.setTitle(R.string.title_background_image_dialog);

        // get the background imageButtons
        ImageButton faceImageButton = backGroundImageDialogView.findViewById(
                R.id.faceImageButton);
        ImageButton grassImageButton = backGroundImageDialogView.findViewById(
                R.id.grassImageButton);
        ImageButton oceanImageButton = backGroundImageDialogView.findViewById(
                R.id.oceanImageButton);

        // register OnClick Listeners for image buttons and set background image
        faceImageButton.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDoodleFragment().getDoodleView().setBackgroundResource(R.drawable.face);
                    }
                })
        );

        grassImageButton.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDoodleFragment().getDoodleView().setBackgroundResource(R.drawable.grass);
                    }
                })
        );

        oceanImageButton.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getDoodleFragment().getDoodleView().setBackgroundResource(R.drawable.ocean);
                    }
                })
        );

        // add Neutral Button to dismiss alert dialog
        builder.setNeutralButton(R.string.button_set_image,
               new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int image ) {
                    dialog.cancel();
                  }
                }
        );

        return builder.create(); // return dialog
    }

    // gets a reference to the MainActivityFragment
    private MainActivityFragment getDoodleFragment() {
        return (MainActivityFragment) getFragmentManager().findFragmentById(
                R.id.doodleFragment);
    }

    // tell MainActivityFragment that dialog is now displayed
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivityFragment fragment = getDoodleFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(true);
    }

    // tell MainActivityFragment that dialog is no longer displayed
    @Override
    public void onDetach() {
        super.onDetach();
        MainActivityFragment fragment = getDoodleFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(false);
    }
}
