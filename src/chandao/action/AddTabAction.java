package chandao.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class AddTabAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        LgDialog lgDialog = new LgDialog();
        lgDialog.showThisDialog();
        System.out.println("AddTabAction.actionPerformed");
    }
    public AddTabAction() {
        super("登录", "登录按钮", AllIcons.General.Add);
    }
}
