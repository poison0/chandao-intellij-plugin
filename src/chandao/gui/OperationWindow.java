package chandao.gui;

import chandao.bean.TaskItem;
import chandao.data.LogInData;
import chandao.message.Notifier;
import chandao.query.HttpClient;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OperationWindow {

    private JPanel contentPanel;
    private JList<TaskItem> listTask;
    private JTextPane remarkCon;
    private JButton addButton;
    private JSplitPane splitPane;
    private JLabel titleLabel;

    private void init(ToolWindow toolWindow) {
        listTask.setFixedCellHeight(35);
        listTask.setModel(LogInData.LIST_MODEL);
        listTask.setCellRenderer(new ListCell());
        listTask.setVisible(true);
        Color color = new JBColor(Gray._255, new Color(64, 66, 67));
        listTask.setBackground(color);
        //赋值给全局变量
        LogInData.listTask = listTask;
        splitPane.setDividerLocation(1500);
        splitPane.setDividerSize(3);
        Font font = new Font(Font.DIALOG, Font.ITALIC, 15);
        titleLabel.setFont(font);
        titleLabel.setBackground(color);
        remarkCon.setBorder(new TitledBorder("备注内容"));
        remarkCon.setBackground(new JBColor(Gray._255, new Color(64, 66, 67)));

    }


    public OperationWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init(toolWindow);
        listTask.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TaskItem selectedValue = LogInData.listTask.getSelectedValue();
                if (e.getClickCount() == 2) {
                    if (selectedValue != null) {
                        new TaskWindow(project,selectedValue);
                    }
                }
                if (selectedValue != null && (selectedValue.getType() == 0 || selectedValue.getType() == 1)) {
                    String title = "  #" + selectedValue.getId() + " " + selectedValue.getP() + " " + selectedValue.getTaskName();
                    if (title.length() > 50) {
                        title = title.substring(0, 50) + "...";
                    }
                    titleLabel.setText(title);
                    LogInData.mainChooseItem = selectedValue;
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
        addButton.addActionListener(e -> {
            if (LogInData.mainChooseItem != null) {
                try {
                    if (remarkCon.getText() != null && !"".equals(remarkCon.getText())) {
                        String uid = "";
                        if (LogInData.mainChooseItem.getUid() == null) {
                            HtmlPage click = LogInData.mainChooseItem.getAnchor().click();
                            List<DomElement> uids = click.getElementsById("uid");
                            if (uids != null && uids.size() > 0) {
                                HtmlHiddenInput input = (HtmlHiddenInput) uids.get(0);
                                LogInData.mainChooseItem.setUid(input.getDefaultValue());
                                uid = LogInData.mainChooseItem.getUid();
                            } else {
                                Notifier.notify("uid 为空！", MessageType.ERROR);
                            }
                        } else {
                            uid = LogInData.mainChooseItem.getUid();
                        }
                        post(project,uid, remarkCon.getText(), LogInData.mainChooseItem.getId());
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    Notifier.notify(ioException.getMessage(), MessageType.ERROR);
                }
            }else {
                Notifier.notify("mainChooseItem 为空！", MessageType.ERROR);
            }
        });
    }
    public static void post(Project project,String uid, String comment, String id) {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Loading...") {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                HashMap<String, String> params = new HashMap<>();
                params.put("comment", comment);
                params.put("uid", uid);
                String value = HttpClient.doPostForm("http://work.ruiyunnet.com/biz/action-comment-task-" + id + ".html", params, LogInData.COOKIE);
                System.out.println(value);
                Notifier.notify("添加成功！", MessageType.INFO);
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
