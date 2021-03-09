package REST.Helpers;


import LaunchDevTools.CurrentUser;
import REST.Bundle;
import REST.Connection;
import REST.RestEndpoints;
import REST.TokenAuthorization;
import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;

public class PropadminRequestHelper {


    public static class Auth {
        String userName;
        String password;

        public Auth(String user, String pass){
            this.userName=user;
            this.password=pass;
        }
    }

 public static String getBundleCreationRequestBody(String bundleKey, String BundleTextInEnglish, String context){

     Bundle bundle = new Bundle (bundleKey,context,CurrentUser.email );
     bundle.addTranslation(new Bundle.Translations("English","en-GB",BundleTextInEnglish ));
     bundle.addTranslation(new Bundle.Translations("German", "de-DE",""));
     String payload= new Gson().toJson(bundle);
     return payload;
    }

    public static String getAuthPayload (){
        Auth auth = new Auth(RestEndpoints.JIRA_USER,RestEndpoints.JIRA_PASS);
       String x= new Gson().toJson(auth);
       return x;
    }


    public static void CreatePropadminBundles(String context, String bundleKey, String bundleText){
        try {
            Connection propadminAuth = new Connection(RestEndpoints.PROPADMIN_AUTH, Connection.Method.POST, PropadminRequestHelper.getAuthPayload(), null);
            // fetch authorization token
            String token = propadminAuth.getResponseObject().get("token").getAsString();
            // compose the body for the call to create the bundles
            String payload = PropadminRequestHelper.getBundleCreationRequestBody(bundleKey,bundleText, context);
            // POST request to create the Propadmin bundles
            Connection createBundle= new Connection(RestEndpoints.PROPADMIN_CREATE_BUNDLE, Connection.Method.POST, payload, new TokenAuthorization(token));
            //check the response code for both requests in case any error occurred
            if (createBundle.getResponseCode()<299){
                JOptionPane.showMessageDialog(null, "Bundles successfully created");
            }
            else {
                JOptionPane.showMessageDialog(null, "The bundle could not be created");
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }



    }

}
