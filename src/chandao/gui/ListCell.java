package chandao.gui;

import chandao.bean.TaskItem;
import com.intellij.application.options.codeStyle.arrangement.util.CalloutBorder;
import com.intellij.designer.designSurface.feedbacks.LineMarginBorder;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.image.ColorModel;

/**
 * 重写显示样式
 * @author nss
 * @date 2021/3/29
 */
public class ListCell extends JLabel implements ListCellRenderer<TaskItem>{
    @Override
    public Component getListCellRendererComponent(JList<? extends TaskItem> list, TaskItem value, int index, boolean isSelected, boolean cellHasFocus) {
        setText("  #"+value.getId()+"  "+value.getP()+"  "+value.getTaskName());
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
        return this;
    }
}
