package datasource.config;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author liugenghua
 * @date：2020/12/1
 * @Description 配置数据源
 * @Version:1.0
 **/
@Configuration
public class DataSourceConfigration {

    @Bean
    public DataSource dataSource() throws IOException, SQLException {
        return YamlShardingDataSourceFactory.createDataSource(getFile("/META-INF/sharding-master-slave.yaml"));
    }

    private static File getFile(final String fileName) {
        return new File(Thread.currentThread().getClass().getResource(fileName).getFile());
    }
}
