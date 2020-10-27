package http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author liugenghua
 * @date：2020/10/26
 * @Description httpclient测试接口
 * @Version:1.0
 **/
public class HttpClientTest {

    public static void main(String[] args) {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.声明get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        //3.发送请求
        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            //4.判断状态码，解析返回值
            if(response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                String string = EntityUtils.toString(entity, "utf-8");
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //5.释放资源
        if(null != httpClient){
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
