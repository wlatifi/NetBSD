import java.io.*;
import java.net.*;
import java.util.*;

public class server{
	public static void main(String[] args) throws Exception {

	int iportno = 5000;
	String sportno = "5000";

	System.out.println("\n\nCHATAPP - Server " + "\n" +
			"Use default Port (Y/N)? ");

	Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

	if(answer.equals("n")){
		System.out.println("Please insert new Port number : ");
		sportno = scanner.nextLine();
		iportno = Integer.parseInt(sportno);
	}

	System.out.println("\n\nStarting server on port "+sportno +"\n");

	ServerSocket sersock =  new ServerSocket(iportno);
	System.out.println("Server ready for chatting\n");
	Socket sock = sersock.accept();

	String clientIP = sock.getInetAddress().getHostName();
	String clientHN = sock.getInetAddress().getCanonicalHostName();

	System.out.println("Connected to : " + clientHN + " " + clientIP);
	
	BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

	OutputStream ostream = sock.getOutputStream();
	PrintWriter pwrite = new PrintWriter(ostream, true);

	InputStream istream = sock.getInputStream();
	BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

	String rMessage, sMessage;

	while(true){

		sMessage = keyRead.readLine();
		if(sMessage.equals("gclient")){
			System.out.println("Client IP: " + clientIP);
			pwrite.println("");
			pwrite.flush();
		}else{
			pwrite.println(sMessage);
			pwrite.flush();
		}

		if((rMessage = receiveRead.readLine()) != null){
			System.out.println("Client :"+rMessage);}
		//sMessage = keyRead.readLine();
		//pwrite.println(sMessage);
		//pwrite.flush();
		}
	}
}
