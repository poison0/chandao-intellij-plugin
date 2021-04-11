package chandao.data;

import chandao.bean.TaskItem;
import com.gargoylesoftware.htmlunit.WebClient;
import com.intellij.ide.util.PropertiesComponent;

import javax.swing.*;
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
        LIST_MODEL = new DefaultListModel<>();
        //数据持久化
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
