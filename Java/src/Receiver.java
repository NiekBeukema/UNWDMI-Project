import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RonOS on 1/29/2017.
 */
public class Receiver {

    private int port;
    private boolean receiving = false;
    private boolean writing = false;
    private MySql sql;
    private WeatherDatabaseHelper database;
    private ServerSocket serverSocket;
    private Socket socket;
    private String outputPath = "Java\\src\\testfile.txt";

    public Receiver(int port,String databaseIp, int databasePort, String databaseUsername, String databasePassword, String databaseName) {
        sql = new MySql(databaseIp, databasePort, databaseName, databaseUsername, databasePassword);
        database = new WeatherDatabaseHelper(sql);
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        }

        catch (IOException ex) {
            System.out.println("An error occured creating the server socket for the receiver: ");
            System.out.println(ex.toString());
        }

    }

    public void receive() {
        if (!receiving) {
            receiving = true;
            try {
                System.out.println("Receiver listening on: " + port);
                socket = serverSocket.accept();
                while (receiving) {
                    //accept socket connection
                    Socket socket = serverSocket.accept();
                    String str = "";
                    //get inputstream
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    //read input data
                    while (!str.contains("</MEASUREMENT>")) {
                        str = br.readLine();

                        builder.append(str);
                    }

                    //close socket for new connection
                    socket.close();

                    //write data to tekstfile from array
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath, true))) {
                        bw.write(builder.toString());
                        bw.newLine();
                    } catch (java.io.IOException ex) {

                    }

                    //System.out.println(array);

                    builder.append("</WEATHERDATA>");
                    String xmlString = builder.toString();
                    xmlString = xmlString.replace("\t", "");
                    Document xml = loadXMLFromString(xmlString);
                    NodeList nList = xml.getElementsByTagName("MEASUREMENT");
                    for (int i = 0; i < nList.getLength(); i++) {
                        Element element = (Element) nList.item(i);
                        int station = Integer.valueOf(element.getElementsByTagName("STN").item(0).getTextContent());
                        String date = element.getElementsByTagName("DATE").item(0).getTextContent();
                        String time = element.getElementsByTagName("TIME").item(0).getTextContent();
                        Float temperature = Float.parseFloat(element.getElementsByTagName("TEMP").item(0).getTextContent().replace("\"", ""));
                        Float dewpoint = Float.parseFloat(element.getElementsByTagName("DEWP").item(0).getTextContent().replace("\"", ""));
                        Float pressure = Float.parseFloat(element.getElementsByTagName("STP").item(0).getTextContent().replace("\"", ""));
                        String slp = element.getElementsByTagName("SLP").item(0).getTextContent();
                        Float visibility = Float.parseFloat(element.getElementsByTagName("VISIB").item(0).getTextContent().replace("\"", ""));
                        Float windspeed = Float.parseFloat(element.getElementsByTagName("WDSP").item(0).getTextContent().replace("\"", ""));
                        Float prcp = Float.parseFloat(element.getElementsByTagName("PRCP").item(0).getTextContent());
                        Float snowdepth = Float.parseFloat(element.getElementsByTagName("SNDP").item(0).getTextContent());
                        int frshtt = Integer.valueOf(element.getElementsByTagName("FRSHTT").item(0).getTextContent());
                        Float cloudcoverage = Float.parseFloat(element.getElementsByTagName("CLDC").item(0).getTextContent());
                        int winddirection = Integer.valueOf(element.getElementsByTagName("WNDDIR").item(0).getTextContent());

                        if(writing) {
                            if (station < 500000) {
                                System.out.println("Station number: " + station);
                                System.out.println("Cloudcoverage : " + cloudcoverage);
                                //database.insertOceaniaData(station, cloudcoverage);
                            } else {
                                //database.insertArgentinaData(station, cloudcoverage, visibility);
                            }
                        }

                        System.out.println(date);
                    }


                }
            } catch (IOException ex) {
                System.out.println("An error occured creating the socket for the receiver: ");
                System.out.println(ex.toString());
            }

            catch (java.lang.Exception ex) {
                System.out.println("An error occured loading XML from the network stream: ");
                System.out.println(ex.toString());
            }
        }
    }

    public void write() {
        if (receiving && !writing) {
            writing = true;
        }

        else {
            System.out.println("The receiver does not have any data to write or is already writing");
        }
    }

    public void stopReceiving() {
        if(receiving) {
            receiving = false;
            writing = false;
        }

        else {
            System.out.println("The receiver is already idle");
        }
    }

    public void stopWriting() {
        if (writing) {
            writing = false;
        }

        else {
            System.out.println("The receiver has already stopped writing");
        }
    }

    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    public boolean isReceiving() {
        return receiving;
    }

    public boolean isWriting() {
        return writing;
    }
}
