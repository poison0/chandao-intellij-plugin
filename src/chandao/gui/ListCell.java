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
        if (value.getType() == 0 || value.getType() == 1) {
//            String headIcon = " \uD83D\uDC2C ";
//            if (value.getType() == 1) {
//                headIcon = " \uD83D\uDC1E ";
//            }
            if (value.getP() == null || value.getP().equals("")) {
                String text = "<html>" +
                        "    <div>" +
                        "        <div style=\"float: left;\" >" +
                        "            <font color='#808080'> &nbsp&nbsp#"+value.getId()+"</font>" +
                        "            "+value.getTaskName()+"" +
                        "        </div>" +
                        "    </div>" +
                        "</html>";
                setText(text);
            }else{
                String p = value.getP();
                switch (p) {
                    case "中":p = "<font color='#A0D0F2'>"+p+"</font>";break;
                    case "高":p = "<font color='#FF9902'>"+p+"</font>";break;
                    case "紧急":p = "<font color='#D50202'>"+p+"</font>";break;
                    case "已上线":p = "<font color='#A0D0F2'>"+p+"</font>";break;
                    case "低":p = "<font color='#029789'>"+p+"</font>";break;
                    case "当前周完成":p = "<font color='#818181'>"+p+"</font>";break;
                    case "bug转入":p = "<font color='#A0D0F2'>"+p+"</font>";break;
                    case "敏捷":p = "<font color='#A0D0F2'>"+p+"</font>";break;
                    case "下周完成":p = "<font color='#A0D0F2'>"+p+"</font>";break;
                    case "轻微":p = "<font color='#FDDAAC'>"+p+"</font>";break;
                    case "一般":p = "<font color='#FFBF53'>"+p+"</font>";break;
                    case "严重":p = "<font color='#FFFFFF'>"+p+"</font>";break;
                    default:
                }

                String text = "<html>" +
                        "    <div>" +
                        "        <div >" +
                        "            <font color='#808080'> &nbsp&nbsp#"+value.getId()+"</font>" +
                        "            "+p+"  "+value.getTaskName()+"" +
                        "        </div>" +
                        "    </div>" +
                        "</html>";
                setText(text);
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
            if (value.getType() == 2) {
                //标题
                setText(" \uD83D\uDD28 "+value.getTaskName());
            }else {
                setText(" \uD83D\uDD27 "+value.getTaskName());
            }
            setFont(new Font(Font.DIALOG, Font.BOLD, 18));
            Color color = new JBColor(new Color(4, 121, 232), new Color(152, 202, 245));
            setForeground(color);

        }
        return this;
    }
}
