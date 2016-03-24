package com.objectedge.store.data.model.order;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "Michael Katkov" on 10/6/2015.
 */
public class OrderModel implements IBaseModel{

    private List<LinkModel> links;
    private String customerId;
    private String transactionId;
    private String x_creationSiteId;
    private String transactionStatusInformation;
    private String POSLogDateTime;
    private boolean x_gwp;
    private List<ShippingGroupModel> shippingGroups;
    private boolean x_giftWrapped;
    private String sequenceNumber;
    private List<PaymentGroupModel> paymentGroups;
    private String orderChannel;
    private String orderPriority;
    private Totals totals;
    private List<LineItem> lineItems;
    private String transactionStatus;
    private String transactionTaxAmount;

    public OrderModel(){
        links = new ArrayList<>();
        shippingGroups = new ArrayList<>();
        paymentGroups = new ArrayList<>();
        lineItems = new ArrayList<>();
    }

    public void updateOrder(OrderModel newOrder){
        links.clear();links.addAll(newOrder.getLinks());
        customerId = newOrder.customerId;
        transactionId = newOrder.transactionId;
        x_creationSiteId = newOrder.x_creationSiteId;
        transactionStatusInformation = newOrder.transactionStatusInformation;
        POSLogDateTime = newOrder.POSLogDateTime;
        x_gwp = newOrder.x_gwp;
        shippingGroups.clear();shippingGroups.addAll(newOrder.getShippingGroups());
        x_giftWrapped = newOrder.x_giftWrapped;
        sequenceNumber = newOrder.sequenceNumber;
        paymentGroups.clear();paymentGroups.addAll(newOrder.getPaymentGroups());
        orderChannel = newOrder.orderChannel;
        orderPriority = newOrder.orderPriority;
        totals = newOrder.totals;
        lineItems.clear();lineItems.addAll(newOrder.getLineItems());
        transactionStatus = newOrder.transactionStatus;
        transactionTaxAmount = newOrder.transactionTaxAmount;
    }

    public void addShipping(ShippingGroupModel shippingGroupModel){
        this.shippingGroups.add(shippingGroupModel);
    }

    public void addPayment(PaymentGroupModel paymentGroupModel){
        this.paymentGroups.add(paymentGroupModel);
    }

