
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import LaunchDevTools.MainMenuForm;

import LaunchDevTools.MainMenu;
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
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}