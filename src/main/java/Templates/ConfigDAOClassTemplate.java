package Templates;

import SystemConfigGenerator.ConfigOption;

import java.util.List;
import java.util.Locale;

public class ConfigDAOClassTemplate {

    public static String fillTemplate(String category, List<ConfigOption> options) {
        StringBuilder sb = new StringBuilder();
        String className = category + "ConfigDAOImpl";
        sb.append("package configuration.");
        sb.append(category.toLowerCase(Locale.ROOT));
        sb.append(";");
        sb.append("\n\n");

        sb.append("import de.imc.clix.core.configuration.ConfigDAOImpl;");
        sb.append("\n");
        sb.append("import de.imc.clix.core.configuration.Configuration;");
        sb.append("\n");
        sb.append("import de.imc.clix.core.configuration.ConfigurationParameter;");
        sb.append("\n");
        sb.append("import org.slf4j.Logger;");
        sb.append("\n");
        sb.append("import org.slf4j.LoggerFactory;");
        sb.append("\n");
        sb.append("import java.util.ArrayList;");
        sb.append("\n");
        sb.append("import java.util.List;");
        sb.append("\n");

        sb.append("public class ");
        sb.append(className);
        sb.append(" extends ConfigDAOImpl {");
        sb.append("\n\n");
        sb.append(" private static final Logger LOGGER = LoggerFactory.getLogger(");
        sb.append(className);
        sb.append(".class;");
        sb.append("\n\n");
        sb.append("@Override\n");

        sb.append("  public void init(final Configuration configuration) {\n");
        sb.append("\n");
        sb.append("   if (configuration.get");
        sb.append(category);
        sb.append("() == null) {");
        sb.append("\n");
        sb.append("configuration.set");
        sb.append(category);
        sb.append("(new ");
        sb.append(category);
        sb.append("());");
        sb.append("\n}");
        sb.append("\n}");

        sb.append("@Override\n");
        sb.append("  public void setParameter(final Configuration configuration, final String name, final String value) {\n");
        sb.append(" final ");
        sb.append(category);
        sb.append(" ");
        sb.append(category.toLowerCase(Locale.ROOT));
        sb.append(" = configuration.get");
        sb.append(category);
        sb.append("();\n");
        sb.append(" switch (name) {\n");
        for (ConfigOption option : options) {
            sb.append(" case \"");
            sb.append(category.toLowerCase());
            sb.append(".");
            sb.append(option.getTitle());
            sb.append("\":\n");
            sb.append(category.toLowerCase());
            sb.append(".");
            sb.append("set");
            sb.append(option.getTitle());
            sb.append("(value);\nbreak;\n");
        }
        sb.append("default:\n");
        sb.append(" LOGGER.error(\"Unknown parameter, {} : {}\", name, value);\n");
        sb.append("break;\n}\n}\n");

        sb.append("@Override\n");
        sb.append(" public List<ConfigurationParameter> getParameters(final Configuration configuration) {\n");
        sb.append("  final List<ConfigurationParameter> parameters = new ArrayList<>();\n");
        sb.append("final ");
        sb.append(category);
        sb.append(" ");
        sb.append(category.toLowerCase());
        sb.append(" = configuration.get");
        sb.append(category);
        sb.append("();\n");

        sb.append("if (");
        sb.append(category.toLowerCase());
        sb.append(" != null) {\n");

        for (ConfigOption option : options) {

            sb.append("parameters.add(new ConfigurationParameter(\"");
            sb.append(category.toLowerCase());
            sb.append(".");
            sb.append(option.getTitle());
            sb.append("\", ");
            sb.append(category.toLowerCase());
            sb.append(".get");
            sb.append(option.getTitle());
            sb.append("()));\n");
        }
        sb.append("\n}\n");
        sb.append("return parameters;\n");
        sb.append("\n}");

        sb.append("\n}");

        return sb.toString();
    }


}

