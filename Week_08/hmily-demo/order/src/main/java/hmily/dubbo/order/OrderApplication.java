package hmily.dubbo.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author liugenghua
 **/
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@ImportResource({"classpath:spring-dubbo.xml"})
@MapperScan("hmily.dubbo.order.mapper")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
