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

public class MovieList {

    ArrayList<Movie> list1;
    ArrayList<String> movies = null;
    int llength;



    public MovieList() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        list1 = new ArrayList<Movie>();

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String url = "https://www.finnkino.fi/xml/Events";
        Document doc = null;
        try {
            doc = db.parse(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();
        NodeList list = doc.getDocumentElement().getElementsByTagName("Event");
        for (int temp = 0; temp < list.getLength(); temp++) {
            ArrayList<Actor> actorList = new ArrayList<>();
            Node node = list.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String title = element.getElementsByTagName("Title").item(0).getTextContent();
                String genre = element.getElementsByTagName("Genres").item(0).getTextContent();
                String length = element.getElementsByTagName("LengthInMinutes").item(0).getTextContent();
                String year = element.getElementsByTagName("ProductionYear").item(0).getTextContent();

                list1.add(new Movie(title,length,year,genre,actorList));
                Node cast = element.getElementsByTagName("Cast").item(0);


                if (cast.getNodeType() == Node.ELEMENT_NODE) {
                    Element castEl = (Element) cast;
                    NodeList actors = castEl.getElementsByTagName("Actor");

                    for (int i = 0; i < actors.getLength(); i++) {
                        Node actorNode = actors.item(i);
                        if (actorNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element actorEl = (Element) actorNode;
                            String first = actorEl.getElementsByTagName("FirstName").item(0).getTextContent();
                            String last = actorEl.getElementsByTagName("LastName").item(0).getTextContent();
                            actorList.add(new Actor(first, last));

                        }
                    }




                }
            }
        }
    }


    public void addMovie(String name, String length, String year,String genre,ArrayList <Actor> actorList) {
        list1.add(new Movie(name,length,year,genre,actorList));
        llength++;
    }

    public ArrayList getNames() {
        ArrayList names = new ArrayList();
        for (int position = 0; position < llength; position++) {
            String a = list1.get(position).getMovName();
            names.add(a);
        }
        return names;
    }

    public String getLength(String movie) {
        String length = null;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(list1.get(i).getLength())) {
                length = list1.get(i).length;
            }
        }
        return length;
    }

    public String getYear(String movie) {
        String year = null;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(list1.get(i).getYear())) {
                year = list1.get(i).year;
            }
        }
        return year;
    }

    public String getGenre(String movie) {
        String genre = null;
        for (int i = 0; i < llength; i++) {
            if (movie.contains(list1.get(i).getGenre())) {
                genre = list1.get(i).genre;
            }
        }
        return genre;
    }
}

