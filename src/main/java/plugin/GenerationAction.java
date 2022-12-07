package plugin;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

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
            InputData dialog = new InputData();
            dialog.pack();
            dialog.setVisible(true);
            if (dialog.GetOKPressed()) {
                var selectionModel = editor.getSelectionModel();
                var start = selectionModel.getSelectionStart();
                var end = selectionModel.getSelectionEnd();
                var code = dialog.ClassCodeGeneration();
                editor.getDocument().replaceString(start, end , code);
            }
        }
    }
}
