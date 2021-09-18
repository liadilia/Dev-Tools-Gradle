package REST;

public interface RestEndpoints {

    //API endpoints used by the APP

    //Propadmin endpoints
    final String JiraIssueRoot = "JIRA_ISSUE_ROOT";
    final String JiraProject = "JIRA_PROJECT";
    final String JiraSubtaskId = "JIRA_SUBTASK_ID";
    final String propadminAuth = "LOCALIZATION_AUTH_ENDPOINT";
    final String propadminCreateBundleEndpoint = "LOCALIZATION_BUNDLE_ENDPOINT";

    String JIRA_COMMENT = "/comment/";


    // methods
    String methodPOST = "POST";
    String methodPUT = "PUT";
    

}
