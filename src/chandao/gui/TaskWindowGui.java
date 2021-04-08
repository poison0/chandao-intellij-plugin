package chandao.gui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;

import javax.swing.*;
import java.awt.*;

public class TaskWindowGui {
    private JPanel mainPanel;
    private JPanel taskDescribe;
    private JPanel historyRecord;
    private JLabel pic;
    private JTextPane textPane1;
    private JLabel history;

    private void init() {

        pic.setText("<html>" +
                    " <div>  " +
                    "   <img  width=\"900\" src='http://work.ruiyunnet.com/biz/file-read-344282.png' />" +
                    " </div>" +
                    " <div>  " +
                    "   <img  width=\"900\" src='http://work.ruiyunnet.com/biz/file-read-344282.png' />" +
                    " </div>" +
                    "</html>");

        textPane1.setEditable(false);
        textPane1.setText(
                "   2020-12-24 18:24:33, 由 hyyao 创建。\n" +
                "   2020-12-25 10:40:18, 由 詹淑英 指派给 吕强鹏。\n" +
                "   2021-04-08 15:15:35, 由 吕强鹏 指派给 牛顺顺。\n"
                );
    }
    public TaskWindowGui() {
        init();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
