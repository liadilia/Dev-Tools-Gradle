package LaunchDevTools;

import DB.ConfigStringsDAO;
import REST.RestEndpoints;

import javax.swing.*;

public class PluginConfigurationStrings implements Runnable{
    public static String jiraIssueRoot = "";
    public static String propadminAuth="";
    public static String jiraProject ="";
    public static String jiraSubtaskId ="";
    public static String propadminBundle ="";

    @Override
    public void run() {
        try{
            jiraIssueRoot = ConfigStringsDAO.getAttributeString(RestEndpoints.JiraIssueRoot);
            propadminAuth= ConfigStringsDAO.getAttributeString(RestEndpoints.propadminAuth);
            jiraProject= ConfigStringsDAO.getAttributeString(RestEndpoints.JiraProject);
            jiraSubtaskId= ConfigStringsDAO.getAttributeString(RestEndpoints.JiraSubtaskId);
            propadminBundle= ConfigStringsDAO.getAttributeString(RestEndpoints.propadminCreateBundleEndpoint)+"?user=";
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Could not load one or more settings. You might be asked to provide these values manually.");
        }

    }
}
