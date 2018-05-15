import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class TimeClient{

  public static void main(String[] args) throws Exception {

  	/*Declaring Vars*/
    int iportno = 5000;
    String socketip = "0.0.0.0";  
    String sportno = "5000";
    String liner = "------------------------------------";
    String rMessage, sMessage;
    Socket sock;

    /*Setting up calendar to get time.*/
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm");
    
    System.out.println("\n\n"+ liner + "\n" +
      "XALER Time - Client " + "\n" +
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

    /*Connecting to server*/
    try{
      sock =  new Socket (socketip,iportno); 
      System.out.println("\nConnected!\n");
    }catch(Exception e){
      sock =  new Socket (socketip,iportno); 
      System.out.println("Cant Connect!");
    }
    
    /*Declaring IOstream*/
    BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
    
    OutputStream ostream = sock.getOutputStream();
    PrintWriter pwrite = new PrintWriter(ostream, true);
    
    InputStream istream = sock.getInputStream();
    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
    
    
    /*Get time and sending it to server*/
    double timeOfDay = dateFormat.format(cal.getTime());
    //int timeOfDay = cal.get(Calendar.HOUR_OF_DAY); <-- use this to get hour of day!
    String stod = Double.toString(timeOfDay);
    pwrite.println(stod);
    pwrite.flush();

    /*Listening for response from server*/
    while(true){
      if((rMessage = receiveRead.readLine()) != null){
          System.out.println("Server : "+rMessage);
      }
    }
  }
}