package Templates;

import java.util.Locale;

public class SystemConfigFactoryTemplate {

    public static String fillTemplate(String category, String factoryClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("package configuration.");
        sb.append(category.toLowerCase(Locale.ROOT));
        sb.append(";\n");
        sb.append("import de.imc.clix.core.configuration.ConfigDAO;");
        sb.append("\n");
        sb.append("import de.imc.clix.core.configuration.ConfigDAOImplFactory;");
        sb.append("\n");
        sb.append("public class ");
        sb.append(factoryClassName);
        sb.append("  implements ConfigDAOImplFactory ");
        sb.append("{\n");
        sb.append("@Override\n");
        sb.append("public ConfigDAO getDaoImpl() ");
        sb.append("{\n");
        sb.append(" return new ");
        sb.append(factoryClassName);
        sb.append("();\n}\n}");
        return sb.toString();
    }

}
