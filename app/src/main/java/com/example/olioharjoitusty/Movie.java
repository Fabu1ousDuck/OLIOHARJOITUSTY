package com.example.olioharjoitusty;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Movie {
    private String name;
    private String genre;
    private ArrayList<Actor> actorArrayList = new ArrayList<>();

    public Movie ( String name,String genre,ArrayList<Actor> actorArrayList) {

        this.name = name;
        this.genre = genre;
        this.actorArrayList = actorArrayList;
    }
    //adding methods for getting the private variables
    public String getMovName() {
        return name;
    }

    public String getGenre(){
        return genre;
    }

    public ArrayList<Actor> getActorArraylist(){
        return actorArrayList;
    }
}
