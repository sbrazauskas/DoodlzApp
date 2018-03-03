package com.example.sarahbrazauskas.deiteldoodlz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Created by SBrazauskas on 2/26/2018.
 * Allows user to set background color to draw on.
 * Enhancement for USCD Android I assignment.
 */

public class BackGroundColorDialogFragment extends DialogFragment {
    private SeekBar alphaBgSeekBar;
    private SeekBar redBgSeekBar;
    private SeekBar greenBgSeekBar;
    private SeekBar blueBgSeekBar;
    private View backgroundColorView;
    private int bgColor;

    // create an AlertDialog and return it
    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        // create dialog
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        View backGroundColorDialogView = getActivity().getLayoutInflater().inflate(
                R.layout.fragment_background_color, null);
        builder.setView(backGroundColorDialogView); // add GUI to dialog

        // set the AlertDialog's message
        builder.setTitle(R.string.title_background_color_dialog);

        // set the background color SeekBars
        alphaBgSeekBar = (SeekBar) backGroundColorDialogView.findViewById(
                R.id.alphaBgSeekBar);
        redBgSeekBar = (SeekBar) backGroundColorDialogView.findViewById(
                R.id.redBgSeekBar);
        greenBgSeekBar = (SeekBar) backGroundColorDialogView.findViewById(
                R.id.greenBgSeekBar);
        blueBgSeekBar = (SeekBar) backGroundColorDialogView.findViewById(
                R.id.blueBgSeekBar);
        backgroundColorView = backGroundColorDialogView.findViewById(R.id.backgroundColorView);

        // register SeekBar event listeners
        alphaBgSeekBar.setOnSeekBarChangeListener(bgColorChangedListener);
        redBgSeekBar.setOnSeekBarChangeListener(bgColorChangedListener);
        greenBgSeekBar.setOnSeekBarChangeListener(bgColorChangedListener);
        blueBgSeekBar.setOnSeekBarChangeListener(bgColorChangedListener);

        // use current background color to set SeekBar values
        final DoodleView doodleView = getDoodleFragment().getDoodleView();
        bgColor = doodleView.getBackgroundColor();
        alphaBgSeekBar.setProgress(Color.alpha(bgColor));
        redBgSeekBar.setProgress(Color.red(bgColor));
        greenBgSeekBar.setProgress(Color.green(bgColor));
        blueBgSeekBar.setProgress(Color.blue(bgColor));

        // add Set Color Button
        builder.setPositiveButton(R.string.button_set_color,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setBackgroundColor(bgColor);
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

    // OnSeekBarChangeListener for the SeekBars in the background color dialog
    private final OnSeekBarChangeListener bgColorChangedListener =
            new OnSeekBarChangeListener() {
                // display the updated color
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {

                    if (fromUser) // user, not program, changed SeekBar progress
                        bgColor = Color.argb(alphaBgSeekBar.getProgress(),
                                redBgSeekBar.getProgress(), greenBgSeekBar.getProgress(),
                                blueBgSeekBar.getProgress());
                    backgroundColorView.setBackgroundColor(bgColor);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {} // required

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {} // required
            };
}
