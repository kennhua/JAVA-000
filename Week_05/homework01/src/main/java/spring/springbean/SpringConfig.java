package spring.springbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liugenghua
 * @date：2020/11/14
 * @Description 用@Bean注解装配bean
 * @Version:1.0
 **/
@Configuration
public class SpringConfig {

    @Bean(name = "springAnnotation2")
    public SpringAnnotation2 getSpringAnnotation2() {
        return new SpringAnnotation2();
    }
}
