import java.io.*;
import java.net.*;
import java.util.*;

public class TimeServer {

  public static void main(String[] args) throws Exception {

  	/*Declaring vars*/
  	double time = 1;
    int iportno = 5000;
    String sportno = "5000";
    String liner = "------------------------------------";
	String rMessage, sMessage;

    System.out.println("\n\n"+ liner + "\n" +
      "XALER Time - Server " + "\n" +
      liner + "\n\n" +
      "Use default Port (Y/N)? ");

    Scanner scanner = new Scanner(System.in);
    String answer = scanner.nextLine();

    if(answer.equals("n") || answer.equals("N")){
      System.out.println("Please insert new Port number : ");
      sportno = scanner.nextLine();
      iportno = Integer.parseInt(sportno);
    }

    System.out.println("\n" + liner + "\n" +
      "Starting server on port "+sportno +"\n" +
      liner + "\n");

    /*Starting serverup*/
    ServerSocket sersock =  new ServerSocket(iportno);

    System.out.println("\n" + liner + "\n" +
      "Server is ready. \n" +
      liner + "\n");

    /*Accepting connection*/
    Socket sock = sersock.accept();
    
    /*Client Info*/
    String clientIP = sock.getInetAddress().getHostName();
    String clientHN = sock.getInetAddress().getCanonicalHostName();
    String clientPort = ""+sock.getLocalPort();
    
    System.out.println("\n" + liner + "\n" +
      "Connected to : "+clientHN+" | "+clientIP + "\n" +
      liner + "\n");

    /*Declaring IOstream*/
    BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
    
    OutputStream ostream = sock.getOutputStream(); 
    PrintWriter pwrite = new PrintWriter(ostream, true);
    
    InputStream istream = sock.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

    /*Listening for respondse from clients*/
    while(true){

    	if((rMessage = receiveRead.readLine()) != null){
    		time = Double.parseDouble(rMessage);	
          	System.out.println("Client : "+rMessage );
          	pwrite.println(getTimeOfDay(time));
          	pwrite.flush();
        }

        sMessage = keyRead.readLine();

        /*!!Not req, function ingetting the client info!!*/
      	if(sMessage.equals("gclient")){
      		System.out.println("\n" + liner + "\n" +
          		"Client IP : " + clientIP + "\n" +
          		"Client Hostname : " + clientHN + "\n" +
          		"Client Port Number : " + clientPort + "\n" +
          		liner + "\n");
      		pwrite.println("");
        	pwrite.flush();
        /*------------------------------------------------*/
        }else{
        	pwrite.println(sMessage);
        	pwrite.flush();
      	}
      }
  }

  /*Method in replying the time*/
  public static String getTimeOfDay(double timeOfDay){

  	if(timeOfDay >= 0 && timeOfDay < 12){
    	return "Good Morning";        
	}else if(timeOfDay >= 12 && timeOfDay < 16){
    	return "Good Afternoon";
	}else if(timeOfDay >= 16 && timeOfDay < 21){
    	return "Good Evening";
	}else if(timeOfDay >= 21 && timeOfDay < 24){
    	return "Good Night";
	}else{
		return "Good Day";
	}
  }
}