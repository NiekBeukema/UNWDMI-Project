import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by RonOS on 1/29/2017.
 */
public class Receiver extends Thread {

    private ServerSocket serverSocket;
    private int counter = 0;
    private int port;

    private Vector<clientThread> clients = new Vector<clientThread>();



    public Receiver(int port,String databaseIp, int databasePort, String databaseUsername, String databasePassword, String databaseName) {
        MySql sql = new MySql(databaseIp, databasePort, databaseName, databaseUsername, databasePassword);
        WeatherDatabaseHelper database = new WeatherDatabaseHelper(sql);
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        }

        catch (IOException ex) {
            System.out.println("An error occured creating the server socket for the receiver: ");
            System.out.println(ex.toString() + "@Ln" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }

    public void run() {
        listen();
    }

    public void listen() {
        new Thread("Listening Thread") {

            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();

                        clientThread newClient = new clientThread(socket);
                        newClient.start();
                        clients.addElement(newClient);
                        System.out.println("Connected Clusters: " + clients.capacity());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


}
