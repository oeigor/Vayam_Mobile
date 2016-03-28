package com.objectedge.store.data.model.order;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;

/**
 * Created by "Michael Katkov" on 10/6/2015.
 */
public class AddShippingToOrderModel implements IBaseModel{

    private Address address;
    private String method;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public static class Address {

        private String lastName;
        private String postalCode;
        private String state;
        private String address1;
        private String address2;
        private String firstName;
        private String country;
        private String city;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public String buildJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
