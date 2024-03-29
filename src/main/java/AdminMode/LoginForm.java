package AdminMode;

import DB.UserDAO;
import LaunchDevTools.CurrentUser;
import LaunchDevTools.CustomSize;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends Component {

    private JButton loginButton;
    private JPanel panel;
    private JTextField textField1;
    private JPasswordField passwordField1;


    public LoginForm() throws ClassNotFoundException {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setBounds(CustomSize.width / 3, CustomSize.height / 8, 450, 500);


        f.setTitle("imc Dev Tools");

        f.setJMenuBar(new JMenuBar());
        f.setContentPane(panel);
        //f.pack();
        f.setVisible(true);

        loginButton.addActionListener(e -> {

            if (UserDAO.validate(textField1.getText(), String.valueOf(passwordField1.getPassword()))) {
                CurrentUser.role = 1;
                this.setVisible(false);
                try {
                    Administration admin = new Administration();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                f.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username or password", "Login Error!", JOptionPane.ERROR_MESSAGE);
                textField1.setText("");
                passwordField1.setText("");
            }
        });
    }
}



