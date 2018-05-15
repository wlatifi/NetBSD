import java.io.*;
import java.net.*;
import java.util.*;

public class chatServer{

  public static void main(String[] args) throws Exception{

    int iportno = 5000;
    String sportno = "5000";
    String liner = "------------------------------------";
    String rMessage, sMessage;

    System.out.println("\n\n"+ liner + "\n" +
      "XALER CHATAPP - Server " + "\n" +
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

    ServerSocket sersock =  new ServerSocket(iportno);

    System.out.println("\n" + liner + "\n" +
      "Server ready for chatting\n" +
      liner + "\n");

    Socket sock = sersock.accept();
    
    String clientIP = sock.getInetAddress().getHostName();
    String clientHN = sock.getInetAddress().getCanonicalHostName();
    String clientPort = ""+sock.getLocalPort();
    
    System.out.println("\n" + liner + "\n" +
      "Connected to : "+clientHN+" | "+clientIP + "\n" +
      liner + "\n\n" +
      "type in gclient to get client info");

    BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
    
    OutputStream ostream = sock.getOutputStream(); 
    PrintWriter pwrite = new PrintWriter(ostream, true);
    
    InputStream istream = sock.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));


    while(true){

      if((rMessage = receiveRead.readLine()) != null){
        System.out.println("Client :"+rMessage);

        if(rMessage.equalsIgnoreCase("Assalamualaikum")){
          pwrite.println("Waalaikumusalam");
          pwrite.flush();  
        }else{
          pwrite.println("Are you a Muslim?");
          pwrite.flush(); 
        }
      }
    }
  }
}