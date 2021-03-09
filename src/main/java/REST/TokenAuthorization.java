package REST;


import java.net.HttpURLConnection;

public class TokenAuthorization implements Connection.Authorization {
    private String header;

    public TokenAuthorization(String token) {
        header = "Bearer " + token;
    }

    @Override
    public void addAuthorization(HttpURLConnection con) {
        con.setRequestProperty("Authorization", header);

    }
}