
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
        new Thread(new PluginConfigurationStrings()).start();
        try {
            WelcomeScreenForm mm = new WelcomeScreenForm();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

