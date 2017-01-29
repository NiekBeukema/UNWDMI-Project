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
		MySql sql = new MySql("127.0.0.1", 3306, "unwdmi", "root", "vtl54711");
		WeatherDatabaseHelper database = new WeatherDatabaseHelper(sql);
		final int portNumber = 7789;
		String filename = "Java\\src\\testfile.txt";
		ArrayList<String> array = new ArrayList<String>();
		
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);

		while (true) {
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
				array.add(str);
			}			
			
			//close socket for new connection
			socket.close();
			
			//write data to tekstfile from array
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			    bw.write(builder.toString());
			    bw.newLine();
			}
			
			//System.out.println(array);
			try {
				builder.append("</WEATHERDATA>");
				String xmlString = builder.toString();
				xmlString = xmlString.replace("\t", "");
				Document xml = loadXMLFromString(xmlString);
				NodeList nList = xml.getElementsByTagName("MEASUREMENT");
				for(int i=0; i < nList.getLength(); i++) {
					Element element = (Element) nList.item(i);
					int station = Integer.valueOf(element.getElementsByTagName("STN").item(0).getTextContent());
					String date = element.getElementsByTagName("DATE").item(0).getTextContent();
					String time = element.getElementsByTagName("TIME").item(0).getTextContent();
					Float temperature = Float.valueOf(element.getElementsByTagName("TEMP").item(0).getTextContent());
					Float dewpoint = Float.valueOf(element.getElementsByTagName("DEWP").item(0).getTextContent());
					Float pressure = Float.valueOf(element.getElementsByTagName("STP").item(0).getTextContent());
					String slp = element.getElementsByTagName("SLP").item(0).getTextContent();
					int visibility = Integer.valueOf(element.getElementsByTagName("VISIB").item(0).getTextContent());
					Float windspeed = Float.valueOf(element.getElementsByTagName("WDSP").item(0).getTextContent());
					String prcp = element.getElementsByTagName("PRCP").item(0).getTextContent();
					String snowdepth = element.getElementsByTagName("SNDP").item(0).getTextContent();
					String frshtt = element.getElementsByTagName("FRSHTT").item(0).getTextContent();
					String cloudcoverage = element.getElementsByTagName("CLDC").item(0).getTextContent();
					String winddirection = element.getElementsByTagName("WNDDIR").item(0).getTextContent();

					database.insertWeahterData();
					System.out.println(date);
				}
			}

			catch (java.lang.Exception ex) {
				System.out.println(ex.toString());
			}
		}
	}

	public static Document loadXMLFromString(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}
}