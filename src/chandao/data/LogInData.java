package chandao.data;

import com.gargoylesoftware.htmlunit.WebClient;

import javax.swing.table.DefaultTableModel;

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

    public static String[] HEAD={"标题","备注","文件名","代码段"};

    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null,HEAD);
    static {
        LOGIN_URL = "http://work.ruiyunnet.com/biz/user-login.html";
    }

    public static String string() {

        return "COOKIE="+COOKIE+"  "+
                "WEB_CLIENT="+WEB_CLIENT+"  "+
                "USER_NAME="+USER_NAME+"  "+
                "PASS_WORD="+PASS_WORD+"  "+
                "LOGIN_URL="+LOGIN_URL;
    }
}
