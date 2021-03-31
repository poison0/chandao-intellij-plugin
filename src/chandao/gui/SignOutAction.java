package chandao.gui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class SignOutAction extends AnAction {

    public SignOutAction() {
        super("退出", "退出", AllIcons.Actions.PopFrame);
//        super("退出", "退出", AllIcons.Actions.PreviewDetails);
//        super("退出", "退出", AllIcons.Process.Stop);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

    }
}
