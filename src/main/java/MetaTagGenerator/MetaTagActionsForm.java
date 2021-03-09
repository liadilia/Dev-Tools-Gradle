package MetaTagGenerator;

import LaunchDevTools.CurrentUser;
import LaunchDevTools.CustomSize;
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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import static REST.RestEndpoints.*;
import static REST.RestEndpoints.PROPADMIN_CREATE_BUNDLE;

public class MetaTagActionsForm {
    private JButton backButton;
    private JTextArea textArea;
    private JButton AddSQLToJira;
    private JButton CreateBundles;
    private JPanel panel;
    private JButton button4;
    private JButton CopyToClipboard;
    private JFormattedTextField formattedTextField;

    private String name;
    private String id;

    private String parentId= CurrentUser.issueKey;
    private String assignee="lia.ghita@im-c.com";




        public MetaTagActionsForm(String sql, String name, String id) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
            this.name=name;
            this.id=id;
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
                String jsonAuth = "{\"userName\":\"" + JIRA_USER + "\", \"password\":\"" + JIRA_PASS + "\"}";
                // authenticate to Propadmin
                try {
                    Connection propadminAuth = new Connection(PROPADMIN_AUTH, Connection.Method.POST, jsonAuth, null);
                    // fetch authorization token
                    String token = propadminAuth.getResponseObject().get("token").getAsString();
                    // compose the body for the call to create the bundles
                    String context = "Name of the meta tag";
                    String metaTagName = PropadminRequestHelper.getBundleCreationRequestBody(Form.metaTagNameKey, Form.metaTagName, context);
                    context = "Description of the meta tag";
                    String metaTagDescription = PropadminRequestHelper.getBundleCreationRequestBody(Form.metaTagDescriptionKey, Form.metaTagDescription, context);
                    // POST request to create the Propadmin bundles
                    Connection createNameBundle = new Connection(PROPADMIN_CREATE_BUNDLE, Connection.Method.POST, metaTagName, new TokenAuthorization(token));
                    Connection createDescriptionBundle = new Connection(PROPADMIN_CREATE_BUNDLE, Connection.Method.POST, metaTagDescription, new TokenAuthorization(token));

                    //check the response code for both requests in case any error occurred
                    if (createNameBundle.getResponseCode() < 299 && createDescriptionBundle.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "Bundles successfully created");
                    } else if (createNameBundle.getResponseCode() < 299 || createDescriptionBundle.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "One or both of the bundles could not be created");
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });

            AddSQLToJira.addActionListener(e -> {
                try {
                    //    System.out.println(JiraRequestHelper.getJiraSubTaskCreationRequestBody(parentId, sql));
                    //    System.out.println(JiraRequestHelper.getJiraTaskAssignmentToUserBody(assignee));
                    Connection createJiraSubTask = new Connection(JIRA_ISSUE_ROOT, Connection.Method.POST, JiraRequestHelper.getJiraSubTaskCreationRequestBody(parentId, sql), new BasicAuthorization(JIRA_USER, JIRA_PASS));
                    String response = createJiraSubTask.getResponseObject().get("key").getAsString();
                    Connection assignTask = new Connection(JIRA_ISSUE_ROOT + response, Connection.Method.PUT, JiraRequestHelper.getJiraTaskAssignmentToUserBody(assignee), new BasicAuthorization(JIRA_USER, JIRA_PASS));
                    if (createJiraSubTask.getResponseCode() < 299 && assignTask.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "Sub-task created and assigned for commit on the init script. The key for the created Jira ticket is " + response);
                    } else if (createJiraSubTask.getResponseCode() < 299) {
                        JOptionPane.showMessageDialog(null, "The sub-task could not be created");
                    } else {
                        JOptionPane.showMessageDialog(null, "The sub-task was created but it could not be assigned. The key for the created Jira ticket is " + response);
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            });


        }
        private String generateMetaTagIdEntry (String name, String ID){

            String newName = name.replace(' ', '_');
            String entry = "\t int " + newName.toUpperCase() + " = " + ID + ";\n";
            return entry;
        }
        private PsiFile getContainingFileForClass (String fqn){
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

