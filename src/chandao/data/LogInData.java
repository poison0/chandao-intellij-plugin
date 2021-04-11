package chandao.data;

import chandao.bean.TaskItem;
import com.gargoylesoftware.htmlunit.WebClient;
import com.intellij.ide.util.PropertiesComponent;
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
    //任务
    public static JList<TaskItem> listTask;

    public static DefaultListModel <TaskItem> LIST_MODEL;

    static {
        LOGIN_URL = "http://work.ruiyunnet.com/biz/user-login.html";
        TASK_LIST = new ArrayList<>();
//        TaskItem taskItem = new TaskItem();
//        taskItem.setId("12233");
//        taskItem.setTaskName("JList类把维护和绘制列表的工作委托给一个对象来完成");
//        taskItem.setP("中");
//        TaskItem taskItem2 = new TaskItem();
//        taskItem2.setId("12233");
//        taskItem2.setTaskName("JList类把维护和绘制列表的工作委托给一个对象来完成");
//        taskItem2.setP("严重");
//        TaskItem taskItem3 = new TaskItem();
//        taskItem3.setId("12233");
//        taskItem3.setTaskName("JList类把维护和绘制列表的工作委托给一个对象来完成");
//        taskItem3.setP("严重");
//        TASK_LIST.add(taskItem);
//        TASK_LIST.add(taskItem2);
//        TASK_LIST.add(taskItem3);
        LIST_MODEL = new DefaultListModel<>();
        PropertiesComponent instance = PropertiesComponent.getInstance();
        String userName = instance.getValue("chandao_user_name");
        String passWord = instance.getValue("chandao_pass_word");
        if (userName != null && passWord != null) {
            USER_NAME = userName;
            PASS_WORD = passWord;
        }
    }

    public static void setTableModel() {
        LIST_MODEL.clear();
        for (TaskItem taskItem : TASK_LIST) {
            LIST_MODEL.addElement(taskItem);
        }
    }
}
