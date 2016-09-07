package mx.edu.uttt.feeds;

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
 * Created by qas on 25/08/16.
 */
public class DOMParser {

    private RSSFeed _feed = new RSSFeed();

    public RSSFeed parseXML(String xml){
        URL url = null;

        try {
            url = new URL(xml);
        }catch(MalformedURLException e){
           e.printStackTrace();
        }
//http://www.vidaysalud.com/tema/diario/bebes/feed/
        try{
            DocumentBuilderFactory factory;
            factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(url.openStream()));
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("item");
            int length = list.getLength();

            for (int i = 0; i < length ; i++) {
                Node currentNode = list.item(i);
                RSSItem item = new RSSItem();

                NodeList childNodes = currentNode.getChildNodes();
                int childLength = childNodes.getLength();

                for (int j = 0; j < childLength; j++) {
                    Node thisNode = childNodes.item(j);
                    String theString = null;
                    String nodeName = thisNode.getNodeName();

                    theString = childNodes.item(j).getFirstChild().getNodeValue();

                    if(theString!=null){
                        if("title".equals(nodeName)){
                            item.set_title(theString);
                        }else if("description".equals(nodeName)){
                            item.set_description(theString);

                            String html = theString;

                            org.jsoup.nodes.Document
                                    docHtml = Jsoup.parse(html);

                            Elements img = docHtml.select("img");
                            item.set_image(img.attr("src"));
                        } else if("pubDate".equals(nodeName)){
                            String formatedDate = theString.replace(" +0000", "");
                            item.set_date(formatedDate);
                        } else if("content:encoded".equals(nodeName)){
                            item.set_content(theString);
                        }
                     }
                }

                _feed.add_item(item);

            }

        }catch (Exception e){

        }
        return _feed;
    }

}
