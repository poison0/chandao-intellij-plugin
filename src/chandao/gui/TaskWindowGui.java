package chandao.gui;


import chandao.bean.TaskDetail;
import chandao.util.TaskDetailUtil;

import javax.swing.*;

public class TaskWindowGui {
    private JPanel mainPanel;
    private JPanel taskDescribe;
    private JPanel historyRecord;
    private JLabel describeLabel;
    private JLabel historyLabel;

    private void init(TaskDetail taskDetail) {
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
