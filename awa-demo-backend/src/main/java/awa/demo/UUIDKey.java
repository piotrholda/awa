package awa.demo;

import awa.persistence.Key;

public class UUIDKey implements Key {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
