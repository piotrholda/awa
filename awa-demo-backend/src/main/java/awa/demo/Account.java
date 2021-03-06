package awa.demo;

import awa.persistence.Entity;

public class Account extends Entity<UUIDKey> {

    private UUIDKey id;
    private String login;
    private String email;
    private String password;

    public UUIDKey getId() {
        return id;
    }

    public void setId(UUIDKey id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
