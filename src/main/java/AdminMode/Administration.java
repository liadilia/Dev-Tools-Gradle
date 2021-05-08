package AdminMode;

import LaunchDevTools.CustomSize;

import javax.swing.*;

public class Administration {
    private JTextField metaTagIdTextField;
    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton saveButton;
    private JButton button1;

    public Administration() throws ClassNotFoundException {

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);
        f.setTitle("imc Dev Tools");

        f.setJMenuBar(new JMenuBar());
        f.setContentPane(panel);
        //f.pack();
        f.setVisible(true);

    }


}
