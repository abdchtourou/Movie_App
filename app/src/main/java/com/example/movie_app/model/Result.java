package com.example.movie_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("page")
    @Expose
    private Integer Page;
    @SerializedName("total_pages")
    @Expose
    private Integer TotalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResult;
    @SerializedName("results")
    @Expose

    private List<Movies> Data;


    public Integer getPage() {
        return Page;
    }

    public void setPage(Integer page) {
        Page = page;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        TotalPages = totalPages;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public List<Movies> getData() {
        return Data;
    }

    public void setData(List<Movies> data) {
        Data = data;
    }
}
