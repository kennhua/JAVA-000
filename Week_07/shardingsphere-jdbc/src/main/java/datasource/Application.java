package datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liugenghua
 * @date：2020/12/1
 * @Description 使用ShardingSphere-jdbc 5.0.0-alpha 实现 读写分离配置
 * @Version:1.0
 **/
@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
