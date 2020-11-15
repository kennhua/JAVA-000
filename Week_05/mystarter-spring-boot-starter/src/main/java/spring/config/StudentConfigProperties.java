package spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description TODO
 * @Version:1.0
 **/
@Data
@ConfigurationProperties(prefix = "mystarter.config.student")
public class StudentConfigProperties {

    private int id;
    private String name;

}
