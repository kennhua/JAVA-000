package datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description 读写分离- 动态 切换数据源 版本1.0
 * @Version:1.0
 **/
@SpringBootApplication
public class DataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSourceApplication.class,args);
    }
}
