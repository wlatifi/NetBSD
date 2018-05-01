import java.io.*;
import java.net.*;
import java.util.*;

public class client{
public static void main(String[] args) throws Exception {
	
	String socketip = "0.0.0.0";  


	System.out.println("CHATAPP - Client " + "\n" +			
			"Use default IP (Y/N)? ");

	Scanner scanner = new Scanner(System.in);
	String answer = scanner.nextLine();

	if(answer.equals("n")) {
		System.out.println("Please insert new ip : ");
		socketip = scanner.nextLine();
	}

	System.out.println("Please insert port number : ");
	String sportno = scanner.nextLine();
	int iportno = Integer.parseInt(sportno);

	System.out.println("\n\n" + 
			"Connecting to : " +socketip + " using port " +sportno);
	
	Socket sock =  new Socket (socketip,iportno);

	System.out.println("\nConnected");


	BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

	OutputStream ostream = sock.getOutputStream();
	PrintWriter pwrite = new PrintWriter(ostream, true);

	InputStream istream = sock.getInputStream();
	BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

	System.out.println("Connected");
	String rMessage, sMessage;

	while(true){
		if((rMessage = receiveRead.readLine()) != null){
			System.out.println("Server :"+rMessage);}
		sMessage = keyRead.readLine();
		pwrite.println(sMessage);
		pwrite.flush();
		}
	}
}
