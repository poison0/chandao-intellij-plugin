package chandao.action;

import chandao.gui.TaskWindow;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class NewWindowsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
//        TaskWindow task = new TaskWindow("测试");
//        System.out.println(task);
    }
    public NewWindowsAction() {
        super("详细信息", "详细信息", AllIcons.Actions.PreviewDetails);
    }
}
