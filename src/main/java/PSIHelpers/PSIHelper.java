package PSIHelpers;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PSIHelper {

    public static PsiFile openFile(String fqn, String format) {
        String filename = fqn;
        if (fqn.contains(".")) {
            filename = fqn.substring(fqn.lastIndexOf('.') + 1);
        }
        if (filename.contains("$")) {
            filename = filename.substring(0, filename.indexOf('$'));
        }

        filename += format;
        @NotNull Project[] p = ProjectManager.getInstance().getOpenProjects();

        Project project = p[0];
        final PsiFile[] files = FilenameIndex.getFilesByName(project, filename, GlobalSearchScope.allScope(project));
        if (files != null && files.length > 0) {
            PsiFile file = files[0];

            OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file.getVirtualFile());
            descriptor.navigateInEditor(project, true);

            return files[0];
        }
        return null;
    }

    public static void appendFile(PsiFile file, String data, String insertBeforeLastOccurenceOf){
        @NotNull Project[] p = ProjectManager.getInstance().getOpenProjects();
        Project project = p[0];
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file.getVirtualFile());
        StringBuilder src = new StringBuilder(file.getText());
        int i = src.lastIndexOf(insertBeforeLastOccurenceOf);
        src.insert(i,data );
        try {
            descriptor.getFile().setBinaryContent(src.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
