package LaunchDevTools;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class LoginForm {

    private JButton okButton;
    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JCheckBox rememberMeCheckBox;
    private JButton cancelButton;


    public static void main(String[] args) {
        try {

        //    UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme");
         //   UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme");
            UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme");

         //   UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme");

         //  UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme");
       //     UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme");

            LoginForm tlf = new LoginForm();

            JFrame f = new JFrame();


            f.setTitle("Zuzu");

            f.setJMenuBar(new JMenuBar());
            f.setContentPane(tlf.panel);
            f.pack();
            f.setVisible(true);

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }


}
