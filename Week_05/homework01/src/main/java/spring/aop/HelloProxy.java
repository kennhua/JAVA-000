package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description HelloImpl的代理类
 * @Version:1.0
 **/
public class HelloProxy implements InvocationHandler {

    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello = hello;
    }

    /**
     * 对代理类sayHello方法进行增强
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----------sayHello begin-----------");
        Object invoke = method.invoke(hello, args);
        System.out.println("-----------sayHello end-----------");
        return invoke;
    }
}
