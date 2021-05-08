package AdminMode;

import LaunchDevTools.CustomSize;

import javax.swing.*;

public class Administration {
    private JTextField metaTagIdTextField;
    private JPanel panel;

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
