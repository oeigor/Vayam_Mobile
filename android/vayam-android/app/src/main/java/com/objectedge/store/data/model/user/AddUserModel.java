package com.objectedge.store.data.model.user;

import com.google.gson.Gson;
import com.objectedge.store.data.model.IBaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "Michael Katkov" on 10/5/2015.
 */
public class AddUserModel implements IBaseModel{

    private String email;
    private String password;
    private String userId;
    private List<Name> names;

    public AddUserModel(String email, List<Name> names, String userId, String password) {
        this.email = email;
        this.names = names;
        this.userId = userId;
        this.password = password;
    }

    public AddUserModel() {
        names = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public void addName(Name name){
        this.names.add(name);
    }

    public String buildJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

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
}
