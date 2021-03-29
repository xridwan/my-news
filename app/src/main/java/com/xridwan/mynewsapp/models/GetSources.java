package com.xridwan.mynewsapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSources {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("sources")
    @Expose
    private List<SourceList> sourceLists;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceList> getSourceLists() {
        return sourceLists;
    }

    public void setSourceLists(List<SourceList> sourceLists) {
        this.sourceLists = sourceLists;
    }
}
