package chandao.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NewWindowPanel extends DialogWrapper {

    protected NewWindowPanel(@Nullable Project project, boolean canBeParent) {
        super(project, canBeParent);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return null;
    }
}
