package spring.aop;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description TODO
 * @Version:1.0
 **/
public class HelloImpl implements Hello {
    @Override
    public void sayHello(String value) {
        System.out.println("hello " + value);
    }
}
