package com.objectedge.store.ui.views;


import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.objectedge.store.R;
import com.objectedge.store.data.model.order.AddPaymentGroupModel;
import com.objectedge.store.ui.adapters.SimpleSpinnerAdapter;
import com.objectedge.store.ui.dialogs.DialogFactory;
import com.objectedge.store.ui.dialogs.WhatIsCCVDialogFragment;
import com.objectedge.store.utils.HelpUtils;
import com.objectedge.store.utils.StringUtils;
import com.objectedge.store.utils.ViewUtils;

import java.util.List;


/**
 * Created by eloor_000 on 6/22/2015.
 */
public class CreditCardBlockView extends LinearLayout {

    private Spinner  cardTypeSpinner;
    private EditText cardNumberEt;
    private EditText cardFirstNameEt;
    private EditText cardLastNameEt;
    private Spinner  expirationMonthSpinner;
    private Spinner  expirationYearSpinner;
    private EditText ccvCodeEt;
    private Button   whatIsItBtn;


    public CreditCardBlockView(Context context) {
        super(context);
        initView();
    }


    public CreditCardBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_credit_card_block, this);
        cardTypeSpinner = (Spinner) findViewById(R.id.payment_block_card_type);
        setCardSpinner();
        cardNumberEt = (EditText) findViewById(R.id.payment_block_credit_card_number);
        cardNumberEt.setOnTouchListener(onNormalTouch);
        cardNumberEt.setOnFocusChangeListener(focusChangeListener);
        cardNumberEt.addTextChangedListener(new CreditCardNumberFormattingTextWatcher());
        cardFirstNameEt = (EditText) findViewById(R.id.payment_block_card_first_name);
        cardFirstNameEt.setOnTouchListener(onNormalTouch);
        cardFirstNameEt.setOnFocusChangeListener(focusChangeListener);
        cardLastNameEt = (EditText) findViewById(R.id.payment_block_card_last_name);
        cardLastNameEt.setOnTouchListener(onNormalTouch);
        cardLastNameEt.setOnFocusChangeListener(focusChangeListener);
        expirationMonthSpinner = (Spinner) findViewById(R.id.payment_block_expiration_month);
        setExpirationMonthSpinner();
        expirationYearSpinner = (Spinner) findViewById(R.id.payment_block_expiration_year);
        setExpirationYearSpinner();
        ccvCodeEt = (EditText) findViewById(R.id.payment_block_ccv_code);
        ccvCodeEt.setOnTouchListener(onNormalTouch);
        ccvCodeEt.setOnFocusChangeListener(focusChangeListener);
        //find and init whatIsItButtonClick
        whatIsItBtn = (Button) findViewById(R.id.payment_block_what_is_this);
        whatIsItBtn.setOnClickListener(whatIsItButtonClick);
        whatIsItBtn.setOnFocusChangeListener(focusChangeListener);
    }

    public static class CreditCardNumberFormattingTextWatcher implements TextWatcher {

        private boolean lock;


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }


        @Override
        public void afterTextChanged(Editable s) {
            if (lock || s.length() > 16) {
                return;
            }
            lock = true;
            for (int i = 4; i < s.length(); i += 5) {
                if (s.toString().charAt(i) != ' ') {
                    s.insert(i, " ");
                }
            }
            lock = false;
        }
    }


    public OnClickListener whatIsItButtonClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            WhatIsCCVDialogFragment whatIsCCVDialogFragment = WhatIsCCVDialogFragment.newInstance();
            DialogFactory.showDialog((AppCompatActivity) getContext(), whatIsCCVDialogFragment);
        }
    };


    public void setCardSpinner() {
        String[] cardsArray = getContext().getResources().getStringArray(R.array.cards_types);
        SimpleSpinnerAdapter monthAdapter = new SimpleSpinnerAdapter(getContext(), cardsArray);
        cardTypeSpinner.setAdapter(monthAdapter);
    }


    public void setCardSpinner(List<String> cardsList) {
        List<String> readableCardsList = StringUtils.toReadableCardNameList(cardsList);
        SimpleSpinnerAdapter cardsTypeAdapter = new SimpleSpinnerAdapter(getContext(), readableCardsList);
        cardTypeSpinner.setAdapter(cardsTypeAdapter);
    }


    public void setCardSpinnerPosition(String value) {
        SpinnerAdapter adapter = cardTypeSpinner.getAdapter();
        int position = -1;
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                position = i;
            }
        }
        if (position != -1) {
            cardTypeSpinner.setSelection(position);
        }
    }


    private void setExpirationMonthSpinner() {
        String[] monthArray = getContext().getResources().getStringArray(R.array.month_numbers);
        SimpleSpinnerAdapter monthAdapter = new SimpleSpinnerAdapter(getContext(), monthArray);
        expirationMonthSpinner.setAdapter(monthAdapter);
    }


    public void setExpirationMonthSpinnerPosition(String value) {
        SpinnerAdapter adapter = expirationMonthSpinner.getAdapter();
        int position = -1;
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                position = i;
            }
        }
        if (position != -1) {
            expirationMonthSpinner.setSelection(position);
        }
    }


    private void setExpirationYearSpinner() {
        List<String> yearsList = HelpUtils.genTenYearsList();
        SimpleSpinnerAdapter yearsAdapter = new SimpleSpinnerAdapter(getContext(), yearsList);
        expirationYearSpinner.setAdapter(yearsAdapter);
    }


    public void setExpirationYearSpinnerPosition(String value) {
        SpinnerAdapter adapter = expirationYearSpinner.getAdapter();
        int position = -1;
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                position = i;
            }
        }
        if (position != -1) {
            expirationYearSpinner.setSelection(position);
        }
    }


    public String getCardNumber() {
        return cardNumberEt.getText().toString().replaceAll("\\s+","");
    }


    public String getFirstName() {
        return cardFirstNameEt.getText().toString();
    }


    public String getLastName() {
        return cardLastNameEt.getText().toString();
    }


    public String getCCVCode() {
        return ccvCodeEt.getText().toString();
    }


    public String getCardType() {
        return (String) cardTypeSpinner.getSelectedItem();
    }


    public String getExpirationMonth() {
        return (String) expirationMonthSpinner.getSelectedItem();
    }


    public String getExpirationYear() {
        return (String) expirationYearSpinner.getSelectedItem();
    }


    public boolean areValidFields() {
        boolean isValid = true;
        if (getCardNumber().isEmpty()) {
            isValid = false;
            ViewUtils.setErrorBackground(getContext(), cardNumberEt);
        }
        if (getFirstName().isEmpty()) {
            isValid = false;
            ViewUtils.setErrorBackground(getContext(), cardFirstNameEt);
        }
        if (getLastName().isEmpty()) {
            isValid = false;
            ViewUtils.setErrorBackground(getContext(), cardLastNameEt);
        }
        if (getCCVCode().isEmpty()) {
            isValid = false;
            ViewUtils.setErrorBackground(getContext(), ccvCodeEt);
        }
        return isValid;
    }


    public AddPaymentGroupModel toAddPaymentModel(){
        AddPaymentGroupModel addPaymentGroupModel = new AddPaymentGroupModel();
        addPaymentGroupModel.setExpirationDate(getExpirationMonth() + "/" + getExpirationYear());
        addPaymentGroupModel.setPaymentMethod("creditCard");
        addPaymentGroupModel.setPrimaryAccountNumber(getCardNumber());
        return addPaymentGroupModel;
    }

    private OnTouchListener onNormalTouch = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ViewUtils.setNormalBackground(getContext(), (EditText) v);
            return false;
        }
    };

    private OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                ViewUtils.setNormalBackground(getContext(), (EditText) v);
            }
        }
    };

    /*
    public void populateFields(PaymentProfileModel paymentProfileModel) {
        StringUtils.asterixedCreditCard(paymentProfileModel.getCard_number());
        cardFirstNameEt.setText(paymentProfileModel.getBilling_address().getFirst_name());
        cardLastNameEt.setText(paymentProfileModel.getBilling_address().getLast_name());
        createNickNameEt.setText(paymentProfileModel.getCredit_card_nickname());
        setExpirationMonthSpinnerPosition(String.valueOf(paymentProfileModel.getExpiry_month()));
        setExpirationYearSpinnerPosition(String.valueOf(paymentProfileModel.getExpiry_year()));
    }
    */

}
