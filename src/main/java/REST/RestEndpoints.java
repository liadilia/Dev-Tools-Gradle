package REST;

public interface RestEndpoints {

    //API endpoints used by the APP

    //Propadmin endpoints
    final String JiraIssueRoot = "JIRA_ISSUE_ROOT";
    final String JiraProject ="JIRA_PROJECT";
    final String JiraSubtaskId="JIRA_SUBTASK_ID";
    final String propadminAuth = "LOCALIZATION_AUTH_ENDPOINT";

    String PROPADMIN_AUTH = "https://propadmin.imc-hosting.com/propadmin/rest/auth";
    String PROPADMIN_CREATE_BUNDLE = "https://propadmin.imc-hosting.com/propadmin/rest/products/ILS/versions/14.6/bundles?user=lia.ghita@im-c.com";

    //Jira endpoints

    // /issue supports POST for creating a new issue
   // String JIRA_ISSUE_ROOT = "https://jira.im-c.de/rest/api/2/issue/";
   //String JIRA_ISSUE_ROOT = "https://xamplitude.atlassian.net/rest/api/2/issue/";
    //these endpoints must be appended to the issue root endpoint, after providing the issue ID/Key after the /issue/
    //https://developer.atlassian.com/server/jira/platform/jira-rest-api-examples/

    String JIRA_COMMENT = "/comment/";


    // methods
    String methodPOST = "POST";
    String methodPUT = "PUT";
    String token = "i9At0tY9UOY387P2H7NNDDEE";

}
