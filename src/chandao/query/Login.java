package chandao.query;

import chandao.util.ExtractDataUtil;
import chandao.data.LogInData;
import chandao.message.Notifier;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.intellij.openapi.ui.MessageType;

import java.io.IOException;
import java.util.Set;

public class Login {
    //你的账号和密码
//        String userName="niushunshun";
//        String password="Niushunshun624";
    //登录页面的网址
//        String loginUrl="http://work.ruiyunnet.com/biz/user-login.html";
    WebClient webClient;
    public Login(){
        //新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient = new WebClient(BrowserVersion.CHROME);
        //当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        //当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        //是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setCssEnabled(false);
        //很重要，启用JS。有些网站要开启！
//        webClient.getOptions().setJavaScriptEnabled(true);
        //很重要，设置支持AJAX
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(30000);
    }
    //登录
    public void login() {

        try {
            //打开登录页面
            final HtmlPage page = webClient.getPage(LogInData.LOGIN_URL);
            //获取账号输入框结点，然后点击账号输入框，最后输入
            HtmlTextInput inputUserName= (HtmlTextInput) page.getElementsByName("account").get(0);
            inputUserName.click();
            inputUserName.setText(LogInData.USER_NAME);

            HtmlPasswordInput inputPasswd= (HtmlPasswordInput) page.getElementsByName("password").get(0);
            inputPasswd.click();
            inputPasswd.setText(LogInData.PASS_WORD);

            HtmlButton btnLogin= (HtmlButton) page.getElementById("submit");
            //点击登录按钮，返回值为登陆成功后跳到的页面
            btnLogin.click();
            Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            StringBuilder cookie = new StringBuilder();
            for (Cookie item : cookies) {
                cookie.append(item.getName()).append("=").append(item.getValue()).append(";");
            }
            //获取cookie
            LogInData.COOKIE = cookie.toString();
            LogInData.WEB_CLIENT = webClient;
//            HtmlPage MainPage = webClient.getPage("http://work.ruiyunnet.com/biz/user-login.html");
            webClient.getPage("http://work.ruiyunnet.com/biz/my-task-assignedTo-id_desc-100-2000-1.html");
            if (webClient.getWebWindows().size() > 1) {
                LogInData.TASK_LIST = ExtractDataUtil.getTaskList(((FrameWindow)webClient.getWebWindows().get(1)).getEnclosingPage());
                LogInData.setTableModel();
            }else{
                Notifier.notify("查询出错，请重试", MessageType.ERROR);
            }
        } catch (IOException e) {
            Notifier.notify(e.getMessage(), MessageType.ERROR);
        }
    }
}
