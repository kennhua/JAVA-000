package nio.work01;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author liugenghua
 * @date：2020/11/4
 * @Description 模拟初级API网关
 * * @Version:1.0
 **/

public class HttpServer {
    /**
     * 1、启动应用 java -jar gateway-server-0.0.1-SNAPSHOT.jar
     * 2、执行main方法，监听8888端口
     * 3、通过http://127.0.0.1:8088可以访问http://127.0.0.1:8088/api/hello
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        final ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = doHttp();
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String doHttp() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:8088/api/hello");
        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if(response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                String string = EntityUtils.toString(entity, "utf-8");
                return string;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null != httpClient){
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
