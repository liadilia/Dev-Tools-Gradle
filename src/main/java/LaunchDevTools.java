
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import LaunchDevTools.MainMenuForm;
import org.jetbrains.annotations.NotNull;
import LaunchDevTools.WelcomeScreenForm;

import javax.swing.*;
import java.io.IOException;


public class LaunchDevTools extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            WelcomeScreenForm mm = new WelcomeScreenForm();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

