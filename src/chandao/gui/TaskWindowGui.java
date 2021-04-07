package chandao.gui;

import com.intellij.icons.AllIcons;

import javax.swing.*;
import java.awt.*;

public class TaskWindowGui {
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel taskDescribe;
    private JPanel historyRecord;

    private void init() {
        titlePanel.setPreferredSize(new Dimension(300,40));
        titleLabel.setText(" 56846（掌中宝交易MB）（拣货单bug）手机端拣货单跟PC端显示的顺序不一致（微微）（dear艳艳）");
        titleLabel.setIcon(AllIcons.FileTypes.Any_type);
    }
    public TaskWindowGui() {
        init();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
