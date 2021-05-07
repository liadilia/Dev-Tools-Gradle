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


    public LoginForm() throws ClassNotFoundException{

            JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

            f.setTitle("imc Dev Tools");

            f.setJMenuBar(new JMenuBar());
            f.setContentPane(panel);
            //f.pack();
            f.setVisible(true);

        }
    }



