package MetaTagGenerator;

import LaunchDevTools.CurrentUser;
import LaunchDevTools.CustomSize;
import LaunchDevTools.MainMenuForm;
import LaunchDevTools.PluginConfigurationStrings;
import REST.BasicAuthorization;
import REST.Connection;
import REST.Helpers.JiraRequestHelper;
import REST.Helpers.PropadminRequestHelper;
import REST.TokenAuthorization;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;


public class MetaTagActionsForm {
    private JButton backButton;
    private JTextArea textArea;
    private JButton AddSQLToJira;
    private JButton CreateBundles;
    private JPanel panel;
    private JButton CopyToClipboard;
    private JFormattedTextField formattedTextField;

    private String name;
    private String id;

    private String parentId = CurrentUser.issueKey;
    private String assignee = "lia.ghita@im-c.com";


    public MetaTagActionsForm(String sql, String name, String id) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        this.name = name;
        this.id = id;
        textArea.setText(sql);


        //   UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme");

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
        //   jf.setIconImage(ImageIO.read(new File("resources/META-INF/imc_logo.png")));
        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);

        CopyToClipboard.addActionListener(e -> {

            StringSelection stringSelection = new StringSelection(sql);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });
        CreateBundles.addActionListener(e -> {
            if (PluginConfigurationStrings.propadminAuth.equals("")) {
                JTextField path = new JTextField();
                int clicked = JOptionPane.showConfirmDialog(null, path, "Enter the localization server URL", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (clicked == JOptionPane.OK_OPTION) {
                    PluginConfigurationStrings.propadminAuth = path.getText();
                }
            } else if (PluginConfigurationStrings.propadminBundle.equals("")) {
                JTextField path = new JTextField();
                int clicked = JOptionPane.showConfirmDialog(null, path, "Enter the bundle endpoint", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (clicked == JOptionPane.OK_OPTION) {
                    PluginConfigurationStrings.propadminBundle = path.getText() + "?user=" + CurrentUser.email;
                }
            } else if (CurrentUser.localizationPassword.equals("")) {
                JPasswordField pf = new JPasswordField();
                int clicked = JOptionPane.showConfirmDialog(null, pf, "Enter your Propadmin password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (clicked == JOptionPane.OK_OPTION) {
                    CurrentUser.localizationPassword = new String(pf.getPassword());
                }
            } else {
                String jsonAuth = "{\"userName\":\"" + CurrentUser.email + "\", \"password\":\"" + CurrentUser.localizationPassword + "\"}";
                // authenticate to Propadmin
                try {
                    Connection propadminAuth = new Connection(PluginConfigurationStrings.propadminAuth, Connection.Method.POST, jsonAuth, null);
                    // fetch authorization token
                    String token = propadminAuth.getResponseObject().get("token").getAsString();
                    // compose the body for the call to create the bundles
                    String context = "Name of the meta tag";
                    String metaTagName = PropadminRequestHelper.getBundleCreationRequestBody(MetaTagCreationForm.metaTagNameKey, MetaTagCreationForm.metaTagName, context);
                    context = "Description of the meta tag";
                    String metaTagDescription = PropadminRequestHelper.getBundleCreationRequestBody(MetaTagCreationForm.metaTagDescriptionKey, MetaTagCreationForm.metaTagDescription, context);
                    // POST request to create the Propadmin bundles
                    Connection createNameBundle = new Connection(PluginConfigurationStrings.propadminBundle, Connection.Method.POST, metaTagName, new TokenAuthorization(token));
                    Connection createDescriptionBundle = new Connection(PluginConfigurationStrings.propadminBundle, Connection.Method.POST, metaTagDescription, new TokenAuthorization(token));
                    //check the response code for both requests in case any error occurred
                    if (createNameBundle.getResponseCode() < 299 && createDescriptionBundle.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "Bundles successfully created");
                    } else if (createNameBundle.getResponseCode() < 299 || createDescriptionBundle.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "One or both of the bundles could not be created");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        AddSQLToJira.addActionListener(e -> {
            if (CurrentUser.jiraPassword.equals("")) {
                JPasswordField pf = new JPasswordField();
                int clicked = JOptionPane.showConfirmDialog(null, pf, "Enter your Jira password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (clicked == JOptionPane.OK_OPTION) {
                    CurrentUser.jiraPassword = new String(pf.getPassword());
                }
            } else {
                try {
                    Connection createJiraSubTask = new Connection(PluginConfigurationStrings.jiraIssueRoot, Connection.Method.POST, JiraRequestHelper.getJiraSubTaskCreationRequestBody(parentId, sql), new BasicAuthorization(CurrentUser.email, CurrentUser.jiraPassword));
                    String response = createJiraSubTask.getResponseObject().get("key").getAsString();
                    Connection assignTask = new Connection(PluginConfigurationStrings.jiraIssueRoot + response, Connection.Method.PUT, JiraRequestHelper.getJiraTaskAssignmentToUserBody(assignee), new BasicAuthorization(CurrentUser.email, CurrentUser.jiraPassword));
                    if (createJiraSubTask.getResponseCode() < 299 && assignTask.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "Sub-task created and assigned for commit on the init script. The key for the created Jira ticket is " + response);
                    } else if ((createJiraSubTask.getResponseCode() < 299) || (createJiraSubTask.getResponseCode() >= 400)) {
                        JOptionPane.showMessageDialog(null, "The sub-task could not be created" + createJiraSubTask.getResponseObject());
                    } else {
                        JOptionPane.showMessageDialog(null, "The sub-task was created but it could not be assigned. The key for the created Jira ticket is " + response);
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "The sub-task could not be created. Please check your credentials and try again");
                    ioException.printStackTrace();
                }
            }
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

    private String generateMetaTagIdEntry(String name, String ID) {

        String newName = name.replace(' ', '_');
        String entry = "\t int " + newName.toUpperCase() + " = " + ID + ";\n";
        return entry;
    }

    private PsiFile getContainingFileForClass(String fqn) {
        String filename = fqn;
        if (fqn.contains(".")) {
            filename = fqn.substring(fqn.lastIndexOf('.') + 1);
        }
        if (filename.contains("$")) {
            filename = filename.substring(0, filename.indexOf('$'));
        }
        filename += ".java";
        Project[] p = ProjectManager.getInstance().getOpenProjects();

        Project project = p[0];
        final PsiFile[] files = FilenameIndex.getFilesByName(project, filename, GlobalSearchScope.allScope(project));
        if (files != null && files.length > 0) {
            PsiFile file = files[0];

            OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file.getVirtualFile());
            descriptor.navigateInEditor(project, true);

            StringBuilder src = new StringBuilder(file.getText());
            int i = src.lastIndexOf("}");
            String insertRow = generateMetaTagIdEntry(name, id);
            src.insert(i, insertRow);

            try {
                descriptor.getFile().setBinaryContent(src.toString().getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(src);
            return files[0];
        }
        return null;
    }
}

