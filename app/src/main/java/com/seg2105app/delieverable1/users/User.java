package com.seg2105app.delieverable1.users;

public abstract class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


}
