package com.objectedge.store.data.model.user;

import com.objectedge.store.data.model.IBaseModel;
import com.objectedge.store.data.model.LinkModel;
import com.objectedge.store.data.model.PagingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class UsersListModel implements IBaseModel{

    private List<LinkModel> links;
    private List<UserModel> items;
    private PagingModel paging;

    public UsersListModel(List<LinkModel> links, List<UserModel> items, PagingModel paging) {
        this.links = links;
        this.items = items;
        this.paging = paging;
    }

    public UsersListModel() {

    }

    public List<LinkModel> getLinks() {
        return links;
    }

    public void setLinks(List<LinkModel> links) {
        this.links = links;
    }

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }

    public PagingModel getPaging() {
        return paging;
    }

    public void setPaging(PagingModel paging) {
        this.paging = paging;
    }
}
