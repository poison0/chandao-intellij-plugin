package chandao.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import static chandao.data.LogInData.clean;

public class SignOutAction extends AnAction {

    public SignOutAction() {
        super("退出", "退出", AllIcons.Actions.PopFrame);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        clean();
    }
}
