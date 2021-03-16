package com.example.InventoryManager.narola.Model;

public class ProductSearchRequest {
    private int catogId = 0;
    private String searchStr = "";
    private String sortBy = "productId";
    private String sortOrder = "asc";
    private int recordPerPage = 10;
    private int pageNo = 1;

    public int getCatogId() {
        return catogId;
    }

    public void setCatogId(int catogId) {
        this.catogId = catogId;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }


    public int getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
