
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import LaunchDevTools.MainMenuForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;


public class LaunchDevTools extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            MainMenuForm mm = new MainMenuForm();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

