package com.example.boyidashi.model;

import java.util.Date;

public class Used {
    String id;
    String username;
    float number;
    int useType;
    Date data;

    public Used(String id, String username, float number, int useType, Date data) {
        this.id = id;
        this.username = username;
        this.number = number;
        this.useType = useType;
        this.data = data;
    }

    public Used(String username, float number, int useType, Date data) {
        this.username = username;
        this.number = number;
        this.useType = useType;
        this.data = data;
    }

    public Used(String username, float number, int useType) {
        this.username = username;
        this.number = number;
        this.useType = useType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}