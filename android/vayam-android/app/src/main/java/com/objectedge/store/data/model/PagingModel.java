package com.objectedge.store.data.model;

/**
 * Created by eloor_000 on 9/9/2015.
 */
public class PagingModel {

    private String page;
    private String totalItemCount;
    private String totalPages;
    private String pageSize;

    public PagingModel(String page, String totalItemCount, String totalPages, String pageSize) {
        this.page = page;
        this.totalItemCount = totalItemCount;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public PagingModel() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(String totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
