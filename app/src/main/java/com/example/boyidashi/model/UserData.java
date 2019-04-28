package com.example.boyidashi.model;

import java.util.Date;
import java.util.List;

public class UserData {
    private String username;
    private Date date;
    private List<UserDetial> userDetialList;

    public UserData(String username, Date date, List<UserDetial> userDetialList) {
        this.username = username;
        this.date = date;
        this.userDetialList = userDetialList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserDetial> getUserDetialList() {
        return userDetialList;
    }

    public void setUserDetialList(List<UserDetial> userDetialList) {
        this.userDetialList = userDetialList;
    }
}
