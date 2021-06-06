package AdminMode;

import DB.ConfigStringsDAO;
import LaunchDevTools.CustomSize;
import LaunchDevTools.PluginConfigurationStrings;
import REST.RestEndpoints;
import jdk.tools.jlink.plugin.Plugin;

import javax.swing.*;

public class Administration {
    private JTextField metaTagIdTextField;
    private JPanel panel;
    private JTextField jiraIssueRoot;
    private JTextField localizationAuth;
    private JTextField localizationBundleEndpoint;
    private JTextField jiraProject;
    private JTextField jiraSubTaskId;
    private JButton saveButton;
    private JButton restoreButton;

    public Administration() throws ClassNotFoundException {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);
        f.setTitle("imc Dev Tools");

        f.setJMenuBar(new JMenuBar());
        f.setContentPane(panel);
        //f.pack();
        jiraIssueRoot.setText(PluginConfigurationStrings.jiraIssueRoot);
        jiraProject.setText(PluginConfigurationStrings.jiraProject);
        jiraSubTaskId.setText(PluginConfigurationStrings.jiraSubtaskId);
        localizationAuth.setText(PluginConfigurationStrings.propadminAuth);
        localizationAuth.setText(PluginConfigurationStrings.propadminBundle);

        f.setVisible(true);


        saveButton.addActionListener(e->{
            ConfigStringsDAO.update(RestEndpoints.JiraProject,jiraIssueRoot.getText());
            ConfigStringsDAO.update(RestEndpoints.JiraSubtaskId,jiraSubTaskId.getText());
            ConfigStringsDAO.update(RestEndpoints.JiraProject,jiraProject.getText());
            ConfigStringsDAO.update(RestEndpoints.propadminAuth,localizationAuth.getText());
            ConfigStringsDAO.update(RestEndpoints.propadminCreateBundleEndpoint,localizationBundleEndpoint.getText());
        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
