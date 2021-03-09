package LaunchDevTools;

import MetaTagGenerator.Form;
import MetaTagGenerator.MetaTagCreationForm;
//import com.bulenkov.iconloader.IconLoader;
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
    private JButton button2;
    private JButton adminLogin;

public MainMenuForm() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

        // UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme");
    //    UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme");

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
 //  Image icon = (Image)IconLoader.getIcon("/resources/META-INF/imc_logo.png");
 //       jf.setIconImage(icon);

        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);


        createNewMetaTag.addActionListener(e -> {
            MetaTagCreationForm newForm = new MetaTagCreationForm();
            jf.dispose();
        });

    }


}
