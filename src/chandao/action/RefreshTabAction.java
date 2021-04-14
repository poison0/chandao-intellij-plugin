package chandao.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import org.jetbrains.annotations.NotNull;

import static chandao.query.Login.refresh;

public class RefreshTabAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        refresh(anActionEvent.getProject());
    }
    public RefreshTabAction() {
        super("刷新", "刷新", AllIcons.Actions.Refresh);
    }
}
