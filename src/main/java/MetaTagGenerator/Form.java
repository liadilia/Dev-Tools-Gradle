package MetaTagGenerator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import LaunchDevTools.CustomSize;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import static javax.swing.JOptionPane.showMessageDialog;

public class Form extends JFrame {



    private JPanel contentPane;
    private JTextField Name;
    private JTextField Id;
    private JTextField Description;

    public static String metaTagNameKey = "";
    public static String metaTagDescriptionKey="";
    public static String metaTagName = "";
    public static String metaTagDescription="";

    public Form() {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(CustomSize.width/4, CustomSize.height/4, 581, 441);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblNewMetaTag = new JLabel("Create a new meta tag");
        lblNewMetaTag.setBounds(174, 5, 224, 27);
        lblNewMetaTag.setForeground(Color.GRAY);
        lblNewMetaTag.setFont(new Font("Tahoma", Font.PLAIN, 22));

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(25, 53, 95, 14);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(25, 91, 117, 14);

        JLabel lblType = new JLabel("Type");
        lblType.setBounds(25, 130, 117, 14);

        JLabel lblID = new JLabel("ID");
        lblID.setBounds(25, 169, 117, 14);

        Name = new JTextField();
        Name.setBounds(130, 50, 301, 20);
        Name.setColumns(10);

        Id = new JTextField();
        Id.setBounds(130, 166, 96, 20);
        Id.setColumns(10);

        Description = new JTextField();
        Description.setBounds(130, 88, 301, 20);

        JButton btnCreate = new JButton("Create");
        btnCreate.setBounds(229, 350, 131, 36);
        btnCreate.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
        btnCreate.setBackground(new Color(216, 191, 216));
        btnCreate.setForeground(Color.DARK_GRAY);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(464, 10, 91, 27);
        btnBack.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216), new Color(216, 191, 216)));
        btnBack.setBackground(new Color(216, 191, 216));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        JComboBox comboBox_Type = new JComboBox();

        comboBox_Type.setBounds(130, 126, 131, 22);
        comboBox_Type.addItem(new ComboItem("Text", "1"));
        comboBox_Type.addItem(new ComboItem("Text Area", "2"));
        comboBox_Type.addItem(new ComboItem("File", "3"));
        comboBox_Type.addItem(new ComboItem("Date", "4"));
        comboBox_Type.addItem(new ComboItem("Date Time", "5"));
        comboBox_Type.addItem(new ComboItem("Select Moderator", "6"));
        comboBox_Type.addItem(new ComboItem("Source", "7"));
        comboBox_Type.addItem(new ComboItem("Select Admin", "9"));
        comboBox_Type.addItem(new ComboItem("Select Status", "10"));
        comboBox_Type.addItem(new ComboItem("Select Tutor", "11"));
        comboBox_Type.addItem(new ComboItem("Picture", "12"));
        comboBox_Type.addItem(new ComboItem("Select registration type", "13"));
        comboBox_Type.addItem(new ComboItem("Checkbox", "14"));
        comboBox_Type.addItem(new ComboItem("Select Cancellation Type", "15"));
        comboBox_Type.addItem(new ComboItem("Link URL", "16"));
        comboBox_Type.addItem(new ComboItem("Contact", "17"));
        comboBox_Type.addItem(new ComboItem("Select Course Start Page", "18"));
        comboBox_Type.addItem(new ComboItem("Select Yes No", "19"));
        comboBox_Type.addItem(new ComboItem("Time", "20"));
        comboBox_Type.addItem(new ComboItem("Select Text", "21"));
        comboBox_Type.addItem(new ComboItem("Select Text I18N", "22"));
        comboBox_Type.addItem(new ComboItem("Select Access Duration", "23"));
        comboBox_Type.addItem(new ComboItem("Date Time with delete", "24"));
        comboBox_Type.addItem(new ComboItem("Time frame", "25"));
        comboBox_Type.addItem(new ComboItem("Select Course Status", "26"));
        comboBox_Type.addItem(new ComboItem("Course Execution", "27"));
        comboBox_Type.addItem(new ComboItem("Organizer", "28"));
        comboBox_Type.addItem(new ComboItem("Price with Currency", "29"));
        comboBox_Type.addItem(new ComboItem("File Upload non Course", "30"));
        comboBox_Type.addItem(new ComboItem("Select Group", "32"));
        comboBox_Type.addItem(new ComboItem("Select Yes/No Default Yes", "39"));
        comboBox_Type.addItem(new ComboItem("Input Non Negative Integer", "42"));
        comboBox_Type.addItem(new ComboItem("Select Popup", "43"));
        comboBox_Type.addItem(new ComboItem("Costs with Currency", "44"));
        comboBox_Type.addItem(new ComboItem("Time Frame Month/Year", "50"));
        comboBox_Type.addItem(new ComboItem("Input Non Negative Decimal", "51"));
        comboBox_Type.addItem(new ComboItem("Select Cost Center", "52"));
        comboBox_Type.addItem(new ComboItem("Select Certificate", "53"));
        comboBox_Type.addItem(new ComboItem("Select Course Trigger", "54"));
        comboBox_Type.addItem(new ComboItem("Select Course Trigger W_END", "55"));
        comboBox_Type.addItem(new ComboItem("Select Type Versions", "56"));
        comboBox_Type.addItem(new ComboItem("Dynamic Date", "57"));
        comboBox_Type.addItem(new ComboItem("External Content Link", "58"));
        comboBox_Type.addItem(new ComboItem("Publication Date", "59"));
        comboBox_Type.addItem(new ComboItem("Date Calendar", "60"));
        comboBox_Type.addItem(new ComboItem("Dynamic allow multiple registration before", "61"));
        comboBox_Type.addItem(new ComboItem("Checkbox and Text Area", "72"));
        comboBox_Type.addItem(new ComboItem("Multiple Checkboxes", "73"));
        comboBox_Type.addItem(new ComboItem("Checkbox with Dependency", "74"));



        JLabel lblNewLabel = new JLabel("Visibility settings");
        lblNewLabel.setBounds(229, 199, 201, 19);
        lblNewLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN, 15));

        JLabel lblSystemItem = new JLabel("Is System Item");
        lblSystemItem.setBounds(25, 229, 104, 14);

        JComboBox comboBox_IsSystemItem = new JComboBox();
        comboBox_IsSystemItem.setBounds(25, 249, 104, 22);

        comboBox_IsSystemItem.addItem(new ComboItem("Yes", "1"));
        comboBox_IsSystemItem.addItem(new ComboItem("No", "0"));


        JLabel lblMultiLanguage = new JLabel("Multi language");
        lblMultiLanguage.setBounds(22, 282, 107, 14);


        JComboBox comboBox_MultiLanguage = new JComboBox();
        comboBox_MultiLanguage.setBounds(25, 299, 104, 22);
        comboBox_MultiLanguage.addItem(new ComboItem("Yes", "1"));
        comboBox_MultiLanguage.addItem(new ComboItem("No", "0"));

        JLabel lblUseForCourse = new JLabel("Use for Course");
        lblUseForCourse.setBounds(174, 229, 104, 14);

        JComboBox comboBox_UseForCourse = new JComboBox();
        comboBox_UseForCourse.setBounds(174, 249, 104, 22);
        comboBox_UseForCourse.addItem(new ComboItem("Yes", "1"));
        comboBox_UseForCourse.addItem(new ComboItem("No", "0"));

        JLabel lblUseForMedia = new JLabel("Use for Media");
        lblUseForMedia.setBounds(307, 229, 102, 14);

        JComboBox comboBox_UseForMedia = new JComboBox();
        comboBox_UseForMedia.setBounds(305, 249, 104, 22);
        comboBox_UseForMedia.addItem(new ComboItem("Yes", "1"));
        comboBox_UseForMedia.addItem(new ComboItem("No", "0"));

        JLabel lblUseForLearningPath = new JLabel("Use for Learning path");
        lblUseForLearningPath.setBounds(174, 282, 153, 14);

        JComboBox comboBox_UseForLearnignPath = new JComboBox();
        comboBox_UseForLearnignPath.setBounds(174, 299, 104, 22);
        comboBox_UseForLearnignPath.addItem(new ComboItem("Yes", "1"));
        comboBox_UseForLearnignPath.addItem(new ComboItem("No", "0"));
        contentPane.setLayout(null);
        contentPane.add(lblNewMetaTag);
        contentPane.add(lblNewLabel);
        contentPane.add(lblDescription);
        contentPane.add(lblName);
        contentPane.add(lblID);
        contentPane.add(lblType);
        contentPane.add(Description);
        contentPane.add(Name);
        contentPane.add(Id);
        contentPane.add(comboBox_Type);
        contentPane.add(lblMultiLanguage);
        contentPane.add(lblSystemItem);
        contentPane.add(lblUseForMedia);
        contentPane.add(lblUseForCourse);
        contentPane.add(lblUseForLearningPath);
        contentPane.add(comboBox_UseForLearnignPath);
        contentPane.add(comboBox_MultiLanguage);
        contentPane.add(comboBox_UseForCourse);
        contentPane.add(comboBox_IsSystemItem);
        contentPane.add(comboBox_UseForMedia);
        contentPane.add(btnCreate);
        contentPane.add(btnBack);

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                metaTagName=Name.getText();

                String Id2=Id.getText();

                metaTagDescription =Description.getText();
                Object itemtype = comboBox_Type.getSelectedItem();
                String type = ((ComboItem)itemtype).getValue();
                Object item = comboBox_IsSystemItem.getSelectedItem();
                String isSystem = ((ComboItem)item).getValue();
                Object item2 = comboBox_UseForCourse.getSelectedItem();
                String useForCourses = ((ComboItem)item2).getValue();
                Object item3 = comboBox_IsSystemItem.getSelectedItem();
                String useForLP=((ComboItem)item3).getValue();
                Object item4 = comboBox_UseForMedia.getSelectedItem();
                String UseForMedia=((ComboItem)item4).getValue();
                Object item5 = comboBox_MultiLanguage.getSelectedItem();
                String multiLang =((ComboItem)item5).getValue();

                metaTagNameKey = "dbsMetatag"+Id2+"Name";
                metaTagDescriptionKey= "dbsMetatag"+Id2+"Description";

               String sql= composeSQL(Id2,type,isSystem,useForCourses,useForLP,UseForMedia,multiLang);
                if (metaTagName.equals(""))  showMessageDialog(null, "Name is mandatory");
                if (Id2.equals(""))  showMessageDialog(null, "Id is mandatory");
                else
                {
                    MetaTagData mtd=new MetaTagData(sql, metaTagName, Id2);
                    dispose();
                }


            }
        });


    }



    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    public String composeSQL(String Id, String type, String isSystem, String useForCourses, String useForLP, String UseForMedia, String multiLang){
        String sql="";
        sql= "insert into metatag (metatag_id, language_id, creator, creator_id, creationdate, name, active, description, description2, formelementtype_id, required_tag, systemitem, useforcourse, useformedia, useforcommunity, useforservice, useforprogram, useforresource, lastupdated, lastupdater_id, useforexercisegroup, useforexercisesheet, useforexercise, multilang) " +
                "values (" + Id+
                ",dbsLANG, 'Learning Suite System', 0, '" + dtf.format(now)+
                " 00:00:00.000'," +metaTagNameKey+
                ", 1," +metaTagDescriptionKey+
                ", '', " +type+
                ", 0," + isSystem+
                ", " +useForCourses+
                "," +UseForMedia+
                ", 0, 0, " +useForLP+
                ", 0, '" +dtf.format(now)+
                " 00:00:00.000', 0, 0, 0, 0," + multiLang+
                " );";


        return sql;
    }
}
