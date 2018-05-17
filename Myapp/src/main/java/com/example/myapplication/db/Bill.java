package com.example.myapplication.db;

/**
 * Created by Administrator on 2018/5/6.
 */

public class Bill {
    private int id;
    private String money;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        int i = Integer.parseInt(money);
        if (i > 0) {
            return "账户余额                                " + "+" + i + "元";
        } else if (i < 0) {
            return "账户余额                                 " + i + "元";
        }
        return null;
    }
}
