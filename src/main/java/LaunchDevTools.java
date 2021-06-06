
import DB.ConfigStringsDAO;
import REST.RestEndpoints;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import LaunchDevTools.WelcomeScreenForm;
import LaunchDevTools.PluginConfigurationStrings;


public class LaunchDevTools extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            WelcomeScreenForm mm = new WelcomeScreenForm();
            PluginConfigurationStrings.jiraIssueRoot = ConfigStringsDAO.getAttributeString(RestEndpoints.JiraIssueRoot);
            PluginConfigurationStrings.propadminAuth= ConfigStringsDAO.getAttributeString(RestEndpoints.propadminAuth);
            PluginConfigurationStrings.jiraProject= ConfigStringsDAO.getAttributeString(RestEndpoints.JiraProject);
            PluginConfigurationStrings.jiraSubtaskId= ConfigStringsDAO.getAttributeString(RestEndpoints.JiraSubtaskId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

