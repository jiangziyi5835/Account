package com.example.boyidashi.model;

public class UserDetial {
    private int useType;
    private Float useNumber;
    private String id;

    public UserDetial(int useType, Float useNumber, String id) {
        this.useType = useType;
        this.useNumber = useNumber;
        this.id = id;
    }


    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public Float getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Float useNumber) {
        this.useNumber = useNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
