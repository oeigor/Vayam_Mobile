package com.objectedge.store.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.objectedge.store.R;
import com.objectedge.store.data.model.order.AddPaymentGroupModel;

import java.util.List;


/**
 * Created by eloor_000 on 5/26/2015.
 */
public class PaymentBlockView extends LinearLayout {

    public static final int CREDIT_CARD_MODE = 0;
    public static final int PHONE_MODE       = 1;
    public static final int PAYPAL_MODE      = 2;

    private int mode;

    private RadioGroup          radioGroup;
    private RadioButton         creditCardRb;
    private RadioButton         paypalRb;
    private RadioButton         phoneOrderRb;
    private CreditCardBlockView creditCardBlockView;
    private LinearLayout        paypalLayout;
    private ImageView           paypalButton;
    private LinearLayout        phoneOrderLayout;
    private Button              phoneButton;

    private OnTabsClickListener onTabsClickListener;


    public PaymentBlockView(Context context) {
        super(context);
        initView();
    }


    public PaymentBlockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_payment_block, this);
        radioGroup = (RadioGroup) findViewById(R.id.payment_block_radiogroup);
        radioGroup.setVisibility(GONE);
        radioGroup.setOnCheckedChangeListener(onRadioGroupClick);
        creditCardRb = (RadioButton) findViewById(R.id.payment_block_credit_card_rb);
        paypalRb = (RadioButton) findViewById(R.id.payment_block_paypal_rb);
        phoneOrderRb = (RadioButton) findViewById(R.id.payment_block_phone_order_rb);
        //find and init CreditCardLayout
        creditCardBlockView = (CreditCardBlockView) findViewById(R.id.payment_block_credit_card);
        //find and init paypal layout
        paypalLayout = (LinearLayout) findViewById(R.id.payment_block_paypal_layout);
        paypalButton = (ImageView) findViewById(R.id.payment_block_paypal_checkout_button);
        //paypalButton.setOnClickListener(onPayPalClick);
        //find and init phone order layout
        phoneOrderLayout = (LinearLayout) findViewById(R.id.payment_block_phone_order_layout);
        phoneButton = (Button) findViewById(R.id.payment_block_phone_button);
        //phoneButton.setOnClickListener(onPhoneClick);
    }


    private RadioGroup.OnCheckedChangeListener onRadioGroupClick = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
            case R.id.payment_block_credit_card_rb:
                mode = CREDIT_CARD_MODE;
                paypalLayout.setVisibility(GONE);
                phoneOrderLayout.setVisibility(GONE);
                creditCardBlockView.setVisibility(VISIBLE);
                if (onTabsClickListener != null) {
                    onTabsClickListener.onCreditCardTabClick();
                }
                break;
            case R.id.payment_block_paypal_rb:
                mode = PAYPAL_MODE;
                phoneOrderLayout.setVisibility(GONE);
                creditCardBlockView.setVisibility(GONE);
                paypalLayout.setVisibility(VISIBLE);
                if (onTabsClickListener != null) {
                    onTabsClickListener.onPayPalTabClick();
                }
                break;
            case R.id.payment_block_phone_order_rb:
                mode = PHONE_MODE;
                paypalLayout.setVisibility(GONE);
                creditCardBlockView.setVisibility(GONE);
                phoneOrderLayout.setVisibility(VISIBLE);
                if (onTabsClickListener != null) {
                    onTabsClickListener.onPhoneOrderTabClick();
                }
                break;
            }
        }
    };


    public void setCreditCardRbInvisible() {
        creditCardRb.setVisibility(GONE);
        paypalRb.setChecked(true);
    }


    public void setOnTabsClickListener(OnTabsClickListener onTabsClickListener) {
        this.onTabsClickListener = onTabsClickListener;
    }


    public void setCardSpinner(List<String> cardsList) {
        creditCardBlockView.setCardSpinner(cardsList);
    }


    public String getCCV() {
        return creditCardBlockView.getCCVCode();
    }


    public interface OnTabsClickListener {

        void onCreditCardTabClick();

        void onPayPalTabClick();

        void onPhoneOrderTabClick();
    }


    public int getCurrentTabMode() {
        return mode;
    }


    public boolean areValidFields() {
        //if (mode == PHONE_MODE) {
        //return true;
        //}
        //if (mode == PAYPAL_MODE) {
        //    //TODO PAYPAL WILL BE AVAILABLE SOON.
        //    return false;
        //}
        return creditCardBlockView.areValidFields();
    }

    public AddPaymentGroupModel toAddPaymentModel() {
        return creditCardBlockView.toAddPaymentModel();
    }

    /*
    private OnClickListener onPhoneClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(Constants.SURFSTITCH_PHONE_NUMBER));
            //getContext().startActivity(callIntent);
        }
    };

    private OnClickListener onPayPalClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getContext(),"This functionality will be added soon",Toast.LENGTH_SHORT).show();
        }
    };
    */


    public void setOnPayPalButtonClickListener(OnClickListener onPayPalClickListener) {
        paypalButton.setOnClickListener(onPayPalClickListener);
    }


    public void setOnPhoneButtonClickListener(OnClickListener onPhoneButtonClickListener) {
        phoneButton.setOnClickListener(onPhoneButtonClickListener);
    }

}
