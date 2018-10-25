package com.seg2105app.delieverable1.users;

public abstract class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }


}