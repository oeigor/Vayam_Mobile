package com.objectedge.store.data.model.user;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Created by eloor_000 on 9/9/2015.
 */
public class UserModel implements IBaseModel{

    private List<LinkModel> links;
    private String gender;
    private String birthMonthDay;
    private String birthYear;
    private Address x_shippingAddress;
    private List<Address> x_secondaryAddresses;
    private ContactInformation contactInformation;
    private List<Name> names;
    private String customerId;
    private String dateTime;
    private String entityInformation;
    private PersonalPreferences personalPreferences;

    public UserModel() {
    }

    public UserModel(List<LinkModel> links, String gender, String birthMonthDay,
                     String birthYear, Address x_shippingAddress, List<Address> x_secondaryAddresses,
                     ContactInformation contactInformation, List<Name> names, String customerId,
                     String dateTime, String entityInformation, PersonalPreferences personalPreferences) {
        this.links = links;
        this.gender = gender;
        this.birthMonthDay = birthMonthDay;
        this.birthYear = birthYear;
        this.x_shippingAddress = x_shippingAddress;
        this.x_secondaryAddresses = x_secondaryAddresses;
        this.contactInformation = contactInformation;
        this.names = names;
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.entityInformation = entityInformation;
        this.personalPreferences = personalPreferences;
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public String getUserId(){
        String url = links.get(0).getHref();
        StringTokenizer stringTokenizer = new StringTokenizer(url,"/");
        List<String> parts = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()){
            parts.add(stringTokenizer.nextToken());
        }
        return parts.get(parts.size() - 1);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthMonthDay() {
        return birthMonthDay;
    }

    public void setBirthMonthDay(String birthMonthDay) {
        this.birthMonthDay = birthMonthDay;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public Address getX_shippingAddress() {
        return x_shippingAddress;
    }

    public void setX_shippingAddress(Address x_shippingAddress) {
        this.x_shippingAddress = x_shippingAddress;
    }

    public List<Address> getX_secondaryAddresses() {
        return x_secondaryAddresses;
    }

    public void setX_secondaryAddresses(List<Address> x_secondaryAddresses) {
        this.x_secondaryAddresses = x_secondaryAddresses;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEntityInformation() {
        return entityInformation;
    }

    public void setEntityInformation(String entityInformation) {
        this.entityInformation = entityInformation;
    }

    public PersonalPreferences getPersonalPreferences() {
        return personalPreferences;
    }

    public void setPersonalPreferences(PersonalPreferences personalPreferences) {
        this.personalPreferences = personalPreferences;
    }

    //X_shippingAddress class start----------------------------------------------------------------
    public static class Address {

        private String postalCode;
        private String state;
        private List<AddressLine> addressLines;
        private String country;
        private String city;

        public Address() {
        }

        public Address(String postalCode, String state, List<AddressLine> addressLines, String country, String city) {
            this.postalCode = postalCode;
            this.state = state;
            this.addressLines = addressLines;
            this.country = country;
            this.city = city;
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

        public List<AddressLine> getAddressLines() {
            return addressLines;
        }

        public void setAddressLines(List<AddressLine> addressLines) {
            this.addressLines = addressLines;
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

        //AddressLine class start------------------------------------------------------------
        public static class AddressLine {

            private String typeCode;
            private String relativeOrder;
            private String addressLine;

            public AddressLine() {
            }

            public AddressLine(String typeCode, String relativeOrder, String addressLine) {
                this.typeCode = typeCode;
                this.relativeOrder = relativeOrder;
                this.addressLine = addressLine;
            }

            public String getTypeCode() {
                return typeCode;
            }

            public void setTypeCode(String typeCode) {
                this.typeCode = typeCode;
            }

            public String getRelativeOrder() {
                return relativeOrder;
            }

            public void setRelativeOrder(String relativeOrder) {
                this.relativeOrder = relativeOrder;
            }

            public String getAddressLine() {
                return addressLine;
            }

            public void setAddressLine(String addressLine) {
                this.addressLine = addressLine;
            }
        }
        //AddressLine class end------------------------------------------------------------
    }

    //ContactInformation class start-------------------------------------------------------------
    public static class ContactInformation {

        private Email email;

        public ContactInformation(Email email) {
            this.email = email;
        }

        public ContactInformation() {
        }

        public Email getEmail() {
            return email;
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        //Email class start-------------------------------------------------------------
        public static class Email {

            private String emailAddress;

            public Email() {
            }

            public Email(String emailAddress) {
                this.emailAddress = emailAddress;
            }

            public String getEmailAddress() {
                return emailAddress;
            }

            public void setEmailAddress(String emailAddress) {
                this.emailAddress = emailAddress;
            }
        }
        //Email class end-------------------------------------------------------------
    }
    //ContactInformation class end---------------------------------------------------------------

    //Name class start----------------------------------------------------------------------------
    public static class Name {

        private String value;
        private String typeCode;

        public Name(String value, String typeCode) {
            this.value = value;
            this.typeCode = typeCode;
        }

        public Name() {
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }
    }
    //Name class end------------------------------------------------------------------------------

    //PersonalPreferences class start-------------------------------------------------------------
    public static class PersonalPreferences {

        private String x_receivePromoEmail;

        public PersonalPreferences(String x_receivePromoEmail) {
            this.x_receivePromoEmail = x_receivePromoEmail;
        }

        public PersonalPreferences() {
        }

        public String getX_receivePromoEmail() {
            return x_receivePromoEmail;
        }

        public void setX_receivePromoEmail(String x_receivePromoEmail) {
            this.x_receivePromoEmail = x_receivePromoEmail;
        }
    }
    //PersonalPreferences class start-------------------------------------------------------------
}
