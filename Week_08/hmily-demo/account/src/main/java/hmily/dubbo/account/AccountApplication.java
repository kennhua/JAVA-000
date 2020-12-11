package hmily.dubbo.account;

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
@MapperScan("hmily.dubbo.account.mapper")
public class AccountApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
