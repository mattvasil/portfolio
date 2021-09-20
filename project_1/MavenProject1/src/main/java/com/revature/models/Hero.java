package com.revature.models;

public class Hero {
    private int id;
    private String first;
    private String last;
    private String alias;

    public Hero() {}

    public Hero(int id, String first, String last, String alias) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
