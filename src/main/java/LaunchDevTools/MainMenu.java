package LaunchDevTools;

import LicenseGernerator.LicenseForm;
import MetaTagGenerator.Form;
//import com.intellij.ide.plugins.newui.LicensePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
        import java.awt.Color;
import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;
        import javax.swing.border.BevelBorder;

        public class MainMenu extends JFrame {

    private JPanel contentPane;


    public MainMenu() {

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(CustomSize.width/4, CustomSize.height/4, 581, 441);
        contentPane = new JPanel();
//        contentPane.setBackground(new Color(255, 255, 255));
//        contentPane.setForeground(new Color(216, 191, 216));
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblTitleImc = new JLabel("imc");
//        lblTitleImc.setForeground(new Color(0, 0, 0));
//        lblTitleImc.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblDevTools = new JLabel(" Dev Tools");
//        lblDevTools.setForeground(new Color(219, 112, 147));
//        lblDevTools.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton btnCreateMetatag = new JButton("Create a new meta tag");
//        btnCreateMetatag.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
//        btnCreateMetatag.setBackground(new Color(216, 191, 216));
//        btnCreateMetatag.setFont(new Font("Tahoma", Font.PLAIN, 13));

        btnCreateMetatag.addActionListener(e -> {
            Form form= new Form();
            dispose();
        });


        JButton btnCreateNewLicense = new JButton("Create a new license");
//        btnCreateNewLicense.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
//        btnCreateNewLicense.setBackground(new Color(216, 191, 216));
        btnCreateNewLicense.addActionListener(arg0 -> {
                    LicenseForm lf = new LicenseForm();
                    dispose();
                }

                );
//        btnCreateNewLicense.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnNewButton2 = new JButton("New Button");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 13));
//        btnNewButton2.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
//        btnNewButton2.setBackground(new Color(216, 191, 216));
        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });



        JButton btnClose = new JButton("Close");
        btnClose.setIconTextGap(7);
//        btnClose.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
//        btnClose.setBackground(new Color(216, 191, 216));
//        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 13));

        btnClose.addActionListener(e -> dispose());



        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(250)
                                .addComponent(lblTitleImc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(114))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addContainerGap(217, Short.MAX_VALUE)
                                .addComponent(lblDevTools, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                .addGap(147))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(184)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                                        .addComponent(btnCreateNewLicense, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnNewButton2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                        .addComponent(btnClose, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(btnCreateMetatag, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                                .addGap(180))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(lblTitleImc)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDevTools, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addGap(31)
                                .addComponent(btnCreateMetatag, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addComponent(btnCreateNewLicense, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addComponent(btnNewButton2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                                .addGap(24))
        );
        contentPane.setLayout(gl_contentPane);
    }

}
