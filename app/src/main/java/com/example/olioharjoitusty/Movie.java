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
    String name;
    String length;
    String year;
    String genre;
    ArrayList<Actor> actorArrayList = new ArrayList<>();

    public Movie ( String name, String length, String year,String genre,ArrayList<Actor> actorArrayList) {

        this.name = name;
        this.length = length;
        this.year = year;
        this.genre = genre;
        this.actorArrayList = actorArrayList;
    }

    public String getMovName() {
        return name;
    }

    public String getLength(){
        return length;
    }

    public String getYear(){
        return year;
    }

    public String getGenre(){
        return genre;
    }

}
