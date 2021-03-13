package SystemConfigGenerator;

import LaunchDevTools.CustomSize;
import PSIHelpers.PSIHelper;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.impl.softwrap.SoftWrapAppliancePlaces;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.xml.actions.xmlbeans.FileUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.SQLOutput;

public class SystemConfigCreationForm {
    private JTextField textField1;
    private JButton addSectionButton;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JButton addButton;
    private JComboBox comboBox2;
    private JPanel panel;

    public SystemConfigCreationForm(){

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
        //  Image icon = (Image)IconLoader.getIcon("/resources/META-INF/imc_logo.png");
        //       jf.setIconImage(icon);

        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);
        addSectionButton.addActionListener(e -> {
            Project p = ProjectManager.getInstance().getDefaultProject();
            System.out.println(p);

            PsiFile file =null;
            final PsiFile[] files = FilenameIndex.getFilesByName(p, "testClass.java", GlobalSearchScope.allScope(p));


            if (files != null && files.length > 0) {
                file = files[0];}

            final PsiDirectory directory = file.getContainingDirectory();


          PsiDirectory dir= PSIHelper.createDirectory(directory, textField1.getText());
PSIHelper.createFromTemplate(dir,"test.java");
        });
    }
}
