package ClientServer_Calculation_Server;

import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 
  
public class Calc_Server 
{ 
    public static void main(String args[]) throws IOException 
    { 
        //Establish the socket connection. 
        ServerSocket serverSock = new ServerSocket(9000); 
        Socket mySock = serverSock.accept(); 
  
        //Processing the request 
        DataInputStream disStream = new DataInputStream(mySock.getInputStream()); 
        DataOutputStream dosStream = new DataOutputStream(mySock.getOutputStream()); 
  
        while (true) 
        { 
            // Wait for input 
            String input = disStream.readUTF(); 
  
            if(input.equals("bye")) 
                break; 
  
                System.out.println("Calculation received :-" + input); 
            int result; 
  
            // Use StringTokenizer to break the equation into operand and 
            // operation 
            StringTokenizer toke = new StringTokenizer(input); 
  
            int num1 = Integer.parseInt(toke.nextToken()); 
            String operation = toke.nextToken(); 
            int num2 = Integer.parseInt(toke.nextToken()); 
  
            // perform the required operation. 
            if (operation.equals("+")) 
            { 
                result = num1 + num2; 
            } 
  
            else if (operation.equals("-")) 
            { 
                result = num1 - num2; 
            } 
            else if (operation.equals("*")) 
            { 
                result = num1 * num2; 
            } 
            else
            { 
                result = num1 / num2; 
            } 
            System.out.println("Passing result to server..."); 
  
            // Pass the result back to the client. 
            dosStream.writeUTF(Integer.toString(result)); 
        } 
    } 
} 