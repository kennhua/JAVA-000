package spring.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import spring.jdbc.entity.Student;

import java.sql.*;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description 配置 Hikari 连接池
 * @Version:1.0
 **/
public class JdbcDemo03 {

    private static final int db_max_conn = 10;
    private static final String db_url = "localhost";
    private static final String db_port = "3306";
    private static final String db_name = "test";
    private static final String db_username = "root";
    private static final String db_password = "123";
    private static HikariDataSource dataSource = null;

    public static void main(String[] args) throws SQLException {

        init();//初始化连接池
        final String querySql = "select * from t_student where id=?";
        try (final Connection connection = getConnection();
             final PreparedStatement psmt = connection.prepareStatement(querySql);) {
            psmt.setInt(1,3);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setNumber(resultSet.getString("number"));
                student.setName(resultSet.getString("name"));
                System.out.println(student.toString());
            }
            if(null != resultSet){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void init(){
        if(dataSource == null){
            HikariConfig config = new HikariConfig();
            config.setMaximumPoolSize(db_max_conn);
            config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            config.addDataSourceProperty("serverName", db_url);
            config.addDataSourceProperty("port", db_port);
            config.addDataSourceProperty("databaseName", db_name);
            config.addDataSourceProperty("user", db_username);
            config.addDataSourceProperty("password", db_password);
            dataSource = new HikariDataSource(config);
        }
    }

    private static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
