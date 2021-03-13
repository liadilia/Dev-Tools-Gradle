package SystemConfigGenerator;

import LaunchDevTools.CustomSize;
import PSIHelpers.PSIHelper;
import Templates.BaseClassTemplate;
import Templates.ConfigDAOClassTemplate;
import Templates.SystemConfigFactoryTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SystemConfigCreationForm {
    private JTextField textField1;
    private JButton addSectionButton;
    private JComboBox comboBox1;
    private JTextField optionTitle;
    private JButton addToListOfButton;
    private JComboBox optionType;
    private JPanel panel;
    private JTextField typeTextField;
    private List<ConfigOption> options = new ArrayList<>();


    public SystemConfigCreationForm(){

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
        //  Image icon = (Image)IconLoader.getIcon("/resources/META-INF/imc_logo.png");
        //       jf.setIconImage(icon);

        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);

        addToListOfButton.addActionListener(e -> {
        String title =  optionTitle.getText();
        ConfigOption.ConfigType type =ConfigOption.ConfigType.valueOf(typeTextField.getText());
        ConfigOption configOption = new ConfigOption(title, type);
        options.add(configOption);
            JOptionPane.showMessageDialog(jf, "Option stored. Add all options before creating the config");
        optionTitle.setText("");
        typeTextField.setText("");
        });


        addSectionButton.addActionListener(e -> {
            Project p = ProjectManager.getInstance().getDefaultProject();
            PsiFile file =null;
            final PsiFile[] files = FilenameIndex.getFilesByName(p, "SystemConfiguration.java", GlobalSearchScope.allScope(p));
            if (files != null && files.length > 0) {
                file = files[0];}

            final PsiDirectory directory = file.getContainingDirectory();


          PsiDirectory dir= PSIHelper.createDirectory(directory, textField1.getText().toLowerCase(Locale.ROOT));
            String baseClass=textField1.getText();
            String baseClassContent = BaseClassTemplate.fillTemplate(baseClass,options);
            String daoClass = baseClass+"ConfigDAOImpl";
            String daoClassContent = ConfigDAOClassTemplate.fillTemplate(baseClass, options);
            String factoryClass=daoClass+"Factory";
            String factoryClassContent = SystemConfigFactoryTemplate.fillTemplate(baseClass,factoryClass);
            String configClass=baseClass+"Config";

            PSIHelper.createFileInDirectory(directory,configClass+".java","blablabla", "JAVA");
            PsiFile base= PSIHelper.createFileInDirectory(dir,baseClass+".java",baseClassContent, "JAVA");

            PsiFile daoFile = PSIHelper.createFileInDirectory(dir,daoClass+".java",daoClassContent, "JAVA");
            PsiFile factory=PSIHelper.createFileInDirectory(dir,factoryClass+".java",factoryClassContent, "JAVA");
        /*    OpenFileDescriptor descriptor = new OpenFileDescriptor(p, factory.getVirtualFile());
            descriptor.navigateInEditor(p, true);*/


        });
    }
}
