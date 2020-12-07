package clientServer_Calculation_Client;

import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  
public class Calc_Client 
{ 
    public static void main(String[] args) throws IOException 
    { 
        InetAddress inetAd = InetAddress.getLocalHost(); 
        int portNum = 9000; 
        Scanner sc = new Scanner(System.in); 
  
        // Open the socket connection
        Socket s = new Socket(inetAd, portNum); 
  
        // Get an input and output stream and store them in empty variables 'dis' and 'dos'
        DataInputStream disStream = new DataInputStream(s.getInputStream()); 
        DataOutputStream dosStream = new DataOutputStream(s.getOutputStream()); 
  
        while (true) 
        {      
            System.out.print("Enter your calculation in the order : ");
            // "Example:  4 * 4 "
            System.out.println("'operand operator operand'"); 
            
            String input = sc.nextLine(); 
  
            if (input.equals("bye")) 
                break; 
  
            // Pass the calculation to the server
            dosStream.writeUTF(input); 
  
            // wait till request is processed and sent back to client 
            String ans = disStream.readUTF(); 
            System.out.println("Answer=" + ans); 
        } 
    } 
}
