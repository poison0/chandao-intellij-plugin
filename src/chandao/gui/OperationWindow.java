package chandao.gui;

import chandao.data.LogInData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class OperationWindow {

    private JPanel contentPanel;
    private JTable table;

    private void init(ToolWindow toolWindow) {
        table.setModel(LogInData.TABLE_MODEL);
        TableColumnModel cm = table.getColumnModel();
        JTableHeader tableHeader = table.getTableHeader();

        TableColumn columnID = cm.getColumn(0);
        columnID.setPreferredWidth(40);
        columnID.setMinWidth(40);
        TableColumn columnP = cm.getColumn(1);
        columnP.setPreferredWidth(40);
        columnP.setMinWidth(40);
        TableColumn columnT = cm.getColumn(2);
        columnT.setPreferredWidth(400);
        table.setEnabled(true);
    }


    public OperationWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init(toolWindow);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }


}
