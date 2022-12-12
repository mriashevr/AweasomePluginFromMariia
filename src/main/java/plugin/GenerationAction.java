package plugin;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerationAction extends EditorAction {

    public GenerationAction() {
        this(new CustomHandler());
    }

    protected GenerationAction(EditorActionHandler defaultHandler) {
        super(defaultHandler);
    }

    private static class CustomHandler extends EditorWriteActionHandler {
        public CustomHandler() {
        }

        @Override
        public void executeWriteAction(Editor editor, DataContext dataContext) {

            System.out.println(editor.getProject().getBasePath());
            System.out.println(editor.getProject().getProjectFilePath());
            InputData dialog = new InputData();

            dialog.pack();
            dialog.setVisible(true);
            if (dialog.GetOKPressed()) {
                if(dialog.getCreateNewFileCheckBox().isSelected()) {
                    File requestedFile = new File(editor.getProject().getBasePath() + "/src", dialog.getClassNameData() + ".java");
                    try {
                        requestedFile.createNewFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    var code = dialog.ClassCodeGeneration();
                    try (FileWriter writer = new FileWriter(requestedFile, false)) {
                        writer.write("package ... \n" +
                                "import lombok.Data;\n\n" + code);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else{
                    var selectionModel = editor.getSelectionModel();
                    var start = selectionModel.getSelectionStart();
                    var end = selectionModel.getSelectionEnd();
                    var code = dialog.ClassCodeGeneration();
                    editor.getDocument().replaceString(start, end , code);
                }
            }
        }
    }
}
