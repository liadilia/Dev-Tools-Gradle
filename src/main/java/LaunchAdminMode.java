import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import AdminMode.LoginForm;


public class LaunchAdminMode extends AnAction {


        @Override
        public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
            try {
                LoginForm lf = new LoginForm();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }




