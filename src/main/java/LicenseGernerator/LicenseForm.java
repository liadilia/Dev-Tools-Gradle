package LicenseGernerator;

import LaunchDevTools.CustomSize;
import LaunchDevTools.MainMenu;
import MetaTagGenerator.ComboItem;
import PSIHelpers.PSIHelper;
import REST.Helpers.JiraRequestHelper;
import REST.Helpers.PropadminRequestHelper;
import com.intellij.psi.PsiFile;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class LicenseForm extends JFrame {

        private JPanel contentPane;
        private JTextField Name;
        private JTextField Identifier;
        private JTextField description;


    public LicenseForm() {
            this.setVisible(true);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(CustomSize.width / 4, CustomSize.height / 4, 581, 441);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(255, 255, 255));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);

            JLabel lblNewLicense = new JLabel("Create a new license");
            lblNewLicense.setBounds(174, 8, 224, 27);
            lblNewLicense.setForeground(Color.GRAY);
            lblNewLicense.setFont(new Font("Arial", Font.PLAIN, 18));

            JLabel lblName = new JLabel("Name");
            lblName.setBounds(25, 80, 95, 14);

            JLabel lblIdentifier = new JLabel("Identifier");
            lblIdentifier.setBounds(25, 114, 117, 14);

            JLabel lblType = new JLabel("Type");
            lblType.setBounds(25, 184, 117, 14);

            Name = new JTextField();
            Name.setBounds(130, 77, 301, 20);
            Name.setColumns(10);

            Identifier = new JTextField();
            Identifier.setBounds(130, 111, 301, 20);
            Identifier.setText("i.e: strLicense...");
            Identifier.setForeground(Color.GRAY);
            Identifier.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                            if (Identifier.getText().equals("i.e: strLicense...")) {
                                    Identifier.setText("");
                                    Identifier.setForeground(Color.BLACK);
                            }
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                            if (Identifier.getText().isEmpty()) {
                                    Identifier.setForeground(Color.GRAY);
                                    Identifier.setText("i.e: strLicense...");
                            }
                    }
            });

            JLabel lblContextDescription = new JLabel("Description");
            lblContextDescription.setBounds(25, 145, 117, 14);


            description = new JTextField();
            description.setBounds(130, 142, 301, 20);
            description.setToolTipText("This text will be used as context for the bundle that will be created in Propadmin");



            JButton btnOpenFiles = new JButton("Open and append related files");
            btnOpenFiles.setBounds(28, 343, 247, 36);
            btnOpenFiles.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
            btnOpenFiles.setBackground(new Color(216, 191, 216));
            btnOpenFiles.setForeground(Color.DARK_GRAY);;
            btnOpenFiles.addActionListener(e->{
                PsiFile file =  PSIHelper.openFile("activationModuleIdentifierType", ".xsd");
                PSIHelper.appendFile(file,"hello","<tag>");
            });
            JButton btnCreateBundles = new JButton("Create bundle in propadmin");
            btnCreateBundles.setForeground(Color.DARK_GRAY);
            btnCreateBundles.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
            btnCreateBundles.setBackground(new Color(216, 191, 216));
            btnCreateBundles.setBounds(342, 343, 184, 36);
            btnCreateBundles.addActionListener(e->{
                  //  PropadminRequestHelper.CreatePropadminBundles(description.getText(), Identifier.getText(),Name.getText());
                  System.out.println( JiraRequestHelper.getJiraSubTaskCreationRequestBody("xxx","xxxx"));
            });

            JButton btnBack = new JButton("Back");
            btnBack.setBounds(464, 10, 91, 27);
            btnBack.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
            btnBack.setBackground(new Color(216, 191, 216));
            btnBack.addActionListener(e -> {
                    MainMenu mm = new MainMenu();
                    dispose();
            });

            JComboBox comboBox_Type = new JComboBox();
            comboBox_Type.setBounds(130, 180, 268, 22);
            comboBox_Type.addItem(new ComboItem("Add-On component", "1"));
            comboBox_Type.addItem(new ComboItem("Integration Framework", "2"));
            comboBox_Type.addItem(new ComboItem("Platform language", "3"));

            contentPane.setLayout(null);
            contentPane.add(lblNewLicense);
            contentPane.add(lblIdentifier);
            contentPane.add(lblName);
            contentPane.add(lblType);
            contentPane.add(description);
            contentPane.add(Name);
            contentPane.add(comboBox_Type);
            contentPane.add(btnOpenFiles);
            contentPane.add(btnBack);
            contentPane.add(btnCreateBundles);
            contentPane.add(Identifier);
            contentPane.add(lblContextDescription);

        }

    }


