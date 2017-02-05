import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class Server {


    /**
     * The main method for the program
     * @param args
     * @throws IOException
     */
	public static void main(String args[]) throws IOException {
		Receiver receiver = new Receiver(7789, "jmdr.koekjesclan.nl", 3306, "j#Mo3deR!1", "root", "unwdmi");
		receiver.run();

		//Allow averages of all stations to be calculated and inserted at interval.
		averageThread avg = new averageThread();
		avg.run();
	}
}