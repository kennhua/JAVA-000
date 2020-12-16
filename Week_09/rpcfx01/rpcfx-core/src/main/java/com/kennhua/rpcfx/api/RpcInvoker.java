package com.kennhua.rpcfx.api;

/**
 * @author liugenghua
 **/
public abstract class RpcInvoker {
    public abstract Object invoke(Object instance, String method, Object[] params);
}