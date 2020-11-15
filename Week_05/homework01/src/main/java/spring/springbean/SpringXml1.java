package spring.springbean;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description setter设值注入
 * @Version:1.0
 **/
public class SpringXml1 implements Spring {

    private String value;

    public void method() {
        System.out.println("SpringBean装配方式一：" + value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
