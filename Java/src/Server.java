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

	public static void main(String args[]) throws IOException {
		Receiver receiver = new Receiver(7789, "127.0.0.1", 3306, "", "root", "unwdmi");
		receiver.run();

	}

}