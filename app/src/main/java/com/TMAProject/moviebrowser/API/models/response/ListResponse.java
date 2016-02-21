package com.TMAProject.moviebrowser.API.models.response;

import com.TMAProject.moviebrowser.API.models.ListData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tarekkma on 2/21/16.
 */
public class ListResponse {
    @SerializedName("data")
    @Expose
    private ListData data;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_message")
    @Expose
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ListData getData() {

        return data;
    }

    public void setData(ListData data) {
        this.data = data;
    }
}
