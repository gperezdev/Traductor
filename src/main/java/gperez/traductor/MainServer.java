
package gperez.traductor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gerard
 */
public class MainServer {
    
   public static void main(String[] args){
       try{
           ServerSocket server = new ServerSocket(8888);
           int i=0;
           while(true){
                System.out.println("Esperant resposta del client");
                Socket socket = server.accept();
                System.out.println("Client "+ i + " connectat");
                i++;
                new ThreadClient(socket).start();
           }
       }catch(IOException ex){
           ex.getMessage();
       }
   }
    
}
