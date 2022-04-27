package com.example.olioharjoitusty;

public class Actor {
    String firstname;
    String lastname;


    public Actor (String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }


    public String getFirstname(){
        return firstname;
    }

    public String getLastname(){
        return lastname;
    }

}
