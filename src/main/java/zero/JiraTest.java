package zero;

/*import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;*/
import REST.RestEndpoints;


import java.net.URI;

class JiraTest {

    public static void main(String[] args) throws Throwable {

       URI url = new URI("https://jira.im-c.de/");
      /* JerseyJiraRestClient client = new JerseyJiraRestClient(url, new BasicHttpAuthenticationHandler(RestEndpoints.JIRA_USER, RestEndpoints.JIRA_PASS));
        client.getVersionRestClient();
        client.getIssueClient().getIssue("LMSILS-20127", new NullProgressMonitor());*/
    }
}