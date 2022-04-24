package com.example.olioharjoitusty;

import java.io.Serializable;

public class Account implements Serializable {
    String name1;
    String email1;
    String uid2;

    public Account(String name, String email, String uid ){
        name1 = name;
        email1 =email;
        uid2 = uid;
    }
}
