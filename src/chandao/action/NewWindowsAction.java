package chandao.action;

import chandao.bean.TaskItem;
import chandao.data.LogInData;
import chandao.gui.TaskWindow;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class NewWindowsAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        TaskItem selectedValue = LogInData.listTask.getSelectedValue();
        if (selectedValue != null) {
            TaskWindow task = new TaskWindow(selectedValue.getTaskName());
            System.out.println(selectedValue);
        }
    }
    public NewWindowsAction() {
        super("详细信息", "详细信息", AllIcons.Actions.PreviewDetails);
    }
}
