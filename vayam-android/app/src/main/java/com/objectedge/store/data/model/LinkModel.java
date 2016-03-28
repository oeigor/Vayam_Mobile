package com.objectedge.store.data.model;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class LinkModel {

    private String rel;
    private String href;

    public LinkModel(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public LinkModel() {
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
