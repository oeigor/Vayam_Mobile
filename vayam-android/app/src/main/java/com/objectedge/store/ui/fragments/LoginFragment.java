package com.objectedge.store.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.objectedge.store.R;

/**
 * Created by "Michael Katkov" on 10/5/2015.
 */
public class LoginFragment extends Fragment {


    private EditText emailField;
    private EditText passwordField;
    private Button loginBtn;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailField = (EditText)view.findViewById(R.id.email);
        passwordField = (EditText)view.findViewById(R.id.password);
        loginBtn = (Button)view.findViewById(R.id.login);
        loginBtn.setOnClickListener(onLoginClick);
        return view;
    }

    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onLoginClick.onClick(loginBtn);
                handled = true;
            }
            return handled;
        }
    };

    private View.OnClickListener onLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
