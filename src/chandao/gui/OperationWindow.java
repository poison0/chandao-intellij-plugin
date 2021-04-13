package chandao.gui;

import chandao.bean.TaskItem;
import chandao.data.LogInData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperationWindow {

    private JPanel contentPanel;
    private JList<TaskItem> listTask;
    private JTextPane textPane1;
    private JButton button1;

    private void init(ToolWindow toolWindow) {
        listTask.setFixedCellHeight(35);
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
                        new TaskWindow(selectedValue);
                    }
                }
            }
        });
        listTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //右键复制到粘贴板
                if (e.getButton() == 3) {
                    System.out.println("复制到"+listTask.getSelectedValue().toString());
                    setClipboardString(listTask.getSelectedValue().toString());
                }
            }
        });
    }
    /**
     * 把文本设置到剪贴板（复制）
     */
    public static void setClipboardString(String text) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
