package chandao.query;

import chandao.util.ExtractDataUtil;
import chandao.data.LogInData;
import chandao.message.Notifier;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static chandao.data.LogInData.LIST_MODEL;
import static chandao.data.LogInData.WEB_CLIENT;

public class Login {

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
    public void login(Project project) {

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
            Page click = btnLogin.click();
            Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            StringBuilder cookie = new StringBuilder();
            for (Cookie item : cookies) {
                cookie.append(item.getName()).append("=").append(item.getValue()).append(";");
            }
            //获取cookie
            LogInData.COOKIE = cookie.toString();
            WEB_CLIENT = webClient;
            refresh(project);
        } catch (IOException e) {
            Notifier.notify(e.getMessage(), MessageType.ERROR);
        }
    }
    public static void refresh(Project project){
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Refresh...") {
            @Override
            public void run(@NotNull ProgressIndicator progressIndicator) {
                LIST_MODEL.clear();
                try {
                    WEB_CLIENT.getPage("http://work.ruiyunnet.com/biz/my-task-assignedTo-id_desc-100-2000-1.html");
                    WEB_CLIENT.getPage("http://work.ruiyunnet.com/biz/my-task-assignedTo-id_desc-100-2000-1.html");
                    WEB_CLIENT.getPage("http://work.ruiyunnet.com/biz/my-bug-assignedTo-id_desc-100-2000-1.html");
                    if (WEB_CLIENT.getWebWindows().size() > 1) {
                        List<WebWindow> webWindows = WEB_CLIENT.getWebWindows();
                        LogInData.TASK_LIST = ExtractDataUtil.getTaskList(((FrameWindow) WEB_CLIENT.getWebWindows().get(1)).getEnclosingPage(), 0);
                        if (webWindows.size() == 4) {
                            LogInData.BUG_LIST = ExtractDataUtil.getTaskList(((FrameWindow) WEB_CLIENT.getWebWindows().get(3)).getEnclosingPage(), 1);
                        } else if (webWindows.size() == 3) {
                            LogInData.BUG_LIST = ExtractDataUtil.getTaskList(((FrameWindow) WEB_CLIENT.getWebWindows().get(2)).getEnclosingPage(), 1);
                        }
                        LogInData.setTableModel();
                    } else {
                        Notifier.notify("查询出错，请重试", MessageType.ERROR);
                    }
                } catch (IOException e) {
                    Notifier.notify(e.getMessage(), MessageType.ERROR);
                }
            }
        });
    }
}
