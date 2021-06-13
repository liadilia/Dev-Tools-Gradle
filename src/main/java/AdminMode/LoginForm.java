package AdminMode;

import DB.UserDAO;
import LaunchDevTools.CurrentUser;
import LaunchDevTools.CustomSize;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends Component {

    private JButton loginButton;
    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton cancelButton;


    public LoginForm() throws ClassNotFoundException{

            JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setBounds(CustomSize.width / 3, CustomSize.height / 8, 450, 500);


            f.setTitle("imc Dev Tools");

            f.setJMenuBar(new JMenuBar());
            f.setContentPane(panel);
            //f.pack();
            f.setVisible(true);

        loginButton.addActionListener(e -> {

            if( UserDAO.validate(textField1.getText(), String.valueOf(passwordField1.getPassword()))) {
                CurrentUser.role=1;
                this.setVisible(false);
                try {
                    Administration admin = new Administration();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }

            else{
                JOptionPane.showMessageDialog(this, "Incorrect username or password","Login Error!", JOptionPane.ERROR_MESSAGE);
                textField1.setText("");
                passwordField1.setText("");
            }
        });
        }
    }



