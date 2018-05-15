import java.io.*;
import java.net.*;
import java.util.*;

public class chatClient{

  public static void main(String[] args) throws Exception {

    int iportno = 5000;
    String socketip = "0.0.0.0";  
    String sportno = "5000";
    String liner = "------------------------------------";
    Socket sock;
    String rMessage, sMessage;
    
    System.out.println("\n\n"+ liner + "\n" +
      "XALER CHATAPP - Client " + "\n" +
      liner + "\n\n" +
      "Use default Port (Y/N)?");

    Scanner scanner = new Scanner(System.in);
    String answer = scanner.nextLine();

    if(answer.equals("n") || answer.equals("N")){
      System.out.println("Please insert new Port number : ");
      sportno = scanner.nextLine();
      iportno = Integer.parseInt(sportno);
    }

    System.out.println("Please insert IP Address : ");
    socketip = scanner.nextLine();

    System.out.println("\n\n" + liner +"\n" + 
      "Connecting to : " +socketip + " using port " +sportno + "\n" +
      liner + "\n");
    try{
      sock =  new Socket (socketip,iportno); 
      System.out.println("\nConnected");
    }catch(Exception e){
      sock =  new Socket (socketip,iportno); 
      System.out.println("Cant Connect!");
    }

    BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
    
    OutputStream ostream = sock.getOutputStream();
    PrintWriter pwrite = new PrintWriter(ostream, true);
    
    InputStream istream = sock.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
    
    while(true){

      sMessage = keyRead.readLine();
        pwrite.println(sMessage);
        pwrite.flush();

    	if((rMessage = receiveRead.readLine()) != null){
    		System.out.println("Server :"+rMessage);
    	}
    	
    }
  }
}