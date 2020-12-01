package datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.Map;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description 数据源配置
 * @Version:1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "datasource.dao")
public class DataSourceConfiguration {

    /**
     * 主库数据源
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
     * @return
     */
    @Bean("slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource getSlave1DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入动态数据源
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
     * @return
     */
    @Bean("dynamicDataSource")
    public AbstractRoutingDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("masterDataSource", getMasterDataSource());
        targetDataSources.put("slave1DataSource", getSlave1DataSource());
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
