import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by RonOS on 1/29/2017.
 */
public class Receiver extends Thread {

    private ServerSocket serverSocket;
    private int counter = 0;
    private int port;

    private Vector<clientThread> clients = new Vector<clientThread>();


    /**
     * The receiver object is responsible for handling all the incoming connections and logging the existing connections.
     * @param port The portnumber to listen to
     * @param databaseIp The IP-adres of the database to write to
     * @param databasePort The portnumber of the database to write to
     * @param databaseUsername The username for the databse
     * @param databasePassword The password for the database
     * @param databaseName The name of database to write to
     */
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

    /**
     * This starts the thread and starts the listering proces
     */
    public void run() {
        listen();
    }

    /**
     * This starts listening on the serversocket in order to handle incoming connections
     */
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
