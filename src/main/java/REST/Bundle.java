package REST;

import java.util.ArrayList;
import java.util.List;

public class Bundle {
    public final String component = "default";
    public final String part = "default";
    public final String services = ":backend:";
    public List<String> bundleLocations = new ArrayList<>();
    public String identifier;
    public String context;
    public List<Translations> translations;
    public boolean fullTranslated;
    public String createdBy;
    public boolean staysEmpty = false;
    public boolean isLocked = false;

    public Bundle(String identifier, String context, String createdBy) {
        this.identifier = identifier;
        this.context = context;
    }

    public void addTranslation(Translations t) {
        if (translations == null) {
            translations = new ArrayList<>();
        }
        translations.add(t);

    }

    public static class Translations {
        public String language, code, value;

        public Translations(String language, String code, String value) {
            this.language = language;
            this.code = code;
            this.value = value;
        }
    }
}