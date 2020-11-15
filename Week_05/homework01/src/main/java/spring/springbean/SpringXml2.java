package spring.springbean;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description 构造方法注入
 * @Version:1.0
 **/
public class SpringXml2 implements Spring {

    private String value;

    public SpringXml2(String value) {
        this.value = value;
    }

    public void method() {
        System.out.println("SpringBean装配方式二：" + value);
    }
}
