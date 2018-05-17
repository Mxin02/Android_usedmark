package com.example.myapplication.base;

import java.io.Serializable;

/**
 * Created by L on 2017/9/12.
 */

public class Text implements  Serializable{
    private String text;
    private String time;
    private Boolean isVisible;
    private Boolean isNetUserVisible;

    public Text() {
    }

    public Text(String text, String time, Boolean isVisible, Boolean isNetUserVisible) {
        this.text = text;
        this.time = time;
        this.isVisible = isVisible;
        this.isNetUserVisible = isNetUserVisible;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getNetUserVisible() {
        return isNetUserVisible;
    }

    public void setNetUserVisible(Boolean netUserVisible) {
        isNetUserVisible = netUserVisible;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
