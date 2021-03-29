package chandao.data;

import chandao.bean.TaskItem;
import com.gargoylesoftware.htmlunit.WebClient;
import com.intellij.ui.CollectionComboBoxModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class LogInData {
    //cookie
    public static String COOKIE;
    //登录之后的webClient
    public static WebClient WEB_CLIENT;
    //用户名
    public static String USER_NAME;
    //密码
    public static String PASS_WORD;

    //登录地址
    public static String LOGIN_URL;
    //登录名
    public static String NAME;
    //任务
    public static List<TaskItem> TASK_LIST;

    public static DefaultListModel <TaskItem> LIST_MODEL;

    static {
        LOGIN_URL = "http://work.ruiyunnet.com/biz/user-login.html";
        TASK_LIST = new ArrayList<>();
        TaskItem taskItem = new TaskItem();
        taskItem.setId("12233");
        taskItem.setTaskName("JList类把维护和绘制列表的工作委托给一个对象来完成");
        taskItem.setP("中");
        TaskItem taskItem2 = new TaskItem();
        taskItem2.setId("12233");
        taskItem2.setTaskName("JList类把维护和绘制列表的工作委托给一个对象来完成");
        taskItem2.setP("严重");
        TASK_LIST.add(taskItem);
        TASK_LIST.add(taskItem2);
        LIST_MODEL = new DefaultListModel<>();
        LIST_MODEL.addElement(taskItem);
        LIST_MODEL.addElement(taskItem2);
    }

    public static void setTableModel() {
    }
}
