import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static void main(String args[]) throws IOException {
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
			
			//read input data
			while (!str.contains("</MEASUREMENT>")) {
				str = br.readLine();
				array.add(str);
			}			
			
			//close socket for new connection
			socket.close();
			
			//write data to tekstfile from array
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			    for (String data : array){
			    	bw.write(data);
			    }
			    bw.newLine();
			}
			
			System.out.println(array);
			
			array.clear();
		}
	}
}