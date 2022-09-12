package gperez.traductor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gerard
 */
public class ThreadClient extends Thread {
    
    private Socket client;
    private Scanner lector;
    private PrintWriter writer;

    public ThreadClient(Socket client){
        try{
            this.client = client;
            this.lector = new Scanner(client.getInputStream());
            this.writer = new PrintWriter(client.getOutputStream(), true);
        }catch(IOException ex){
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Override
    public void run(){
        String missatge;
        SoftCatalaClient softCatala = new SoftCatalaClient();
        String traduccio;
        
        try{
            writer.println("Conectat al servidor");
            while(lector.hasNext()){
                missatge = lector.nextLine();
                traduccio = softCatala.translate(missatge);
                System.out.println("Traducció rebuda: " + traduccio);
                if(missatge.contains("EXIT")){
                    writer.println("Tancat");
                    break;
                }else{
                    writer.println("Traducció: " + traduccio);
                } 
            }
            client.close();
        }catch (IOException ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
