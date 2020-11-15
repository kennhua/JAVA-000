package spring.springbean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liugenghua
 * @date：2020/11/13
 * @Description 基于xml方式装配：设值注入、构造方法; 基于注解方式装配：@Component，@Bean
 * @Version:1.0
 **/
public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringXml1 springXml1 = (SpringXml1) context.getBean("springXml1");
        springXml1.method();

        SpringXml2 springXml2 = (SpringXml2) context.getBean("springXml2");
        springXml2.method();

        SpringAnnotation1 springAnnotation1 = (SpringAnnotation1) context.getBean("springAnnotation1");
        springAnnotation1.method();

        SpringAnnotation2 springAnnotation2 = (SpringAnnotation2) context.getBean("springAnnotation2");
        springAnnotation2.method();
    }
}
