package com.seg2105app.users;

public class UserFactory {

    public User getUser(String username, String password, String firstName, String lastName, String userType){
        if (userType == null){
            return null;
        }
        if (userType.equalsIgnoreCase("Administrator")){
            return new Administrator(username, password, firstName, lastName, userType);
        }
        else if (userType.equalsIgnoreCase("ServiceProvider")){
            return new ServiceProvider(username, password, firstName, lastName, userType);
        }
        else if (userType.equalsIgnoreCase("HomeOwner")){
            return new HomeOwner(username, password, firstName, lastName, userType);
        }

        return null;
    }
}
