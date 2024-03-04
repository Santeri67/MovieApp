package com.friendlist.tehtava.domain;

public class Friend {
    private String firstName;
    private String lastName;

    // Konstruktorit, getterit ja setterit
    public Friend() {}

    public Friend(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
