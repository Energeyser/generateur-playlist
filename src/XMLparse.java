// le parser, c'est lui qui va generer la playlist a partir de la bibliotheque et de la duree souhaitee


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLparse {
    DocumentBuilder builder;
    Document doc = null;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public XMLparse() {
        factory.setNamespaceAware(true);
    }

    public void Parse(int duree, File fichier){
        try{
            this.builder = this.factory.newDocumentBuilder();
            this.doc = this.builder.parse(fichier);

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();
            ArrayList<Chanson> bibliotheque = new ArrayList<>();
            NodeList nodes = null;
            try {
                XPathExpression expr = xpath.compile("//key[. = 'Name']/following-sibling::*[1]/text()");
                nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < nodes.getLength(); i++) {
                Chanson chanson = new Chanson();


                //appel aux differentes fonctions qui permettent d'extraire les informations souhaitees
                //chanson.setTotalTime(getTrackTime(this.doc, xpath, i));
                System.out.println(i);
                //System.out.println("Temps des chansons :" + Arrays.toString(times.toArray()));
                chanson.setName(getTrackName(nodes,i));
                //List<String> locations = getTrackLocations(this.doc, xpath);
                //System.out.println("Noms des chansons :" + Arrays.toString(names.toArray()));


                bibliotheque.add(chanson);
            }
            try {
                XPathExpression expr = xpath.compile("//key[. = 'Total Time']/following-sibling::*[1]/text()");
                nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            } catch (XPathExpressionException e) { //gestion des exceptions
                e.printStackTrace();
            }
            for (int i = 0; i < nodes.getLength(); i++) {
                bibliotheque.get(i).setTotalTime(getTrackTime(nodes,i));
            }

            /*for(int i=0; i<bibliotheque.size();i++) {
                System.out.println(i + " : " + bibliotheque.get(i).getName());
                System.out.println(i + " : " + bibliotheque.get(i).getTotalTime());
            }*/
            ArrayList<Chanson> playlist = generatePlaylist(duree,bibliotheque);
            for(int i=0; i<playlist.size();i++) {
                System.out.println(i + " : " + playlist.get(i).getName());
                //System.out.println(i + " : " + playlist.get(i).getTotalTime());
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    //cette fonction va reccuperer les durees des chansons contenues dans la bibliotheque et les stocker dans une liste de String
    public static int getTrackTime(NodeList nodes,int i) {
        String s_time = "0";
        // on fait l'action sur l'ensemble du fichier
        //for (long i = 0; i < nodes.getLength(); i++)
        s_time = nodes.item((int) i).getNodeValue();
        // System.out.println(s_time);
        int time = Integer.parseInt(s_time);

        return time;
    }

    //meme chose qu'au dessus mais pour le nom des chansons, seule l'expression xpath change
    public static String getTrackName(NodeList nodes,int i) {
        String name = " ";
        name = nodes.item(i).getNodeValue();

        return name;
    }

    //pareil pour les chemins des chansons
    public static List<String> getTrackLocations(Document doc, XPath xpath) {
        List<String> locations = new ArrayList<>();
        try {
            XPathExpression expr = xpath.compile("//key[. = 'Location']/following-sibling::*[1]/text()");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                locations.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return locations;
    }


    //cette fontion genere la playlist a partir des informations
    public static ArrayList<Chanson> generatePlaylist(long duree, ArrayList<Chanson> bibliotheque) {
        //ce tableau fait la taille du tableau des temps et permet de savoir si la chanson au rang correspondant a été
        //utilisee -> permet d'eviter les doublons
        int[] utilise = new int[bibliotheque.size()];
        long temp = 0; //variable temporaire
        ArrayList<Chanson> playlist = new ArrayList<>();


        /* algorithme glouton, avec un seul facteur (duree de la chanson)
        principe : tant qu'on a pas atteint la duree demandee, on rajoute les morceaux qui rentrent
        ajout d'un facteur aleatoire pour ne pas avoir toujours les memes chansons
        la precision augmente en fonction du nombre de morceaux dans la bibliotheque (le temps de calcul aussi)
        PS: a optimiser (ameliorer precision et reduire tps de calcul) mais fonctionne*/


        //le 10000 correspond a la tolerance en ms
        while (temp <= (duree - 10000)) {
            for (long i = 0; i < bibliotheque.size(); i++) { // pour le nombre de chansons dans la liste
                double x = Math.random() * (bibliotheque.size()); // on choisit une chanson au hasard dans la liste

                if ((bibliotheque.get((int) x).getTotalTime() + temp) <= duree && bibliotheque.get((int)x).getTotalTime() != 0) { //si elle rentre dans le temps non utilise de la playlist
                    utilise[(int) x] = 1; // alors on la marque comme utilisee
                    playlist.add(bibliotheque.get((int) x));
                    //System.out.println(bibliotheque.get((int) x).getName());
                    temp = temp + bibliotheque.get((int) x).getTotalTime(); // on met a jour la duree actuelle de la playlist
                } else {
                    utilise[(int) x] = 0; // sinon on marque qu'on utilise pas la chanson
                }
            }

            //calcul de la duree reelle de la playlist en hh:mm:ss a partir des ms
            long nb_hh = temp / (60 * 60 * 1000);

            long reste_milli = temp - (nb_hh * 60 * 60 * 1000);
            long nb_min = reste_milli / (60 * 1000);

            reste_milli = reste_milli - (nb_min * 60 * 1000);
            long nb_sec = reste_milli / 1000;

            reste_milli = reste_milli - (nb_sec * 1000);
            long nb_mil = reste_milli;

            System.out.println("Duree reelle de la playlist :\n" + nb_hh + ":" + nb_min + ":" + nb_sec + ":" + nb_mil);



            //WindowPlaylist windowPlaylist = new WindowPlaylist();
        }
        return playlist;


    }

}