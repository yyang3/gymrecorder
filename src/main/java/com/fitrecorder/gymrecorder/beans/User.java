package com.fitrecorder.gymrecorder.beans;

public class User implements Cloneable {

    private int id;
    private String surName;
    private String givenName;
    private String userName;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override public String toString() {
        return "User{" +
               "id=" + id +
               ", surName='" + surName + '\'' +
               ", givenName='" + givenName + '\'' +
               ", userName='" + userName + '\'' +
               ", pwd='" + pwd + '\'' +
               '}';
    }

    private String pwd;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User clone () {
        User ret = new User();
        ret.setUserName(this.userName);
        ret.setSurName(this.surName);
        ret.setGivenName(this.givenName);
        ret.setPwd(this.pwd);
        ret.setId(this.id);
        return ret;
    }
}
