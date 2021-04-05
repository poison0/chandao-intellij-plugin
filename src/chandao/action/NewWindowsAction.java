package chandao.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class NewWindowsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

    }
    public NewWindowsAction() {
        super("详细信息", "详细信息", AllIcons.Actions.PreviewDetails);
    }
}
