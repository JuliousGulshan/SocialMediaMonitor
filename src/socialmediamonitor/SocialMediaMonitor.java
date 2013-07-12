/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediamonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author afonso
 */
public class SocialMediaMonitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        
        HttpURLConnection connection = null;
        OutputStreamWriter wr = null;
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        URL serverAddress = null;
        
        JSONParser parser = new JSONParser();
        
        try {
          serverAddress = new URL("http://www.portinari.org.br/services/item?lang=pt&offset=0&limit=14&_=1373587842029");
          //set up out communications stuff
          connection = null;
        
          //Set up the initial connection
          connection = (HttpURLConnection)serverAddress.openConnection();
          connection.setRequestMethod("GET");
          connection.setDoOutput(true);
          connection.setReadTimeout(10000);
                    
          connection.connect();
        
          //get the output stream writer and write the output to the server
          //not needed in this example
          //wr = new OutputStreamWriter(connection.getOutputStream());
          //wr.write("");
          //wr.flush();
        
          //read the result from the server
          rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          sb = new StringBuilder();
        
          while ((line = rd.readLine()) != null)
          {
              sb.append(line + '\n');
          }
          
          Object obj = parser.parse(sb.toString());
          JSONObject jsonObject =  (JSONObject)obj;
          
            System.out.println(jsonObject.get("artwork_total"));
            System.out.println(jsonObject.get("artworkset_total"));
        
          //System.out.println(sb.toString());
                    
      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (ProtocolException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
      finally
      {
          //close the connection, set all objects to null
          connection.disconnect();
          rd = null;
          sb = null;
          wr = null;
          connection = null;
      } 
    }
}
