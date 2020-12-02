package datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description 数据源切换路由
 * @Version:1.0
 **/
public class MultiDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