    public void addLineItem(LineItem lineItem){
        this.lineItems.add(lineItem);
    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getX_creationSiteId() {
        return x_creationSiteId;
    }

    public void setX_creationSiteId(String x_creationSiteId) {
        this.x_creationSiteId = x_creationSiteId;
    }

    public String getTransactionStatusInformation() {
        return transactionStatusInformation;
    }

    public void setTransactionStatusInformation(String transactionStatusInformation) {
        this.transactionStatusInformation = transactionStatusInformation;
    }

    public String getPOSLogDateTime() {
        return POSLogDateTime;
    }

    public void setPOSLogDateTime(String POSLogDateTime) {
        this.POSLogDateTime = POSLogDateTime;
    }

    public boolean isX_gwp() {
        return x_gwp;
    }

    public void setX_gwp(boolean x_gwp) {
        this.x_gwp = x_gwp;
    }

    public List<ShippingGroupModel> getShippingGroups() {
        return shippingGroups;
    }

    public void setShippingGroups(List<ShippingGroupModel> shippingGroups) {
        this.shippingGroups = shippingGroups;
    }

    public boolean isX_giftWrapped() {
        return x_giftWrapped;
    }

    public void setX_giftWrapped(boolean x_giftWrapped) {
        this.x_giftWrapped = x_giftWrapped;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public List<PaymentGroupModel> getPaymentGroups() {
        return paymentGroups;
    }

    public void setPaymentGroups(List<PaymentGroupModel> paymentGroups) {
        this.paymentGroups = paymentGroups;
    }

    public String getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(String orderChannel) {
        this.orderChannel = orderChannel;
    }

    public String getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(String orderPriority) {
        this.orderPriority = orderPriority;
    }

    public Totals getTotals() {
        return totals;
    }

    public void setTotals(Totals totals) {
        this.totals = totals;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionTaxAmount() {
        return transactionTaxAmount;
    }

    public void setTransactionTaxAmount(String transactionTaxAmount) {
        this.transactionTaxAmount = transactionTaxAmount;
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

    public static class X_specialInstructions {

    }

    public static class ShippingGroupModel implements IBaseModel{

        private String x_shippingGroupId;
        private List<LinkModel> links;
        private String shippingFee;
        private X_specialInstructions x_specialInstructions;
        private Address address;
        private List<X_handlingInstruction> x_handlingInstructions;
        private String x_type;
        private String x_shippingGroupStateDetail;
        private String x_shippingGroupState;
        private String method;
        private String notes;

        public static class X_handlingInstruction {

        }

        public String getX_shippingGroupId() {
            return x_shippingGroupId;
        }

        public void setX_shippingGroupId(String x_shippingGroupId) {
            this.x_shippingGroupId = x_shippingGroupId;
        }

        public List<LinkModel> getLinks() {
            return links;
        }

        public void setLinks(List<LinkModel> links) {
            this.links = links;
        }

        public String getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(String shippingFee) {
            this.shippingFee = shippingFee;
        }

        public X_specialInstructions getX_specialInstructions() {
            return x_specialInstructions;
        }

        public void setX_specialInstructions(X_specialInstructions x_specialInstructions) {
            this.x_specialInstructions = x_specialInstructions;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<X_handlingInstruction> getX_handlingInstructions() {
            return x_handlingInstructions;
        }

        public void setX_handlingInstructions(List<X_handlingInstruction> x_handlingInstructions) {
            this.x_handlingInstructions = x_handlingInstructions;
        }

        public String getX_type() {
            return x_type;
        }

        public void setX_type(String x_type) {
            this.x_type = x_type;
        }

        public String getX_shippingGroupStateDetail() {
            return x_shippingGroupStateDetail;
        }

        public void setX_shippingGroupStateDetail(String x_shippingGroupStateDetail) {
            this.x_shippingGroupStateDetail = x_shippingGroupStateDetail;
        }

        public String getX_shippingGroupState() {
            return x_shippingGroupState;
        }

        public void setX_shippingGroupState(String x_shippingGroupState) {
            this.x_shippingGroupState = x_shippingGroupState;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    public static class PaymentGroupModel implements IBaseModel{

        private List<LinkModel> links;
        private X_specialInstructions x_specialInstructions;
        private double amount;
        private double x_amountDebited;
        private String currencyCode;
        private String expirationDate;
        private Address address;
        private double authorizedAmount;
        private String primaryAccountNumber;
        private String typeCode;
        private String x_paymentGroupState;
        private String creditCard;
        private double x_amountCredited;

        public List<LinkModel> getLinks() {
            return links;
        }

        public void setLinks(List<LinkModel> links) {
            this.links = links;
        }

        public X_specialInstructions getX_specialInstructions() {
            return x_specialInstructions;
        }

        public void setX_specialInstructions(X_specialInstructions x_specialInstructions) {
            this.x_specialInstructions = x_specialInstructions;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getX_amountDebited() {
            return x_amountDebited;
        }

        public void setX_amountDebited(double x_amountDebited) {
            this.x_amountDebited = x_amountDebited;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public double getAuthorizedAmount() {
            return authorizedAmount;
        }

        public void setAuthorizedAmount(double authorizedAmount) {
            this.authorizedAmount = authorizedAmount;
        }

        public String getPrimaryAccountNumber() {
            return primaryAccountNumber;
        }

        public void setPrimaryAccountNumber(String primaryAccountNumber) {
            this.primaryAccountNumber = primaryAccountNumber;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getX_paymentGroupState() {
            return x_paymentGroupState;
        }

        public void setX_paymentGroupState(String x_paymentGroupState) {
            this.x_paymentGroupState = x_paymentGroupState;
        }

        public String getCreditCard() {
            return creditCard;
        }

        public void setCreditCard(String creditCard) {
            this.creditCard = creditCard;
        }

        public double getX_amountCredited() {
            return x_amountCredited;
        }

        public void setX_amountCredited(double x_amountCredited) {
            this.x_amountCredited = x_amountCredited;
        }
    }

    public static class Totals {

        private String transactionGrandAmount;
        private String currencyCode;
        private String transactionNetAmount;

        public String getTransactionGrandAmount() {
            return transactionGrandAmount;
        }

        public void setTransactionGrandAmount(String transactionGrandAmount) {
            this.transactionGrandAmount = transactionGrandAmount;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getTransactionNetAmount() {
            return transactionNetAmount;
        }

        public void setTransactionNetAmount(String transactionNetAmount) {
            this.transactionNetAmount = transactionNetAmount;
        }
    }

    public static class LineItem implements IBaseModel{

        private List<LinkModel> links;
        private X_itemPriceInfo x_itemPriceInfo;
        private String itemType;
        private List<MerchandiseHierarchy> merchandiseHierarchy;
        private String state;
        private String x_gwp;
        private int quantity;
        private String itemId;
        private String x_siteId;
        private String sequenceNumber;

        public String getProductIdFromMerHer(){
            for(MerchandiseHierarchy m: merchandiseHierarchy){
                if(m.getTypeCode().equals("PRODUCT")){
                    return m.getValue();
                }
            }
            return null;
        }

        public String getX_gwp() {
            return x_gwp;
        }

        public void setX_gwp(String x_gwp) {
            this.x_gwp = x_gwp;
        }
        public List<LinkModel> getLinks() {
            return links;
        }

        public void setLinks(List<LinkModel> links) {
            this.links = links;
        }

        public X_itemPriceInfo getX_itemPriceInfo() {
            return x_itemPriceInfo;
        }

        public void setX_itemPriceInfo(X_itemPriceInfo x_itemPriceInfo) {
            this.x_itemPriceInfo = x_itemPriceInfo;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public List<MerchandiseHierarchy> getMerchandiseHierarchy() {
            return merchandiseHierarchy;
        }

        public void setMerchandiseHierarchy(List<MerchandiseHierarchy> merchandiseHierarchy) {
            this.merchandiseHierarchy = merchandiseHierarchy;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getX_siteId() {
            return x_siteId;
        }

        public void setX_siteId(String x_siteId) {
            this.x_siteId = x_siteId;
        }

        public String getSequenceNumber() {
            return sequenceNumber;
        }

        public void setSequenceNumber(String sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }

        public static class MerchandiseHierarchy {
            private String value;
            private String typeCode;

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

        public static class X_itemPriceInfo {

            private double extendedDiscountAmount;
            private double amount;
            private List<RetailPricingModifier> retailPricingModifierList;
            private String currencyCode;
            private double unitListPrice;
            private Tax tax;
            private double regularSalesUnitPrice;
            private double extendedAmount;
            private double discountAmount;

            public double getExtendedDiscountAmount() {
                return extendedDiscountAmount;
            }

            public void setExtendedDiscountAmount(double extendedDiscountAmount) {
                this.extendedDiscountAmount = extendedDiscountAmount;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public List<RetailPricingModifier> getRetailPricingModifierList() {
                return retailPricingModifierList;
            }

            public void setRetailPricingModifier(List<RetailPricingModifier> retailPricingModifierList) {
                this.retailPricingModifierList = retailPricingModifierList;
            }

            public String getCurrencyCode() {
                return currencyCode;
            }

            public void setCurrencyCode(String currencyCode) {
                this.currencyCode = currencyCode;
            }

            public double getUnitListPrice() {
                return unitListPrice;
            }

            public void setUnitListPrice(double unitListPrice) {
                this.unitListPrice = unitListPrice;
            }

            public Tax getTax() {
                return tax;
            }

            public void setTax(Tax tax) {
                this.tax = tax;
            }

            public double getRegularSalesUnitPrice() {
                return regularSalesUnitPrice;
            }

            public void setRegularSalesUnitPrice(double regularSalesUnitPrice) {
                this.regularSalesUnitPrice = regularSalesUnitPrice;
            }

            public double getExtendedAmount() {
                return extendedAmount;
            }

            public void setExtendedAmount(double extendedAmount) {
                this.extendedAmount = extendedAmount;
            }

            public double getDiscountAmount() {
                return discountAmount;
            }

            public void setDiscountAmount(double discountAmount) {
                this.discountAmount = discountAmount;
            }

            public static class RetailPricingModifier {

            }

            public static class Tax {
                private double amount;
                private List<RetailPricingModifier> retailPricingModifierList;
                private double countryTax;
                private double stateTax;
                private double cityTax;
                private double districtTax;

                public double getAmount() {
                    return amount;
                }

                public void setAmount(double amount) {
                    this.amount = amount;
                }

                public List<RetailPricingModifier> getRetailPricingModifierList() {
                    return retailPricingModifierList;
                }

                public void setRetailPricingModifier(List<RetailPricingModifier> retailPricingModifierList) {
                    this.retailPricingModifierList = retailPricingModifierList;
                }

                public double getCountryTax() {
                    return countryTax;
                }

                public void setCountryTax(double countryTax) {
                    this.countryTax = countryTax;
                }

                public double getStateTax() {
                    return stateTax;
                }

                public void setStateTax(double stateTax) {
                    this.stateTax = stateTax;
                }

                public double getCityTax() {
                    return cityTax;
                }

                public void setCityTax(double cityTax) {
                    this.cityTax = cityTax;
                }

                public double getDistrictTax() {
                    return districtTax;
                }

                public void setDistrictTax(double districtTax) {
                    this.districtTax = districtTax;
                }
            }
        }
    }
}
