package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description Hello测试类
 * @Version:1.0
 **/
public class HelloApplication {

    public static void main(String[] args) {
        //定义被代理类的对象
        Hello hello = new HelloImpl();
        //定义代理类对象
        InvocationHandler helloProxy = new HelloProxy(hello);
        //创建一个动态代理实例
        Hello proxyInstance = (Hello) Proxy.newProxyInstance(helloProxy.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), helloProxy);
        //调用sayHello方法执行代理后方法
        proxyInstance.sayHello("world");
    }
}
