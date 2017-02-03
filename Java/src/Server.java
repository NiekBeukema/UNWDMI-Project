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
		Receiver receiver = new Receiver(7789, "145.33.225.143", 3306, "zOlBAimnx9LlGsUw", "weathergen", "unwdmi");
		receiver.run();


		//Allow averages of all stations to be calculated and inserted at interval.
		averageThread avg = new averageThread();
		avg.run();

	}

}