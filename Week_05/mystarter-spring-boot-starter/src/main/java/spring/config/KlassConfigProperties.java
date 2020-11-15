package spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import spring.bean.Student;

import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description TODO
 * @Version:1.0
 **/
@Data
@ConfigurationProperties(prefix = "mystarter.config.klass")
public class KlassConfigProperties {

    List<Student> students;
}
