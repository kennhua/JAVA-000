package spring.springbean;

import org.springframework.stereotype.Component;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description @Component注解
 * @Version:1.0
 **/
@Component
public class SpringAnnotation1 implements Spring {

    public void method() {
        System.out.println("SpringBean装配方式三：@Component注解");
    }
}
