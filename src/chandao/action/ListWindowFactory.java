package chandao.action;

import chandao.gui.AddTabAction;
import chandao.gui.OperationWindow;
import chandao.gui.ToolWindowPanel;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ListWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        OperationWindow noteListWindow = new OperationWindow(project, toolWindow);
        ContentFactory instance = ContentFactory.SERVICE.getInstance();
        JPanel contentPanel = noteListWindow.getContentPanel();
        ToolWindowPanel panel = new ToolWindowPanel();
        panel.setToolbar(createToolBar().getComponent());
        panel.setContent(contentPanel);
        Content content = instance.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private ActionToolbar createToolBar() {
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new AddTabAction());
        group.add(new RefreshTabAction());
        group.add(new NewWindowsAction());
        group.add(new SignOutAction());
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.TOOLBAR, group, false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        return toolbar;
    }
}
