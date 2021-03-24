import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.util.Cookie;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Demo {
    WebClient webClient;
    public Demo(){
        webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS。有些网站要开启！
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
        webClient.getOptions().setTimeout(30000);
    }
    public void login() { //登录
        String userName="niushunshun";  //你的账号和密码
        String password="Niushunshun624";
        String loginUrl="http://work.ruiyunnet.com/biz/user-login.html"; //登录页面的网址
        try {
            final HtmlPage page = webClient.getPage(loginUrl);//打开登录页面
//            System.out.println(page.getWebResponse().getContentAsString()); //输出一下登录页面看看
            //获取账号输入框结点，然后点击账号输入框，最后输入
            HtmlTextInput inputUserName= (HtmlTextInput) page.getElementsByName("account").get(0);
            inputUserName.click();
            inputUserName.setText(userName);

            HtmlPasswordInput inputPasswd= (HtmlPasswordInput) page.getElementsByName("password").get(0);
            inputPasswd.click();
            inputPasswd.setText(password);

            HtmlButton btnLogin= (HtmlButton) page.getElementById("submit");
            btnLogin.click(); //点击登录按钮，返回值为登陆成功后跳到的页面
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //爬取某网页
    public void work(String url) {
        try {
            HtmlPage page = webClient.getPage(url);//打开网页
            Set<Cookie> cookies = webClient.getCookieManager().getCookies();
            StringBuilder cookie = new StringBuilder();
            for (Cookie item : cookies) {
                cookie.append(item.getName()).append("=").append(item.getValue()).append(";");
            }
//            webClient.waitForBackgroundJavaScript(10000);
//            String html=page.getWebResponse().getContentAsString(); //获取网页源代码html
//            System.out.println("爬取成功，html代码如下：");
//            System.out.println(html);
//            List<HtmlTableDataCell> byXPath = page.getByXPath("//tr/td[@class='c-name']");
//            for (HtmlTableDataCell o : byXPath) {
//                String title = o.getAttribute("title");
//                System.out.println(title);
//            }
            /*
             * 这里可以做一些事情，从html中截取所需数据。具体方法可以使用正则表达式
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Demo demo=new Demo();
        demo.login(); //如果网站必须登录才能进入，那就登录
        System.out.println("已登录:");
        demo.work("http://work.ruiyunnet.com/biz/task-view-28564.html");
    }
}
