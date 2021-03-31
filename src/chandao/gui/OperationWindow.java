package chandao.gui;

import chandao.bean.TaskItem;
import chandao.data.LogInData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperationWindow {

    private JPanel contentPanel;
    private JList<TaskItem> listTask;

    private void init(ToolWindow toolWindow) {

        listTask.setModel(LogInData.LIST_MODEL);
        listTask.setCellRenderer(new ListCell());
        listTask.setVisible(true);
    }


    public OperationWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init(toolWindow);
        listTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("双击");
                    System.out.println(listTask.getSelectedValue());
                }
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
