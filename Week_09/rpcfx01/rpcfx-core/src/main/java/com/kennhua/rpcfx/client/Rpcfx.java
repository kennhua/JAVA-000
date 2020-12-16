package com.kennhua.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.google.gson.JsonObject;
import com.kennhua.rpcfx.api.RpcfxRequest;
import com.kennhua.rpcfx.api.RpcfxResponse;
import com.kennhua.rpcfx.utils.HttpClientUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("com.kennhua");
    }

    public static <T> T create(final Class<T> serviceClass, final String url) {

        // 0. 替换动态代理 -> AOP
        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url));

    }

    public static class RpcfxInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        public <T> RpcfxInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);
            RpcfxResponse response = post(request, url);

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException
            if(!response.isStatus()){
                System.out.println("errMsg: " + response.getException().getErrMsg());
                return null;
            }
            return JSON.parse(response.getResult().toString());
        }

        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
//            OkHttpClient client = new OkHttpClient();
//            final Request request = new Request.Builder()
//                    .url(url)
//                    .post(RequestBody.create(JSONTYPE, reqJson))
//                    .build();
//            String respJson = client.newCall(request).execute().body().string();
            // 使用httpclient连接池
            final JsonObject respJson = HttpClientUtil.post(url, reqJson);
            return JSON.parseObject(respJson.toString(), RpcfxResponse.class);
        }
    }
}
