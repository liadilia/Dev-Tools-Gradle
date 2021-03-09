package LicenseGernerator;

import PSIHelpers.PSIHelper;
import com.intellij.psi.PsiFile;

public class LicenseInputTemplates {

    // For adding to activationModuleIdentifierType.xsd
    public static String LicenseIdentifier (String id){
        String text = "<xs:enumeration value="+"\""+id+"\""+"/>";
        return text;
    }

    // for adding to the InitialLicenseSettings.xml and MaxLicenseSettings.xml
    public static String ActivationModule (String id){
        String text = "<activationModule identifier="+"\""+id+"\""+"comment=\"\">"+
                "<activationModuleSettings enabled=\"false\"/>"+
                "</activationModule>";
        return text;
    }

    public static void OpenAndAppend (PsiFile file, String name, String format, String data, String insertBefore){
        file =  PSIHelper.openFile(name, format);
        PSIHelper.appendFile(file,data,insertBefore);

    }
}
