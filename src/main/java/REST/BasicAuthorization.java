package REST;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuthorization implements Connection.Authorization {
    private final String header;

    public BasicAuthorization(String user, String pass) {
        header = "Basic " + Base64.getEncoder().encodeToString((user+":"+pass).getBytes(StandardCharsets.UTF_8));
    }
    @Override
    public void addAuthorization(HttpURLConnection con) {
        con.setRequestProperty("Authorization", header);
    }
}


