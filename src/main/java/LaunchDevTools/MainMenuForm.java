package LaunchDevTools;

import AdminMode.LoginForm;
import DB.UserDAO;
import MetaTagGenerator.MetaTagCreationForm;
import SystemConfigGenerator.SystemConfigCreationForm;

import javax.swing.*;

public class MainMenuForm {
    private JPanel panel;
    private JLabel title;
    private JButton createNewMetaTag;
    private JButton createSystemConfig;
    private JButton adminLogin;

    public MainMenuForm() throws ClassNotFoundException {

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 450, 500);
        jf.setTitle("imc Dev Tools");
        jf.setContentPane(panel);
        jf.setVisible(true);


        createNewMetaTag.addActionListener(e -> {
            MetaTagCreationForm newForm = new MetaTagCreationForm();
            jf.dispose();

        });
        createSystemConfig.addActionListener(e -> {
            SystemConfigCreationForm newForm = new SystemConfigCreationForm();
            jf.dispose();
        });

        adminLogin.addActionListener(e -> {

            System.out.println(UserDAO.getUserName("1"));
            try {
                LoginForm lf = new LoginForm();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

        });
    }


}
