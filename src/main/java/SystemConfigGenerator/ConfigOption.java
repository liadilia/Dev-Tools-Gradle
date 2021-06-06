package SystemConfigGenerator;

import java.awt.*;

public class ConfigOption extends Component {


    String title;
    ConfigType type;

    enum ConfigType{
        String
    }

    public ConfigOption(String title, ConfigType type){
        this.title = title;
        this.type=type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setType(ConfigType type){
        this.type = type;
    }

    public ConfigType getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
