package chandao.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperationWindow {

    private JPanel contentPanel;
    private JList list;

    private void init() {
//        table.setModel(LogInData.TABLE_MODEL);
//        table.setEnabled(true);
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        model.addElement("123442333");
        list.setFixedCellHeight(30);
        list.setModel(model);
        list.setEnabled(true);
    }

    public OperationWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init();
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
