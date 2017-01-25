/**
 * Created by RonOS on 12/25/2016.
 */

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.sql.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XmlWriter {

    public String title;
    public String filepath;
    public Document doc;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;

    public XmlWriter(String title, String filepath) {
        this.title = title;
        this.filepath = filepath;
        try {
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
            doc = null;

        }

        catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public void stationDataToXml(int stationId) {
        try {

            if(doc == null) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("WEATHERDATA");
                doc.appendChild(rootElement);

                //Station data element
                Element station = doc.createElement("STATIONDATA");
                Element measurement = doc.createElement("MEASUREMENT");
                Element date = doc.createElement("DATE");
                Element time = doc.createElement("TIME");
                Element temp = doc.createElement("TEMP");
                Element dewpoint = doc.createElement("DEWP");
                Element stp = doc.createElement("STP");
                Element slp = doc.createElement("SLP");
                Element visibility = doc.createElement("VISIB");
                Element windspeed = doc.createElement("WDSP");
                Element pressure = doc.createElement("PRCP");
                Element snowdepth = doc.createElement("SNDP");
                Element frshtt = doc.createElement("FRSHTT");
                Element cloudiness = doc.createElement("CLDC");
                Element winddirection = doc.createElement("WNDDIR");

                rootElement.appendChild(station);



            }

            else {
                System.out.println("This XmlWriter already has a document assigned to it.");
                System.out.println("Purge the current document to add a new one");

            }



        } catch (Exception pce) {
            pce.printStackTrace();
        }
    }

    public void purgeDocument() {
        doc = null;
    }
}
