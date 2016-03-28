package com.objectedge.store.ui.dialogs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.objectedge.store.R;


/**
 * Created by eloor_000 on 5/28/2015.
 */
public class WhatIsCCVDialogFragment extends DialogFragment {

    public static final String TAG = "WhatIsCCVDialogFragment";


    public static WhatIsCCVDialogFragment newInstance() {
        return new WhatIsCCVDialogFragment();
    }


    public WhatIsCCVDialogFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(DialogFragment.STYLE_NO_TITLE, getTheme());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_what_is_ccv_layout, container, false);
        view.findViewById(R.id.what_is_this_cross).setOnClickListener(onCrossClick);
        return view;
    }

    private View.OnClickListener onCrossClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

}
