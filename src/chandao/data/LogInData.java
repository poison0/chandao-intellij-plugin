package chandao.data;

import chandao.bean.TaskItem;
import com.gargoylesoftware.htmlunit.WebClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public static String[] HEAD={"ID","P","名称"};

    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null,HEAD);
    static {
        LOGIN_URL = "http://work.ruiyunnet.com/biz/user-login.html";
    }

    public static void setTableModel() {
        for (TaskItem taskItem : TASK_LIST) {
            TABLE_MODEL.addRow(DataConvert.covert(taskItem));
        }
    }
}
