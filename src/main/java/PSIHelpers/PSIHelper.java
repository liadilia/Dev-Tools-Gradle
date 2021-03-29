package PSIHelpers;

import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.FilenameIndex;
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
        VirtualFile vFile=file.getVirtualFile();
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, vFile);
        descriptor.navigateInEditor(project, true);
        StringBuilder src = new StringBuilder(file.getText());
        int i = src.lastIndexOf(insertBeforeLastOccurenceOf);
        src.insert(i,data );
        try {
            vFile.setBinaryContent(src.toString().getBytes("utf-8"));
          System.out.println(file.getNode().getChildren(null)) ;

        } catch (IOException e) {
            e.printStackTrace();
        }
        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        Document document = fileDocumentManager.getDocument(vFile);
        PsiDocumentManager manager = PsiDocumentManager.getInstance(project);
        manager.commitDocument(document);

    }


    public static void appendFileAfterOccurence(PsiFile file, String data, String landmark){
        @NotNull Project[] p = ProjectManager.getInstance().getOpenProjects();
        Project project = p[0];
        OpenFileDescriptor descriptor = new OpenFileDescriptor(project, file.getVirtualFile());
        StringBuilder src = new StringBuilder(file.getText());
        int i = src.indexOf(landmark,0);
        src.insert(i,data );
        WriteCommandAction.runWriteCommandAction(project, new Runnable() {
            @Override
            public void run() {
                try {
                    descriptor.getFile().setBinaryContent(src.toString().getBytes("utf-8"));
                    FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
                    Document document = fileDocumentManager.getDocument(descriptor.getFile());
                    if (document != null) {
                        fileDocumentManager.saveDocument(document);
                    }
                    PsiDocumentManager manager = PsiDocumentManager.getInstance(project);
                    manager.commitDocument(document);
                    CodeStyleManager styleManager = CodeStyleManager.getInstance(project);

                    styleManager.reformat(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }



    public static PsiDirectory createDirectory(PsiDirectory parent, String name)
            throws IncorrectOperationException {
        Project p = ProjectManager.getInstance().getDefaultProject();
        final PsiDirectory[] result = {null};

        for (PsiDirectory dir : parent.getSubdirectories()) {
            if (dir.getName().equalsIgnoreCase(name)) {
                result[0] = dir;
                break;
            }
        }

        if (null == result[0]) {
            WriteCommandAction.runWriteCommandAction(p, new Runnable() {
                @Override
                public void run() {
                    result[0] = parent.createSubdirectory(name);
                }
            });

        }

        return result[0];
    } // createDirectory()



    public static PsiFile createFileInDirectory(final PsiDirectory directory, String name, String content, String language) throws IncorrectOperationException {
        @NotNull Project[] p = ProjectManager.getInstance().getOpenProjects();
        Project project = p[0];
        final PsiFile currentFile = directory.findFile(name);
        if (currentFile != null) {
           return currentFile;
        }
        final PsiFileFactory factory = PsiFileFactory.getInstance(directory.getProject());

        final PsiFile file = factory.createFileFromText(name, Language.findLanguageByID(language), content);

        //format the code
        CodeStyleManager styleManager = CodeStyleManager.getInstance(project);
        WriteCommandAction.runWriteCommandAction(project, new Runnable() {
            @Override
            public void run() {
                styleManager.reformat(file);
            }
        });

       return (PsiFile) directory.add(file);
    }



    public static PsiFile getContainingFileInDir(String fqn, PsiDirectory dir) {
        String filename = fqn;
        if (fqn.contains(".")) {
            filename = fqn.substring(fqn.lastIndexOf('.') + 1);
        }
        if (filename.contains("$")) {
            filename = filename.substring(0, filename.indexOf('$'));
        }
        filename += ".java";
        Project[] p = ProjectManager.getInstance().getOpenProjects();

        Project project = p[0];
        final PsiFile[] files = FilenameIndex.getFilesByName(project, filename, GlobalSearchScope.allScope(project));
        PsiFile file = files[0];
        if (files != null && files.length > 0) {
            boolean foundMatch = false;
            for (int i=0; i<files.length; i++){
                file=files[i];
         //       if (file.getContainingDirectory()!= dir){
                if (file.getContainingDirectory().equals(dir)){
                    System.out.println(file.getContainingDirectory());
                    System.out.println(dir);
                    foundMatch=true;
                    break;
                } else {
                    foundMatch=false;

                }


            }
         if (foundMatch)  {
             System.out.println("I returned"+file.getContainingDirectory());
             return file;
         }


        }
        return null;
    }

    public static PsiFile getContainingFile(String fqn) {
        String filename = fqn;
        if (fqn.contains(".")) {
            filename = fqn.substring(fqn.lastIndexOf('.') + 1);
        }
        if (filename.contains("$")) {
            filename = filename.substring(0, filename.indexOf('$'));
        }
        filename += ".java";
        Project[] p = ProjectManager.getInstance().getOpenProjects();

        Project project = p[0];
        final PsiFile[] files = FilenameIndex.getFilesByName(project, filename, GlobalSearchScope.allScope(project));

        if (files != null && files.length > 0) {
            PsiFile file = files[0];

                return file;
            }

        return null;


    }














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
//Not functional yet
        PsiFile[] files= getFilesByName(p,name, GlobalSearchScope.allScope(p));
        System.out.println(files[0].getName());
        if (files[0].isDirectory())
            return files[0];
        else return null;
    }

    public static void createFile(Project p, String name){
        PsiFileFactory fileFactory = PsiFileFactory.getInstance(p);
        PsiFile file = fileFactory.createFileFromText(name, Language.ANY,"blabla");


    }











}
