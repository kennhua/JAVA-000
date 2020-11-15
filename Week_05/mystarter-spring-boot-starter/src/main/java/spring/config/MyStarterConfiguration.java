package spring.config;

import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.bean.Klass;
import spring.bean.Student;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description TODO
 * @Version:1.0
 **/
@Configuration
@EnableConfigurationProperties({StudentConfigProperties.class,KlassConfigProperties.class})
public class MyStarterConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "mystarter.config", name = "enable", havingValue = "true")
    public Student student(StudentConfigProperties properties) {
        Student student = new Student();
        BeanUtils.copyProperties(properties, student);
        return student;
    }

    @Bean
    @ConditionalOnProperty(prefix = "mystarter.config", name = "enable", havingValue = "true")
    public Klass klass(KlassConfigProperties properties) {
        Klass klass = new Klass();
        BeanUtils.copyProperties(properties, klass);
        return klass;
    }
}
