package chandao.gui;


import chandao.bean.TaskDetail;
import chandao.util.TaskDetailUtil;
import com.intellij.util.ui.JBHtmlEditorKit;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

public class TaskWindowGui {
    private JPanel mainPanel;
    private JPanel taskDescribe;
    private JLabel describeLabel;
    private JLabel historyLabel;
    private JScrollPane scrollPane;

    private void init(TaskDetail taskDetail) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        describeLabel.setText(TaskDetailUtil.createdDescribeHtml(taskDetail.getTaskDescribes()));
        historyLabel.setText(TaskDetailUtil.createdHistory(taskDetail.getTaskHistories()));
    }
    public TaskWindowGui(TaskDetail taskDetail) {
        init(taskDetail);
    }

    public JPanel getTaskDescribe() {
        return taskDescribe;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
