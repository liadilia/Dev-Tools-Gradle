package REST.Helpers;

import com.google.gson.Gson;

public class JiraRequestHelper {

    public static String getJiraSubTaskCreationRequestBody(String parentId, String description) {
        JiraTask jt = new JiraTask(parentId, description);

        return new Gson().toJson(jt);
    }

    public static String getJiraTaskAssignmentToUserBody(String assignee) {
        TaskAssignment tA = new TaskAssignment(assignee);

        return new Gson().toJson(tA);
    }

    public static class TaskAssignment {

        TaskAssignmentFields fields;

        public TaskAssignment(String assignee) {
            this.fields = new TaskAssignmentFields(assignee);
        }


        public class TaskAssignmentFields {
            Assignee assignee;

            public TaskAssignmentFields(String assignee) {
                this.assignee = new Assignee(assignee);
            }

            public class Assignee {

                String name;

                public Assignee(String assignee) {
                    this.name = assignee;
                }
            }


        }
    }
}
