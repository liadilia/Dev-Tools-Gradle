package LaunchDevTools;

import javax.swing.*;

public class WelcomeScreenForm {
    private JTextField txtName;
    private JTextField taskKey;
    private JButton enterButton;
    private JLabel emailLabel;
    private JLabel jiraTicket;
    private JPanel panel;

    public WelcomeScreenForm() throws ClassNotFoundException {

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 450, 500);
        jf.setTitle("imc Dev Tools");
        jf.setContentPane(panel);
        jf.setVisible(true);

        enterButton.addActionListener(e -> {
            CurrentUser.issueKey = taskKey.getText().toUpperCase();
            CurrentUser.email = txtName.getText();
            PluginConfigurationStrings.propadminBundle += txtName.getText();
            jf.dispose();
            try {
                MainMenuForm mm = new MainMenuForm();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
    }
}