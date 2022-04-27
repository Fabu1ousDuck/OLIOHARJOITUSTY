package com.example.olioharjoitusty;

public class Actor {
    private String firstname;
    private String lastname;


    public Actor (String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }

    //adding methods for getting the private variables
    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

}
