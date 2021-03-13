package PSIHelpers;

import com.intellij.lang.Language;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.text.StringTokenizer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static com.intellij.psi.search.FilenameIndex.getFilesByName;

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
        final PsiFile[] files = getFilesByName(project, filename, GlobalSearchScope.allScope(project));
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

    public static PsiDirectory createDirectory(PsiDirectory parent, String name)
            throws IncorrectOperationException {
        PsiDirectory result = null;

        for (PsiDirectory dir : parent.getSubdirectories()) {
            if (dir.getName().equalsIgnoreCase(name)) {
                result = dir;
                break;
            }
        }

        if (null == result) {
            result = parent.createSubdirectory(name);
        }

        return result;
    } // createDirectory()

    public static PsiDirectory createPackage(PsiDirectory sourceDir, String qualifiedPackage)
            throws IncorrectOperationException {
        PsiDirectory parent = sourceDir;
        StringTokenizer token = new StringTokenizer(qualifiedPackage, ".");
        while (token.hasMoreTokens()) {
            String dirName = token.nextToken();
            parent = createDirectory(parent, dirName);
        }
        return parent;
    } // createPackage()

    public static PsiFile getDirectoryByName(Project p, String name){

      PsiFile[] files= getFilesByName(p, name, GlobalSearchScope.projectScope(p));
      if (files[0].isDirectory())
      return files[0];
      else return null;
    }
    public static void createFile(Project p, String name){
        PsiFileFactory fileFactory = PsiFileFactory.getInstance(p);
        PsiFile file = fileFactory.createFileFromText(name, Language.ANY,"blabla");


    }

    public static PsiFile createFromTemplate(final PsiDirectory directory, String name) throws IncorrectOperationException {

        final PsiFile currentFile = directory.findFile(name);
        if (currentFile != null) {
           return currentFile;
        }
        final PsiFileFactory factory = PsiFileFactory.getInstance(directory.getProject());


        String content = "bla bla";

        final PsiFile file = factory.createFileFromText(name, Language.findLanguageByID("JAVA"), content);
       return (PsiFile) directory.add(file);
    }

}
