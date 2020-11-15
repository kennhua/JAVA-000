package spring.jdbc;

import spring.jdbc.entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description 使用 JDBC 原生接口，实现数据库的增删改查操作
 * @Version:1.0
 **/
public class JdbcDemo01 {

    private static final String db_url = "jdbc:mysql://localhost:3306/test?" + "useUnicode=true&characterEncoding=UTF8";
    private static final String db_username = "root";
    private static final String db_password = "123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //查询
        try (final Connection connection = DriverManager.getConnection(db_url, db_username, db_password);
             final Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from t_student")) {
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setNumber(resultSet.getString("number"));
                student.setName(resultSet.getString("name"));
                list.add(student);
            }
            System.out.println(list.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        //新增
//        try (final Connection connection = DriverManager.getConnection(url, user, password);
//             final Statement statement = connection.createStatement();) {
//            statement.execute("insert into t_student values (4,'121544244','libai')");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        //修改
//        try (final Connection connection = DriverManager.getConnection(url, user, password);
//             final Statement statement = connection.createStatement();) {
//            statement.execute("update t_student set name='李白' where id=4");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        //删除
//        try (final Connection connection = DriverManager.getConnection(url, user, password);
//             final Statement statement = connection.createStatement();) {
//            statement.execute("delete from t_student where id=4");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
