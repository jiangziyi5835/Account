package com.example.account.model;

public class Used {
    String username;
    float number;
    int useType;
    String data;

    public Used(String username, float number, int useType, String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}