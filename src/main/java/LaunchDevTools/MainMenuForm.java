package LaunchDevTools;

import DB.UserDAO;
import MetaTagGenerator.Form;
import MetaTagGenerator.MetaTagCreationForm;
//import com.bulenkov.iconloader.IconLoader;
import SystemConfigGenerator.SystemConfigCreationForm;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenuForm {
    private JPanel panel;
    private JLabel title;
    private JButton createNewMetaTag;
    private JButton createSystemConfig;
    private JButton adminLogin;

public MainMenuForm() throws ClassNotFoundException{

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);
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

        System.out.println( UserDAO.getUserName("8"));
        try {
            LoginForm lf = new LoginForm();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

    });
    }


}
