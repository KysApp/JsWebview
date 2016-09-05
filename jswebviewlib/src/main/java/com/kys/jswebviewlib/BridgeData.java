package com.kys.jswebviewlib;

/**
 * Created by bsy on 2016/1/21.
 */
public class BridgeData {
    private String type, value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return value;
    }

    public void setAge(String value) {
        this.value = value;
    }

    public BridgeData(String type, String value) {
        super();
        this.type = type;
        this.value = value;
    }
}
