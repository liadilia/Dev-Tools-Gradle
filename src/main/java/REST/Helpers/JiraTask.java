package REST.Helpers;

//import sun.security.x509.IssuerAlternativeNameExtension;

import LaunchDevTools.PluginConfigurationStrings;

public class JiraTask {
//https://developer.atlassian.com/server/jira/platform/jira-rest-api-example-create-issue-7897248/
    JiraTaskFields fields;
    public JiraTask(String parentKey, String description){
    this.fields = new JiraTaskFields(parentKey,description);
};
    public JiraTaskFields getTask(){
    return fields;
}

    public static class JiraTaskFields {
    Project project = new Project();
    Parent parent;
    String summary = "Insert SQL for new meta tag";
    String description;
    IssueType issuetype = new IssueType();

    public JiraTaskFields (String parentKey, String description){
        this.parent=new Parent(parentKey);
        this.description = description;
    }
    public class Project{
        String key = PluginConfigurationStrings.jiraProject;
    }
    public class Parent {
        String key;
        public Parent(String key){
            this.key=key;
        }
    }
    public class IssueType{
        String id=PluginConfigurationStrings.jiraSubtaskId;
    }
  }
}
