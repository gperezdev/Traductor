package gperez.traductor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerard
 */
public class MainClient {
    
    public static void main(String[] args){
        try{
            Socket socket = new Socket("localhost", 8888);
            Scanner lector = new Scanner(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            
            Scanner lectorTeclat = new Scanner(System.in);
            System.out.println("Introdueixi la paraula a traduïr o EXIT per sortir: ");
            
            while(lector.hasNext()){
                String missatge = lector.nextLine();
                String splitMissatge[] = missatge.split("//");
                
                for (String splitMissatge1 : splitMissatge) {
                    System.out.println(splitMissatge1);
                }
                if(missatge.contains("EXIT")){
                    System.exit(0);
                }else{
                    writer.println(lectorTeclat.nextLine());
                }
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
