package com.seg2105app.delieverable1.users;

public abstract class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String type;

    public User(String username, String password, String firstName, String lastName, String type){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getType(){ return type; }
}
