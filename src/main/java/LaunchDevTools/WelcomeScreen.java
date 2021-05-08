package LaunchDevTools;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

 public class WelcomeScreen extends JFrame {

        private JPanel contentPane;
        private JTextField txtName;
        private JTextField taskKey;

        public WelcomeScreen() {
            this.setVisible(true);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setBounds(CustomSize.width/4, CustomSize.height/4, 581, 441);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(255, 255, 255));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);

            JLabel lblTitle = new JLabel("imc");
            lblTitle.setForeground(Color.BLACK);
            lblTitle.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel lblEmail = new JLabel("E-mail address");

            JLabel lblTaskKey = new JLabel("Task Key");

            txtName = new JTextField();
            txtName.setText("lia.ghita@im-c.com");
            txtName.setToolTipText("E-mail");
            txtName.setBackground(new Color(255, 255, 255));
            txtName.setColumns(10);

            taskKey = new JTextField();
            taskKey.setToolTipText("Task Key");
            taskKey.setText("LMSILS-20127");
            taskKey.setColumns(10);
            taskKey.setBackground(Color.WHITE);

            JLabel lblDevTools = new JLabel("Dev Tools");
            lblDevTools.setForeground(new Color(219, 112, 147));
            lblDevTools.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel lblHint = new JLabel("Please provide your e-mail address and the key of the task you are working on:");
            lblHint.setForeground(new Color(128, 128, 128));

            JLabel lblHintExample = new JLabel("i.e: LMSILS-20127");

            JButton btnEnter = new JButton("Enter");
            btnEnter.setBackground(new Color(216, 191, 216));
            btnEnter.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
            btnEnter.addActionListener(e -> {
                CurrentUser.issueKey=taskKey.getText().toUpperCase();
                CurrentUser.email=txtName.getText();
                dispose();
                MainMenu mm= new MainMenu();
            });


            GroupLayout gl_contentPane = new GroupLayout(contentPane);
            gl_contentPane.setHorizontalGroup(
                    gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addContainerGap(103, Short.MAX_VALUE)
                                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                    .addComponent(lblTitle)
                                                    .addGap(275))
                                            .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                    .addComponent(lblDevTools, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(215))
                                            .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblEmail)
                                                            .addComponent(lblTaskKey)
                                                            .addComponent(lblHintExample))
                                                    .addGap(35)
                                                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(taskKey, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(81))
                                            .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                    .addComponent(lblHint)
                                                    .addGap(71))))
            );
            gl_contentPane.setVerticalGroup(
                    gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGap(10)
                                    .addComponent(lblTitle)
                                    .addGap(18)
                                    .addComponent(lblDevTools, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblHint)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblEmail)
                                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(28)
                                    .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblTaskKey)
                                            .addComponent(taskKey, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblHintExample)
                                    .addGap(58)
                                    .addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                    .addGap(45))
            );
            contentPane.setLayout(gl_contentPane);
        }

     public static void main(String[] args) {
         WelcomeScreen ws = new WelcomeScreen();
     }
    }





