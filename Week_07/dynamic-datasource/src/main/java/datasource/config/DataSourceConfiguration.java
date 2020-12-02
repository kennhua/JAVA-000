package datasource.config;

import datasource.eumn.DataSourceType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description 数据源配置
 * @Version:1.0
 **/
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(datasource.config.SlaveConfig.class)
@MapperScan(basePackages = "datasource.dao")
public class DataSourceConfiguration {

    @Autowired
    private SlaveConfig slaveConfig;

    /**
     * 主库数据源
     *
     * @return
     */
    @Primary
    @Bean("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 从库数据源
     *
     * @return
     */
    public DataSource getSlaveDataSource(final int i) {
        SlaveConfig.Config config = slaveConfig.getSlave().get(i);
        return DataSourceBuilder.create().driverClassName(config.getDriverClassName())
                .url(config.getJdbcUrl()).username(config.getUsername()).build();
    }

    /**
     * 注入动态数据源
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    /**
     * 配置动态数据源
     *
     * @return
     */
    @Bean("dynamicDataSource")
    public AbstractRoutingDataSource dynamicDataSource() {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.masterDataSource, getMasterDataSource());
        List<SlaveConfig.Config> slave = slaveConfig.getSlave();
        if (null != slave && slave.size() > 0) {
            for (int i = 0; i < slave.size(); i++) {
                targetDataSources.put(DataSourceType.slaveDataSource + "_" + i, getSlaveDataSource(i));
            }
        }
        AbstractRoutingDataSource routingDataSource = new MultiDataSourceRouter();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(getMasterDataSource());
        return routingDataSource;
    }

    /**
     * 用于事务管理
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置动态数据源
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
