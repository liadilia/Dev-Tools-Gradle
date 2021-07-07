import AdminMode.LoginForm;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import LaunchDevTools.PluginConfigurationStrings;


public class LaunchAdminMode extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            new Thread(new PluginConfigurationStrings()).start();
            LoginForm lf = new LoginForm();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}




