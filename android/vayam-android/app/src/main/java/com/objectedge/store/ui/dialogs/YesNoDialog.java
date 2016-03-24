package com.objectedge.store.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.objectedge.store.R;

/**
 * Created by eloor_000 on 5/21/2015.
 */
public class YesNoDialog extends DialogFragment {

    public static final String TAG = "YesNoDialog";

    private static final String MESSAGE = "message";

    private String message;

    private OnDialogButtonsClickListener onDialogButtonsClickListener;

    public static YesNoDialog newInstance(String message){
        YesNoDialog fragment = new YesNoDialog();
        Bundle args = new Bundle();
        args.putString(MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    public YesNoDialog(){

    }

    public void setOnDialogButtonsClickListener(OnDialogButtonsClickListener onDialogButtonsClickListener){
        this.onDialogButtonsClickListener = onDialogButtonsClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(MESSAGE);
        }
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, getTheme());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(onDialogButtonsClickListener != null){
                            onDialogButtonsClickListener.onYes();
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(onDialogButtonsClickListener != null){
                            onDialogButtonsClickListener.onNo();
                        }
                        dialog.dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        AlertDialog alertDialog = builder.create();
        //alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        return alertDialog;
    }

    public interface OnDialogButtonsClickListener{
        public void onYes();
        public void onNo();
    }
}
