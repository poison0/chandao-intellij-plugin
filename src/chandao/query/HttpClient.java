package chandao.query;
import chandao.message.Notifier;
import com.intellij.openapi.ui.MessageType;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;


public class HttpClient {

    /**
     * 表单提交
     * @param url 接口
     * @param params 入参
     * @param cookie cookie
     * @return 接口返回内容
     */
    public static String doPostForm(String url, Map<String, String> params,String cookie) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httpPost = new HttpPost(url);
        try {
            // 设置提交方式
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.addHeader("cookie", cookie);
            // 添加参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (params.size() != 0) {
                // 将mapdata中的key存在set集合中，通过迭代器取出所有的key，再获取每一个键对应的值
                Set<String> keySet = params.keySet();
                // key
                for (String k : keySet) {
                    // value
                    String v = params.get(k);
                    nameValuePairs.add(new BasicNameValuePair(k, v));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 获得http响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // 响应的结果
                return EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            Notifier.notify(e.getMessage(), MessageType.ERROR);
        }
        Notifier.notify("读取数据错误", MessageType.ERROR);
        return null;
    }
}
