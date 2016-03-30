package mx.edu.utng.xmlreader.parser;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by qas on 15/03/16.
 */

public class DOMParser {

    private RSSFeed feed = new RSSFeed();

    public RSSFeed parseXml(String xml){

        URL url = null;
        try {
            url = new URL(xml);
        }catch (MalformedURLException e){
            Log.e("URL Malformed", e.toString());
        }

        try{
          //Declarando instancias requeridas
            DocumentBuilderFactory dbf;
            dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            //Parsear el xml
            Document document = db.parse(
                    new InputSource(url.openStream()));
            document.getDocumentElement().normalize();

            //Obteniendo todos los items
            NodeList nodeList = document.getElementsByTagName("item");
            int length = nodeList.getLength();

            for (int i = 0; i<length; i++){
                Node currentNode = nodeList.item(i);
                RSSItem item = new RSSItem();
                NodeList nChild = currentNode.getChildNodes();
                int cLength = nChild.getLength();

                //Obtener los elementos de cada item.
                for (int j = 1; j < cLength; j=j+2) {
                    Node thisNode = nChild.item(j);
                    String strNode = null;
                    String nodeName = thisNode.getNodeName();
                    strNode = nChild.item(j).getFirstChild().getNodeValue();

                    if(strNode!=null){
                        if(nodeName.equals("title")){
                            item.setTitle(strNode);
                        }else  if(nodeName.equals("description")){
                            item.setDescription(strNode);
                            //Parsear description en xml para obtener la ruta de la imagen
                            org.jsoup.nodes.Document documentXML = Jsoup.parse(strNode);
                            Elements imagen = documentXML.select("img");
                            item.setImage(imagen.attr("src"));
                        }else if(nodeName.equals("pubDate")){
                            //Remplazar el signo + y los ceros por cadena vacia
                            String formatedDate = strNode.replace(" +0000",
                                    "");
                            item.setDate(formatedDate);
                        }
                    }
                }
                feed.addItem(item);
            }

        }catch (Exception e){
            Log.e("Error al parsear XML", e.toString());
        }
        return feed;
    }

}
