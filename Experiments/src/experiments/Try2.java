package experiments;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Ivan
 */
public class Try2 {

    public static void main(String[] args) throws MalformedURLException {
        
        URL url = new URL("https://graph.facebook.com/search?q=java&type=post");
        try (InputStream is = url.openStream();
                JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                System.out.print(result.getJsonObject("from").getString("name"));
                System.out.print(": ");
                System.out.println(result.getString("message", ""));
                System.out.println("-----------");
            }
        } catch (IOException e) {
            
        }
    }
}
