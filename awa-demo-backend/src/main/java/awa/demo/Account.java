package awa.demo;

import awa.persistence.Entity;

public class Account extends Entity<UUIDKey> {

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
