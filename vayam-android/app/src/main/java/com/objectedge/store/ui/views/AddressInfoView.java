package com.objectedge.store.ui.views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.objectedge.store.R;
import com.objectedge.store.data.model.order.AddPaymentGroupModel;
import com.objectedge.store.data.model.order.AddShippingToOrderModel;
import com.objectedge.store.ui.adapters.SimpleSpinnerAdapter;
import com.objectedge.store.utils.HelpUtils;
import com.objectedge.store.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by eloor_000 on 5/25/2015.
 */
public class AddressInfoView extends LinearLayout {

    public static final String TAG = "AddressInfoView";

    //private TextView emailLabel;
    //private TextView thisIsLabel;
    //private EditText emailAddress;
    private TextView titleTv;
    private Spinner  titleSpinner;
    //private TextView firstNameTv;
    private EditText firstNameEt;
    //private TextView lastNameTv;
    private EditText lastNameEt;
    private Spinner  countrySpinner;
    private EditText addressLine1Et;
    private EditText addressLine2Et;
    private EditText cityTownEt;
    private EditText postalCodeEt;
    private EditText stateEt;
    private EditText telephoneEt;


    public AddressInfoView(Context context) {
        super(context);
        initView();
    }


    public AddressInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_address_info_layout, this);
        //emailLabel = (TextView) findViewById(R.id.address_label_email);
        //thisIsLabel = (TextView) findViewById(R.id.address_label_this_is);
        //emailAddress = (EditText) findViewById(R.id.address_email);
        //emailAddress.setOnTouchListener(normalClick);
        titleTv = (TextView) findViewById(R.id.address_title_text);
        titleSpinner = (Spinner) findViewById(R.id.address_title);
        setTitleSpinner();
        //titleSpinner.setOnTouchListener(normalSpinnerClick);
        //firstNameTv = (TextView) findViewById(R.id.address_first_name_text);
        firstNameEt = (EditText) findViewById(R.id.address_first_name);
        firstNameEt.setOnTouchListener(normalClick);
        firstNameEt.setOnFocusChangeListener(focusChangeListener);
        //lastNameTv = (TextView) findViewById(R.id.address_last_name_text);
        lastNameEt = (EditText) findViewById(R.id.address_last_name);
        lastNameEt.setOnTouchListener(normalClick);
        lastNameEt.setOnFocusChangeListener(focusChangeListener);
        countrySpinner = (Spinner) findViewById(R.id.address_country);
        setCountrySpinnerValue();
        addressLine1Et = (EditText) findViewById(R.id.address_line_1);
        addressLine1Et.setOnTouchListener(normalClick);
        addressLine1Et.setOnFocusChangeListener(focusChangeListener);
        addressLine2Et = (EditText) findViewById(R.id.address_line_2);
        addressLine2Et.setOnTouchListener(normalClick);
        addressLine2Et.setOnFocusChangeListener(focusChangeListener);
        cityTownEt = (EditText) findViewById(R.id.address_city_town);
        cityTownEt.setOnTouchListener(normalClick);
        cityTownEt.setOnFocusChangeListener(focusChangeListener);
        postalCodeEt = (EditText)findViewById(R.id.address_postal_code);
        postalCodeEt.setOnTouchListener(normalClick);
        postalCodeEt.setOnFocusChangeListener(focusChangeListener);
        stateEt = (EditText)findViewById(R.id.address_state);
        stateEt.setOnTouchListener(normalClick);
        stateEt.setOnFocusChangeListener(focusChangeListener);
        telephoneEt = (EditText)findViewById(R.id.address_telephone);
        telephoneEt.setOnTouchListener(normalClick);
        telephoneEt.setOnFocusChangeListener(focusChangeListener);
    }

    //public void setEmailAddressValue(String emailString){
    //    emailAddress.setText(emailString);
    //}

    public void setTitleSpinner() {
        String[] titleArray = getContext().getResources().getStringArray(R.array.titles_array);
        SimpleSpinnerAdapter titleAdapter = new SimpleSpinnerAdapter(getContext(), titleArray);
        titleSpinner.setAdapter(titleAdapter);
    }

    public void setTitleSpinnerValues(List<String> titles){
        if(titleSpinner.getAdapter() != null && titleSpinner.getAdapter().getCount() > 0){
            //not update, when titleSpinner has items
            return;
        }
        //make a copy of items to avoid problems
        List<String> newTitles = new ArrayList<>(titles);
        //add "Please select" item
        String currentTitle = getContext().getString(R.string.please_select);
        newTitles.add(currentTitle);
        SimpleSpinnerAdapter titlesAdapter = new SimpleSpinnerAdapter(getContext(),newTitles);
        titleSpinner.setAdapter(titlesAdapter);
        //set current value
        int position = -1;
        for(int i = 0; i < newTitles.size(); i++) {
            if(newTitles.get(i).equals(currentTitle)) {
                position = i;
            }
        }
        if(position != -1) {
            titleSpinner.setSelection(position);
        }
    }

    public void selectTitleSpinnerPosition(String value){
        SpinnerAdapter spinnerAdapter = titleSpinner.getAdapter();
        int position = -1;
        for(int i = 0; i < spinnerAdapter.getCount(); i++) {
            if((spinnerAdapter.getItem(i)).equals(value)) {
                position = i;
            }
        }
        if(position != -1) {
            titleSpinner.setSelection(position);
        }
    }

    public void setFirstNameValue(String firstName){
        firstNameEt.setText(firstName);
    }

    public void setLastNameValue(String lastName){
        lastNameEt.setText(lastName);
    }

    public void setCountrySpinnerValue(){
        List<String> countries = HelpUtils.getAllCountries();
        SimpleSpinnerAdapter countriesAdapter = new SimpleSpinnerAdapter(getContext(),countries);
        countrySpinner.setAdapter(countriesAdapter);
        //set current value
        String currentCity = getContext().getResources().getConfiguration().locale.getDisplayCountry();
        if(currentCity != null && !currentCity.equals(" ")){
            int position = -1;
            for(int i = 0; i < countries.size(); i++) {
                if(countries.get(i).equals(currentCity)) {
                    position = i;
                }
            }
            if(position != -1) {
                countrySpinner.setSelection(position);
            }
        }
    }

    public void setAddressLine1Value(String addressLine1){
        addressLine1Et.setText(addressLine1);
    }

    public void setAddressLine2Value(String addressLine2){
        addressLine2Et.setText(addressLine2);
    }

    public void setCityTownValue(String cityTown){
        cityTownEt.setText(cityTown);
    }

    public void setPostalCodeValue(String postalCode){
        postalCodeEt.setText(postalCode);
    }

    public void setStateValue(String state){
        stateEt.setText(state);
    }

    public void setTelephoneValue(String telephone){
        telephoneEt.setText(telephone);
    }

    //public String getEmail(){
    //    return emailAddress.getText().toString();
    //}

    public String getTitle(){
        return (String)titleSpinner.getSelectedItem();
    }

    public String getFirstName(){
        return firstNameEt.getText().toString();
    }

    public String getLastName(){
        return lastNameEt.getText().toString();
    }

    public String getCountry(){
        return (String)countrySpinner.getSelectedItem();
    }

    public String getAddressLine1(){
        return addressLine1Et.getText().toString();
    }

    public String getAddressLine2(){
        return addressLine2Et.getText().toString();
    }

    public String getCity(){
        return cityTownEt.getText().toString();
    }

    public String getPostalCode(){
        return postalCodeEt.getText().toString();
    }

    public String getState(){
        return stateEt.getText().toString();
    }

    public String getTelephone(){
        return telephoneEt.getText().toString();
    }

    //public void setEmailAddressInvisible(){
    //    emailAddress.setVisibility(GONE);
    //}

    //public void setCustomerInfoInvisible(){
    //    emailAddress.setVisibility(GONE);
    //    emailLabel.setVisibility(GONE);
    //    thisIsLabel.setVisibility(GONE);
    //}

    public void setTitlesAndNamesInvisible(){
        titleTv.setVisibility(GONE);
        titleSpinner.setVisibility(GONE);
        firstNameEt.setVisibility(GONE);
        //firstNameTv.setVisibility(GONE);
        lastNameEt.setVisibility(GONE);
        //lastNameTv.setVisibility(GONE);
    }

    public AddShippingToOrderModel constructAddShippingAddressModel(){
        AddShippingToOrderModel addShippingToOrderModel = new AddShippingToOrderModel();
        addShippingToOrderModel.setMethod("Ground");
        AddShippingToOrderModel.Address address = new AddShippingToOrderModel.Address();
        address.setAddress1(getAddressLine1());
        address.setAddress2(getAddressLine2());
        address.setCity(getCity());
        address.setCountry(getCountry());
        address.setFirstName(getFirstName());
        address.setLastName(getLastName());
        address.setPostalCode(getPostalCode());
        address.setState(getState());
        addShippingToOrderModel.setAddress(address);
        return addShippingToOrderModel;
    }

    public AddPaymentGroupModel addAddressToPayment(AddPaymentGroupModel addPaymentGroupModel){
        AddPaymentGroupModel.Address address = new AddPaymentGroupModel.Address();
        address.setAddress1(getAddressLine1());
        address.setAddress2(getAddressLine2());
        address.setCity(getCity());
        address.setCountry(getCountry());
        address.setFirstName(getFirstName());
        address.setLastName(getLastName());
        address.setPostalCode(getPostalCode());
        address.setState(getState());
        addPaymentGroupModel.setAddress(address);
        return addPaymentGroupModel;
    }
    /*
    public AddPhonePaymentToOrderModel.BillingAddress constructBillingAddressModel(){
        AddPhonePaymentToOrderModel.BillingAddress billingAddress = new AddPhonePaymentToOrderModel.BillingAddress();
        AddPhonePaymentToOrderModel.BillingAddress.Details details = billingAddress.getDetails();
        details.getTitle().setValue(getTitle());
        details.setFirst_name(getFirstName());
        details.setLast_name(getLastName());
        details.setCompanyName("");
        details.setAddress1(getAddressLine1());
        details.setAddress2(getAddressLine2());
        details.setCity(getCity());
        details.setPostalCode(getPostalCode());
        details.setCountry(getCountry());
        details.setState(getState());
        details.setPhone_number(getTelephone());
        details.setFax_number(getTelephone());
        details.getAddress_type().setValue("residential");
        return billingAddress;
    }
    */
    //public boolean isValidEmail(){
    //    boolean isValid = HelpUtils.validateEmail(getEmail());
    //   if(!isValid) {
    //        ViewUtils.setErrorBackground(getContext(), emailAddress);
    //    }
    //    return isValid;
    //}

    public boolean isValidInfo(){//check all fields
        boolean isValid = true;
        if(getFirstName().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), firstNameEt);
            isValid = false;
        }
        if(getLastName().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), lastNameEt);
            isValid = false;
        }
        if(getAddressLine1().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), addressLine1Et);
            isValid = false;
        }
        if(getCity().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), cityTownEt);
            isValid = false;
        }
        if(getPostalCode().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), postalCodeEt);
            isValid = false;
        }
        if(getState().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), stateEt);
            isValid = false;
        }
        if(getTelephone().isEmpty()){
            ViewUtils.setErrorBackground(getContext(), telephoneEt);
            isValid = false;
        }
        return isValid;
    }

    private OnTouchListener normalClick = new OnTouchListener() {

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

    private OnTouchListener normalSpinnerClick = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            setNormalTitleSpinner();
            return false;
        }
    };


    private void setErrorTitleSpinner() {
        SimpleSpinnerAdapter adapter = (SimpleSpinnerAdapter) titleSpinner.getAdapter();
        adapter.setErrorMarked(true);
    }


    private void setNormalTitleSpinner() {
        SimpleSpinnerAdapter adapter = (SimpleSpinnerAdapter) titleSpinner.getAdapter();
        adapter.setErrorMarked(false);
    }

    /*
    public void setModel(AddressModel model) {
        setTitleSpinnerValues(model.getTitle().getOptions());
        selectTitleSpinnerPosition(model.getTitle().getValue());
        setFirstNameValue(model.getFirst_name());
        setLastNameValue(model.getLast_name());
        setCountrySpinnerValue();
        setAddressLine1Value(model.getAddress1());
        setAddressLine2Value(model.getAddress2());
        setCityTownValue(model.getCity());
        setPostalCodeValue(model.getPostal_code());
        setStateValue(model.getState());
        setTelephoneValue(model.getPhone());
    }


    public AddAddressToProfileModel getAddressToProfileModel(String profileNickName) {
        AddAddressToProfileModel addAddressToProfileModel = new AddAddressToProfileModel();
        AddAddressToProfileModel.Address addressModel = new AddAddressToProfileModel.Address();
        addressModel.getTitle().setValue(getTitle());
        addressModel.setFirst_name(getFirstName());
        addressModel.setLast_name(getLastName());
        addressModel.setCountry(getCountry());
        addressModel.setAddress1(getAddressLine1());
        addressModel.setAddress2(getAddressLine2());
        addressModel.setCity(getCity());
        addressModel.setPostal_code(getPostalCode());
        addressModel.setState(getState());
        addressModel.setPhone(getTelephone());
        addressModel.setAddress_nickname(profileNickName);
        addressModel.setIsDefault(true);
        addAddressToProfileModel.addAddresses(addressModel);
        return addAddressToProfileModel;
    }
    */
}
