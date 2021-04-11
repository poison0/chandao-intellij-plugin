package chandao.gui;

import chandao.bean.TaskItem;
import chandao.data.LogInData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperationWindow {

    private JPanel contentPanel;
    private JList<TaskItem> listTask;

    private void init(ToolWindow toolWindow) {
        listTask.setFixedCellHeight(40);
        listTask.setModel(LogInData.LIST_MODEL);
        listTask.setCellRenderer(new ListCell());
        listTask.setVisible(true);
        //赋值给全局变量
        LogInData.listTask = listTask;
    }


    public OperationWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init(toolWindow);
        listTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("双击");
                    System.out.println(listTask.getSelectedValue());
                    TaskItem selectedValue = LogInData.listTask.getSelectedValue();
                    if (selectedValue != null) {
                        TaskWindow task = new TaskWindow(selectedValue.getTaskName());
                        System.out.println(selectedValue);
                    }
                }
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
