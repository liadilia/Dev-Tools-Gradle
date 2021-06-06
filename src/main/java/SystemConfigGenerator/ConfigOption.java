package SystemConfigGenerator;

import java.awt.*;

public class ConfigOption extends Component {


    String title;
    String type;



    public ConfigOption(String title, String type){
        this.title = title;
        this.type=type;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
