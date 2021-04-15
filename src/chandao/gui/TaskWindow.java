package chandao.gui;


import chandao.bean.TaskItem;
import chandao.util.TaskDetailUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TaskWindow extends JFrame {

    public TaskWindow(Project project,TaskItem item) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Loading...") {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                setupView(item);
                setTitle("#" + item.getId() + " " + item.getP() + " " + item.getTaskName());
            }
        });
    }
    private void setupView(TaskItem item) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = screenSize.width / 2;
        int height = screenSize.height / 2;

        height = height == 0 ? 500 : height;
        width = width == 0 ? 300 : width;

        setPreferredSize(new Dimension(width, height));
        TaskWindowGui taskWindowGui = new TaskWindowGui(TaskDetailUtil.generateDetails(item));
        add(taskWindowGui.getMainPanel(), BorderLayout.CENTER);
        setSize(new Dimension(width, height));

        int locationWidth = (screenSize.width / 2 - width / 2) +  20;
        int locationHeight = (screenSize.height / 2 - height / 2) +  20;
//
        setLocation(locationWidth, locationHeight);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        java.awt.EventQueue.invokeLater(() -> {
            setVisible(true);
            toFront();
            repaint();
        });
    }
}
