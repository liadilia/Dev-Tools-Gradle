package SystemConfigGenerator;

import LaunchDevTools.CustomSize;
import LaunchDevTools.MainMenuForm;
import PSIHelpers.PSIHelper;
import Templates.BaseClassTemplate;
import Templates.ConfigDAOClassTemplate;
import Templates.SystemConfigFactoryTemplate;
import com.intellij.openapi.command.WriteCommandAction;
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
import java.util.concurrent.atomic.AtomicInteger;

public class SystemConfigCreationForm {
    private JTextField textField1;
    private JButton addSectionButton;
    private JTextField optionTitle;
    private JButton addToListOfButton;
    private JComboBox optionType;
    private JPanel panel;
    private JTextField typeTextField;
    private JButton backButton;
    private JList <String> optionList;
    private JButton deleteSelectedOptionsButton;
    private List<ConfigOption> options = new ArrayList<>();


    public SystemConfigCreationForm(){

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
        //  Image icon = (Image)IconLoader.getIcon("/resources/META-INF/imc_logo.png");
        //       jf.setIconImage(icon);
        DefaultListModel<String>  model= new DefaultListModel<String>();
        optionList.setModel(model);
        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);
        int index= 0;
        addToListOfButton.addActionListener(e -> {
        String title =  optionTitle.getText();
        ConfigOption.ConfigType type =ConfigOption.ConfigType.valueOf(typeTextField.getText());
        ConfigOption configOption = new ConfigOption(title, type);
        options.add(configOption);
            JOptionPane.showMessageDialog(jf, "Option stored. Add all options before creating the config");
        optionTitle.setText("");
        typeTextField.setText("");
        model.addElement(configOption.title);
        addSectionButton.setEnabled(true);
        });


        addSectionButton.addActionListener(e -> {
            Project p = ProjectManager.getInstance().getDefaultProject();
            PsiFile file =null;

            //Identify the System Configuration directory by finding the directory where the SystemConfiguration class is located, as PsiDirectory does not have a method to fetch directory by name
            final PsiFile[] files = FilenameIndex.getFilesByName(p, "SystemConfiguration.java", GlobalSearchScope.allScope(p));
            if (files != null && files.length > 0) {
                file = files[0];}

            final PsiDirectory directory = file.getContainingDirectory();

           // Create the new directory
           PsiDirectory dir= PSIHelper.createDirectory(directory, textField1.getText().toLowerCase(Locale.ROOT));
            String baseClass=textField1.getText();
            String baseClassContent = BaseClassTemplate.fillTemplate(baseClass,options);
            String daoClass = baseClass+"ConfigDAOImpl";
            String daoClassContent = ConfigDAOClassTemplate.fillTemplate(baseClass, options);
            String factoryClass=daoClass+"Factory";
            String factoryClassContent = SystemConfigFactoryTemplate.fillTemplate(baseClass,factoryClass);
            String configClass=baseClass+"Config";
            WriteCommandAction.runWriteCommandAction(p, new Runnable() {
                @Override
                public void run() {
                    PSIHelper.createFileInDirectory(directory,configClass+".java","test", "JAVA");
                }
            });

            //Create the setting files
            PsiFile base= PSIHelper.createFileInDirectory(dir,baseClass+".java",baseClassContent, "JAVA");
            PsiFile daoFile = PSIHelper.createFileInDirectory(dir,daoClass+".java",daoClassContent, "JAVA");
            PsiFile factory=PSIHelper.createFileInDirectory(dir,factoryClass+".java",factoryClassContent, "JAVA");

            // Append the ConfigurationType class
            PsiFile configTypeClass=  PSIHelper.getContainingFile("ConfigurationType");
            String data = baseClass.toUpperCase()+"(<increment here and correct semicolon above>,\""+baseClass.toLowerCase()+"\");\n\n";
            PSIHelper.appendFileAfterOccurence(configTypeClass,data, "private" );

            // Append the Configuration class
            PsiFile generalConfigClass=  PSIHelper.getContainingFile("Configuration");

            StringBuilder sb = new StringBuilder();
            sb.append("\n public ");
            sb.append(baseClass);
            sb.append(" get");
            sb.append(baseClass);
            sb.append("() {\n");
            sb.append(" return ");
            sb.append(baseClass.toLowerCase());
            sb.append(";\n}\n");
            sb.append("\n public void ");
            sb.append(baseClass);
            sb.append(" set");
            sb.append(baseClass);
            sb.append("() {\n");
            sb.append(" this.");
            sb.append(baseClass.toLowerCase());
            sb.append(" = ");
            sb.append(baseClass.toLowerCase());
            sb.append(";\n}\n");

            String data2 = "private "+baseClass+" "+baseClass.toLowerCase()+";\n";
            PSIHelper.appendFile(generalConfigClass,data2, " Timestamp getLoaded()" );

            PSIHelper.appendFile(generalConfigClass, sb.toString(),"}");

        });

        backButton.addActionListener(e -> {
            try {
                MainMenuForm mmf = new MainMenuForm();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            jf.dispose();

        });
    }


}
