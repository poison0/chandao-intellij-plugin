package chandao.action;

import chandao.gui.TaskWindow;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class NewWindowsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        TaskWindow task = new TaskWindow(" 56846（掌中宝交易MB）（拣货单bug）手机端拣货单跟PC端显示的顺序不一致（微微）（dear艳艳）");
        System.out.println(task);
    }
    public NewWindowsAction() {
        super("详细信息", "详细信息", AllIcons.Actions.PreviewDetails);
    }
}
