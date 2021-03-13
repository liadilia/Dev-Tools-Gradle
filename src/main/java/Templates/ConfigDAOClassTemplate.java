package Templates;

import SystemConfigGenerator.ConfigOption;

import java.util.List;
import java.util.Locale;

public class ConfigDAOClassTemplate {

    public static String fillTemplate(String category, List<ConfigOption> options){
     StringBuilder sb = new StringBuilder();
     String className = category+"ConfigDAOImpl";
    sb.append("package de.imc.clix.core.configuration.");
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



    sb.append("\n}");
        return sb.toString();
    }




}


/*



    @Override
    public void setParameter(final Configuration configuration, final String name, final String value) {
        final Shopify shopify = configuration.getShopify();
        switch (name) {
            case "shopify.baseUrl":
                shopify.setBaseUrl(value);
                break;
            case "shopify.apiVersion":
                shopify.setApiVersion(value);
                break;
            case "shopify.apiUser":
                shopify.setApiUser(value);
                break;
            case "shopify.apiPassword":
                shopify.setApiPassword(value);
                break;
            case "shopify.apiSecret":
                shopify.setApiSecret(value);
                break;
            case "shopify.apiProductGetPath":
                shopify.setApiProductGetPath(value);
                break;
            case "shopify.apiProductPostPath":
                shopify.setApiProductPostPath(value);
                break;
            case "shopify.apiProductPutPath":
                shopify.setApiProductPutPath(value);
                break;
            default:
                LOGGER.error("Unknown parameter, {} : {}", name, value);
                break;
        }
    }

    @Override
    public List<ConfigurationParameter> getParameters(final Configuration configuration) {
        final List<ConfigurationParameter> parameters = new ArrayList<>();
        final Shopify shopify = configuration.getShopify();
        if (shopify != null) {
            parameters.add(new ConfigurationParameter("shopify.baseUrl", shopify.getBaseUrl()));
            parameters.add(new ConfigurationParameter("shopify.apiVersion", shopify.getApiVersion()));
            parameters.add(new ConfigurationParameter("shopify.apiUser", shopify.getApiUser()));
            parameters.add(new ConfigurationParameter("shopify.apiPassword", shopify.getApiPassword()));
            parameters.add(new ConfigurationParameter("shopify.apiSecret", shopify.getApiSecret()));
            parameters.add(new ConfigurationParameter("shopify.apiProductGetPath", shopify.getApiProductGetPath()));
            parameters.add(new ConfigurationParameter("shopify.apiProductPostPath", shopify.getApiProductPostPath()));
            parameters.add(new ConfigurationParameter("shopify.apiProductPutPath", shopify.getApiProductPutPath()));
        }
        return parameters;
    }
}*/
