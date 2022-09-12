package gperez.traductor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Gerard
 */
public class SoftCatalaClient {
    
    public String translate(String paraula){
        
        try{
            String url = "https://www.softcatala.org/diccionari-multilingue/paraula/";
            url = url+paraula;
            
            Elements fills=parsejador(url);
            String resultat = "";
            for(Element el : fills){
                resultat+=el.text() + " // ";
            }
            return resultat;
           
        }catch(Exception ex){
            System.out.println("Error");
            return null;
        }
    }
    
    public Elements parsejador(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        Element element = doc.getElementsByClass("col-sm-8 multilingue_list").first();
        Element ul = element.children().get(0);
        Elements fills = ul.children();
        
        return fills;
    }
    
}
