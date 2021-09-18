package Templates;

import SystemConfigGenerator.ConfigOption;

import java.util.List;
import java.util.Locale;

public class BaseClassTemplate {

    public static String fillTemplate(String category, List<ConfigOption> options) {
        StringBuilder sb = new StringBuilder();
        sb.append("package configuration.");
        sb.append(category.toLowerCase(Locale.ROOT));
        sb.append(";\n");
        sb.append("public class ");
        sb.append(category);
        sb.append("{\n");

        for (ConfigOption option : options) {
            sb.append("private ");
            sb.append(option.getType());
            sb.append(" ");
            sb.append(option.getTitle());
            sb.append(";\n");
        }

        for (ConfigOption option : options) {
            sb.append("public ");
            sb.append(option.getType());
            sb.append(" ");
            sb.append("get");
            sb.append(option.getTitle());
            sb.append(" () {\n");
            sb.append("return ");
            sb.append(option.getTitle());
            sb.append(";");
            sb.append("\n");
            sb.append("}");
            sb.append("public void ");
            sb.append("set");
            sb.append(option.getTitle());
            sb.append(" () {\n\n");
            sb.append("this. ");
            sb.append(option.getTitle());
            sb.append(" = ");
            sb.append(option.getTitle());
            sb.append(";\n}");
        }

        sb.append("}");

        return sb.toString();

    }
}
