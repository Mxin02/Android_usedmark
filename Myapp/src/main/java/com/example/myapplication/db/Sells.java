package com.example.myapplication.db;

/**
 * Created by Administrator on 2018/5/5.
 */

public class Sells {
    private int id;
    private String BuyGoodsId;
    private String username;
    private String number;
    private String time;
    private String img;
    private String tit;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuyGoodsId() {
        return BuyGoodsId;
    }

    public void setBuyGoodsId(String buyGoodsId) {
        BuyGoodsId = buyGoodsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
