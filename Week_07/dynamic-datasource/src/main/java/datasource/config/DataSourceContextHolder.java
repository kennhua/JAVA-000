package datasource.config;

/**
 * @author liugenghua
 * @date：2020/11/30
 * @Description 数据源上下文
 * @Version:1.0
 **/
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSource(String value) {
        contextHolder.set(value);
    }

    public static String getDataSource() {
        return contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
