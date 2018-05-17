package com.example.myapplication.base;

import java.io.Serializable;

/**
 * Created by L on 2017/9/13.
 */

public class Response implements  Serializable {
    private String response;
    private String time;
    private Boolean isVisible;

    public Response(String response, String time, Boolean isVisible) {

        this.response = response;
        this.time = time;
        this.isVisible = isVisible;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
