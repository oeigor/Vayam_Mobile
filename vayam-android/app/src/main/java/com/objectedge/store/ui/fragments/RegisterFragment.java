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
import android.widget.Toast;

import com.objectedge.store.R;
import com.objectedge.store.StoreApplication;
import com.objectedge.store.data.manager.ModelObserver;
import com.objectedge.store.data.manager.UsersManager;
import com.objectedge.store.data.model.user.AddUserModel;
import com.objectedge.store.ui.activities.MainActivity;
import com.objectedge.store.utils.RegisterValidator;

/**
 * Created by "Michael Katkov" on 10/5/2015.
 */
public class RegisterFragment extends Fragment implements ModelObserver{

    private EditText nameField;
    private EditText surnameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button registerBtn;

    private UsersManager usersManager;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        usersManager = ((StoreApplication)getActivity().getApplication()).getUsersManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        nameField = (EditText)view.findViewById(R.id.name);
        surnameField = (EditText)view.findViewById(R.id.surname);
        emailField = (EditText)view.findViewById(R.id.email);
        passwordField = (EditText)view.findViewById(R.id.password);
        confirmPasswordField = (EditText)view.findViewById(R.id.confirm_password);
        confirmPasswordField.setOnEditorActionListener(actionListener);
        registerBtn = (Button)view.findViewById(R.id.register);
        registerBtn.setOnClickListener(onRegisterClick);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        usersManager.addObserver(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        usersManager.removeObserver(this);
    }

    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onRegisterClick.onClick(registerBtn);
                handled = true;
            }
            return handled;
        }
    };

    private View.OnClickListener onRegisterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddUserModel addUserModel = new AddUserModel();
            addUserModel.addName(new AddUserModel.Name(nameField.getText().toString(),"givenName"));
            addUserModel.addName(new AddUserModel.Name(surnameField.getText().toString(), "familyName"));
            //check email
            if (!RegisterValidator.validateEmail(emailField.getText().toString())) {
                Toast.makeText(getContext(),"Invalid email", Toast.LENGTH_SHORT).show();
                return;
            } else {
                addUserModel.setEmail(emailField.getText().toString());
            }
            //check password
            if (!RegisterValidator.validatePassword(passwordField.getText().toString())) {
                Toast.makeText(getContext(),"Your password should contain small letters, upper letters and numbers.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                addUserModel.setPassword(passwordField.getText().toString());
            }
            //check passwords match
            if(!passwordField.getText().toString().equals(confirmPasswordField.getText().toString())){
                Toast.makeText(getContext(),"Your confirmation should match to your password", Toast.LENGTH_SHORT).show();
                return;
            }
            addUserModel.setUserId(emailField.getText().toString());
            usersManager.addUser(addUserModel);
        }
    };

    @Override
    public void modelChanged(String message, String error) {
        if(error != null){
            Toast.makeText(getContext(), getString(R.string.server_error), Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.start(getContext(),true);
            getActivity().finish();
        }
    }
}
