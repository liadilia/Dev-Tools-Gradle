package MetaTagGenerator;

import LaunchDevTools.CustomSize;
import LaunchDevTools.MainMenuForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.swing.JOptionPane.showMessageDialog;


public class MetaTagCreationForm {
    private JPanel panel;
    private JTextField metatagName;
    private JButton createButton;
    private JTextField metatagId;
    private JComboBox<ComboItem> metatagTypeSelection;
    private JComboBox<ComboItem> isSystemSelect;
    private JComboBox<ComboItem> useForCourses;
    private JComboBox<ComboItem> useForMedia;
    private JComboBox<ComboItem> multiLanguage;
    private JComboBox<ComboItem> useForPaths;
    private JButton backButton;
    private JTextArea metatagDescription;

    public static String metaTagNameKey = "";
    public static String metaTagDescriptionKey="";
    public static String metaTagName = "";
    public static String metaTagDescription="";

public MetaTagCreationForm (){

   //     UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme");

        JFrame jf = new JFrame();
        jf.setBounds(CustomSize.width / 3, CustomSize.height / 8, 750, 800);

        jf.setTitle("imc Dev Tools");
  //      jf.setIconImage(ImageIO.read(new File("resources/META-INF/imc_logo.png")));
        jf.setContentPane(panel);
        //  jf.pack();
        jf.setVisible(true);



        DefaultComboBoxModel multiLanguageModel =  (DefaultComboBoxModel)multiLanguage.getModel();
        multiLanguageModel.addElement(new ComboItem("Yes", "1"));
        multiLanguageModel.addElement(new ComboItem("No", "0"));
        DefaultComboBoxModel useForPathsModel =  (DefaultComboBoxModel)useForPaths.getModel();
        useForPathsModel.addElement(new ComboItem("Yes", "1"));
        useForPathsModel.addElement(new ComboItem("No", "0"));
        DefaultComboBoxModel useForMediaModel =  (DefaultComboBoxModel)useForMedia.getModel();
        useForMediaModel.addElement(new ComboItem("Yes", "1"));
        useForMediaModel.addElement(new ComboItem("No", "0"));
        DefaultComboBoxModel useForCoursesModel =  (DefaultComboBoxModel)useForCourses.getModel();
        useForCoursesModel.addElement(new ComboItem("Yes", "1"));
        useForCoursesModel.addElement(new ComboItem("No", "0"));
        DefaultComboBoxModel isSystemModel =  (DefaultComboBoxModel) isSystemSelect.getModel();
        isSystemModel.addElement(new ComboItem("Yes", "1"));
        isSystemModel.addElement(new ComboItem("No", "0"));


        DefaultComboBoxModel<ComboItem> typeModel = new DefaultComboBoxModel<ComboItem>();
        typeModel.addElement(new ComboItem("Text", "1"));
        typeModel.addElement(new ComboItem("Text Area", "2"));
        typeModel.addElement(new ComboItem("File", "3"));
        typeModel.addElement(new ComboItem("Date", "4"));
        typeModel.addElement(new ComboItem("Date Time", "5"));
        typeModel.addElement(new ComboItem("Select Moderator", "6"));
        typeModel.addElement(new ComboItem("Source", "7"));
        typeModel.addElement(new ComboItem("Select Admin", "9"));
        typeModel.addElement(new ComboItem("Select Status", "10"));
        typeModel.addElement(new ComboItem("Select Tutor", "11"));
        typeModel.addElement(new ComboItem("Picture", "12"));
        typeModel.addElement(new ComboItem("Select registration type", "13"));
        typeModel.addElement(new ComboItem("Checkbox", "14"));
        typeModel.addElement(new ComboItem("Select Cancellation Type", "15"));
        typeModel.addElement(new ComboItem("Link URL", "16"));
        typeModel.addElement(new ComboItem("Contact", "17"));
        typeModel.addElement(new ComboItem("Select Course Start Page", "18"));
        typeModel.addElement(new ComboItem("Select Yes No", "19"));
        typeModel.addElement(new ComboItem("Time", "20"));
        typeModel.addElement(new ComboItem("Select Text", "21"));
        typeModel.addElement(new ComboItem("Select Text I18N", "22"));
        typeModel.addElement(new ComboItem("Select Access Duration", "23"));
        typeModel.addElement(new ComboItem("Date Time with delete", "24"));
        typeModel.addElement(new ComboItem("Time frame", "25"));
        typeModel.addElement(new ComboItem("Select Course Status", "26"));
        typeModel.addElement(new ComboItem("Course Execution", "27"));
        typeModel.addElement(new ComboItem("Organizer", "28"));
        typeModel.addElement(new ComboItem("Price with Currency", "29"));
        typeModel.addElement(new ComboItem("File Upload non Course", "30"));
        typeModel.addElement(new ComboItem("Select Group", "32"));
        typeModel.addElement(new ComboItem("Select Yes/No Default Yes", "39"));
        typeModel.addElement(new ComboItem("Input Non Negative Integer", "42"));
        typeModel.addElement(new ComboItem("Select Popup", "43"));
        typeModel.addElement(new ComboItem("Costs with Currency", "44"));
        typeModel.addElement(new ComboItem("Time Frame Month/Year", "50"));
        typeModel.addElement(new ComboItem("Input Non Negative Decimal", "51"));
        typeModel.addElement(new ComboItem("Select Cost Center", "52"));
        typeModel.addElement(new ComboItem("Select Certificate", "53"));
        typeModel.addElement(new ComboItem("Select Course Trigger", "54"));
        typeModel.addElement(new ComboItem("Select Course Trigger W_END", "55"));
        typeModel.addElement(new ComboItem("Select Type Versions", "56"));
        typeModel.addElement(new ComboItem("Dynamic Date", "57"));
        typeModel.addElement(new ComboItem("External Content Link", "58"));
        typeModel.addElement(new ComboItem("Publication Date", "59"));
        typeModel.addElement(new ComboItem("Date Calendar", "60"));
        typeModel.addElement(new ComboItem("Dynamic allow multiple registration before", "61"));
        typeModel.addElement(new ComboItem("Checkbox and Text Area", "72"));
        typeModel.addElement(new ComboItem("Multiple Checkboxes", "73"));
        typeModel.addElement(new ComboItem("Checkbox with Dependency", "74"));

        metatagTypeSelection.setModel(typeModel);

        createButton.addActionListener(e -> {
            //    MetaTagData metaTagData = new MetaTagData();
            jf.dispose();
        });
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String metaTagName = metatagName.getText();

                String Id2 = metatagId.getText();

                String metaTagDescription = metatagDescription.getText();
                Object itemtype = metatagTypeSelection.getSelectedItem();
                String type = ((ComboItem) itemtype).getValue();
                Object item = isSystemSelect.getSelectedItem();
                String isSystem = ((ComboItem)item).getValue();
                Object item2 = useForCourses.getSelectedItem();
                String useForCourses = ((ComboItem) item2).getValue();
                Object item3 = isSystemSelect.getSelectedItem();
                String useForLP = ((ComboItem) item3).getValue();
                Object item4 = useForMedia.getSelectedItem();
                String UseForMedia = ((ComboItem) item4).getValue();
                Object item5 = multiLanguage.getSelectedItem();
                String multiLang = ((ComboItem) item5).getValue();

                String metaTagNameKey = "dbsMetatag" + Id2 + "Name";
                String metaTagDescriptionKey = "dbsMetatag" + Id2 + "Description";

                String sql = composeSQL(Id2, type, isSystem, useForCourses, useForLP, UseForMedia, multiLang);
                if (metaTagName.equals("")) showMessageDialog(null, "Name is mandatory");
                if (Id2.equals("")) showMessageDialog(null, "Id is mandatory");
                else {
                    try {
                        MetaTagActionsForm mtd = new MetaTagActionsForm(sql, metaTagName, Id2);
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                        unsupportedLookAndFeelException.printStackTrace();
                    } catch (InstantiationException instantiationException) {
                        instantiationException.printStackTrace();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    jf.dispose();
                }


            }
        });
    backButton.addActionListener(e -> {
        try {
            MainMenuForm mmf = new MainMenuForm();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        jf.dispose();

    });

    }


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    public String composeSQL(String Id, String type, String isSystem, String useForCourses, String useForLP, String UseForMedia, String multiLang){
        String sql="";
        StringBuilder builder = null;
       /* builder.append("insert into metatag (metatag_id, language_id, creator, creator_id, creationdate, name, active, description, description2, formelementtype_id, required_tag, systemitem, useforcourse, useformedia, useforcommunity, useforservice, useforprogram, useforresource, lastupdated, lastupdater_id, useforexercisegroup, useforexercisesheet, useforexercise, multilang) values (");
        builder.append(Id);
        builder.append("dbsLANG, 'Learning Suite System', 0, '");
        builder.append(dtf.format(now));
        builder.append(" 00:00:00.000',");
        builder.append(metaTagNameKey);
        builder.append(", 1,");
        builder.append(metaTagDescriptionKey);
        builder.append(", '', ");
        builder.append(type);
        builder.append(", 0," );
        builder.append(isSystem);
        builder.append(", ");
        builder.append(useForCourses);
        builder.append(", ");
        builder.append(UseForMedia);
        builder.append(", 0, 0, ");
        builder.append(useForLP);
        builder.append(", 0, '");
        builder.append(dtf.format(now));
        builder.append(" 00:00:00.000', 0, 0, 0, 0,");
        builder.append(multiLang);
        builder.append(" );");
*/
        sql= "insert into metatag (metatag_id, language_id, creator, creator_id, creationdate, name, active, description, description2, formelementtype_id, required_tag, systemitem, useforcourse, useformedia, useforcommunity, useforservice, useforprogram, useforresource, lastupdated, lastupdater_id, useforexercisegroup, useforexercisesheet, useforexercise, multilang) " +
                "values (" + Id+
                "dbsLANG, 'Learning Suite System', 0, '" + dtf.format(now)+
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

     //   sql = builder.toString();
        return sql;
    }
}

