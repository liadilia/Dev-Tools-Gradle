package MetaTagGenerator;


import LaunchDevTools.CurrentUser;
import LaunchDevTools.CustomSize;
import REST.BasicAuthorization;
import REST.Connection;
import REST.Helpers.JiraRequestHelper;
import REST.Helpers.PropadminRequestHelper;
import REST.RestEndpoints;
import REST.TokenAuthorization;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.psi.search.GlobalSearchScope;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;

import static REST.RestEndpoints.*;

public class MetaTagData extends JFrame {

    private JPanel contentPane;

    private String name;
    private String id;

    private String parentId= CurrentUser.issueKey;
    private String assignee="lia.ghita@im-c.com";

    public MetaTagData(String sql, String name, String id) {
        this.name=name;
        this.id=id;

        this.setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(CustomSize.width / 4, CustomSize.height / 4, 581, 441);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        getContentPane().setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        textArea.setBackground(new Color(216, 191, 216));
        textArea.setVisible(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(20);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setText(sql);
        textArea.setBounds(23, 41, 378, 209);
        getContentPane().add(textArea);


        JLabel lblMetaTagCreationSQL = new JLabel("Generated SQL for meta tag creation");
        lblMetaTagCreationSQL.setBounds(23, 16, 208, 14);
        getContentPane().add(lblMetaTagCreationSQL);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBounds(358, 43, 17, 90);
        getContentPane().add(scrollBar);


        JButton GenerateCode = new JButton("Open and append related files");
        GenerateCode.addActionListener(e -> {
          getContainingFileForClass("MetaTagId");
          // System.out.println(JiraRequestHelper.getJiraTaskAssignmentToUserBody("test"));
        });
        GenerateCode.setBounds(151, 368, 281, 23);
        contentPane.add(GenerateCode);

        // Copy the generated SQL to clipboard.

        JButton CopyToClipboard = new JButton("Copy SQL to clipboard");
        CopyToClipboard.setBounds(411, 76, 147, 23);
        contentPane.add(CopyToClipboard);
        CopyToClipboard.addActionListener(e -> {

            StringSelection stringSelection = new StringSelection(sql);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

        });

        //Create bundles in Propadmin

        JButton CreateBundles = new JButton("Create bundles");
        CreateBundles.setBounds(411, 42, 147, 23);
        contentPane.add(CreateBundles);
        CreateBundles.addActionListener(e -> {
            String jsonAuth = "{\"userName\":\""+ JIRA_USER +"\", \"password\":\""+ JIRA_PASS + "\"}";
            // authenticate to Propadmin
            try {
                Connection propadminAuth = new Connection(PROPADMIN_AUTH, Connection.Method.POST, jsonAuth, null);
                // fetch authorization token
                String token = propadminAuth.getResponseObject().get("token").getAsString();
                // compose the body for the call to create the bundles
                String context ="Name of the meta tag";
                String metaTagName = PropadminRequestHelper.getBundleCreationRequestBody(Form.metaTagNameKey,Form.metaTagName, context);
                context ="Description of the meta tag";
                String metaTagDescription = PropadminRequestHelper.getBundleCreationRequestBody(Form.metaTagDescriptionKey,Form.metaTagDescription, context);
                 // POST request to create the Propadmin bundles
                Connection createNameBundle= new Connection(PROPADMIN_CREATE_BUNDLE, Connection.Method.POST, metaTagName, new TokenAuthorization(token));
                Connection createDescriptionBundle= new Connection(PROPADMIN_CREATE_BUNDLE, Connection.Method.POST, metaTagDescription,new TokenAuthorization(token));

                //check the response code for both requests in case any error occurred
               if (createNameBundle.getResponseCode()<299 && createDescriptionBundle.getResponseCode()<299){
                   JOptionPane.showMessageDialog(null, "Bundles successfully created");
               }
               else if (createNameBundle.getResponseCode()<299 || createDescriptionBundle.getResponseCode()<299){
                   JOptionPane.showMessageDialog(null, "One or both of the bundles could not be created");
               }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        JButton AddSQLToJira = new JButton("Add SQL to Jira Ticket");
        AddSQLToJira.setBounds(411, 110, 147, 23);
        contentPane.add(AddSQLToJira);
        AddSQLToJira.addActionListener(e->{
            try {
            //    System.out.println(JiraRequestHelper.getJiraSubTaskCreationRequestBody(parentId, sql));
            //    System.out.println(JiraRequestHelper.getJiraTaskAssignmentToUserBody(assignee));
                Connection createJiraSubTask= new Connection(JIRA_ISSUE_ROOT, Connection.Method.POST, JiraRequestHelper.getJiraSubTaskCreationRequestBody(parentId, sql), new BasicAuthorization(JIRA_USER, JIRA_PASS));
                String response = createJiraSubTask.getResponseObject().get("key").getAsString();
                Connection assignTask= new Connection(JIRA_ISSUE_ROOT+response, Connection.Method.PUT, JiraRequestHelper.getJiraTaskAssignmentToUserBody(assignee),  new BasicAuthorization(JIRA_USER, JIRA_PASS));
                if (createJiraSubTask.getResponseCode()<299 && assignTask.getResponseCode()<299){
                    JOptionPane.showMessageDialog(null, "Sub-task created and assigned for commit on the init script. The key for the created Jira ticket is "+response);
                }
                else if (createJiraSubTask.getResponseCode()<299){
                    JOptionPane.showMessageDialog(null, "The sub-task could not be created");
                }
                else {
                    JOptionPane.showMessageDialog(null, "The sub-task was created but it could not be assigned. The key for the created Jira ticket is "+response);
                }

            } catch (IOException ioException) {
               ioException.printStackTrace();
            }

        });

    }

    private String generateMetaTagIdEntry(String name, String ID){

        String newName=name.replace(' ','_');
        String entry = "\t int "+newName.toUpperCase() + " = "+ID +";\n";
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
            src.insert(i,insertRow );

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





