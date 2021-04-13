package chandao.gui;

import chandao.bean.TaskItem;
import com.intellij.icons.AllIcons;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

/**
 * 重写显示样式
 * @author nss
 * @date 2021/3/29
 */
public class ListCell extends JLabel implements ListCellRenderer<TaskItem>{
    @Override
    public Component getListCellRendererComponent(JList<? extends TaskItem> list, TaskItem value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value.getType() != 2) {
            if (value.getP() == null || value.getP().equals("")) {
                setText(" \uD83D\uDC2C #"+value.getId()+"  （子）  "+value.getTaskName());
            }else{
                setText(" \uD83D\uDC2C #"+value.getId()+"  "+value.getP()+"  "+value.getTaskName());
            }
//            setText("<html>" +
//                    "<span> \uD83D\uDC2C </span>" +
//                    "<span>#"+value.getId()+" </span>" +
//                    "<span style='background-color:red'> "+value.getP()+" </span>" +
//                    "<span> "+value.getTaskName()+" </span>" +
//                    "</html>");
            //判断是否选中
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
        }else{
            //🔧🐞🐌
            if (isSelected) {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }else{
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            //标题
            setText(" \uD83D\uDD28 "+value.getTaskName());
            setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            Color color = new JBColor(new Color(4, 121, 232), new Color(152, 202, 245));
            setForeground(color);

        }
        return this;
    }
}
