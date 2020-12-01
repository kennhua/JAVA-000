package datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liugenghua
 * @date：2020/12/1
 * @Description 从库数据源配置信息
 * @Version:1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class SlaveConfig {

    private List<Config> slave;

    @Data
    public static class Config {
        private String driverClassName;
        private String jdbcUrl;
        private String username;
        private String password;
    }
}
