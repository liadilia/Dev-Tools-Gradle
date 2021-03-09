package REST;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Connection {


    public  enum Method {
        GET, PUT, POST
    }
    interface Authorization {
        void addAuthorization(HttpURLConnection connection);
    }
     private JsonObject responseObject = null;
     private int code= -1;

    public Connection(String stringUrl, Method method, String payload, Authorization auth ) throws IOException {
        URL url = new URL (stringUrl);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod(String.valueOf(method));
        if (auth != null) {
            auth.addAuthorization(con);
        }
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Connection", "keep-alive");
        con.setDoOutput(true);
        String jsonInputString = payload;

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        //    System.out.println(response.toString());

            String json = response.toString();
            JsonObject convertedObject = new Gson().fromJson(json, JsonObject.class);
            this.code = con.getResponseCode();

          this.responseObject= convertedObject;

        }
    }

    public JsonObject getResponseObject (){
        return this.responseObject;
    }
    public int getResponseCode (){
        return this.code;
    }
}
