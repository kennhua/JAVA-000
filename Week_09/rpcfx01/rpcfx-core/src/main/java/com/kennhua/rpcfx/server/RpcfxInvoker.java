package com.kennhua.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kennhua.rpcfx.api.RpcInvoker;
import com.kennhua.rpcfx.api.RpcfxRequest;
import com.kennhua.rpcfx.api.RpcfxResolver;
import com.kennhua.rpcfx.api.RpcfxResponse;
import com.kennhua.rpcfx.common.InvokerFactory;
import com.kennhua.rpcfx.common.RpcfxException;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        // 作业1：改成泛型和反射
        Object service = resolver.resolve(serviceClass);
        try {
            RpcInvoker rpcInvoker = InvokerFactory.getInvoker(service.getClass());
            Object result = rpcInvoker.invoke(service, request.getMethod(), request.getParams());

            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch ( Exception e) {

            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            response.setException(new RpcfxException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.toString()));
            response.setStatus(false);
            return response;
        }
    }

}
