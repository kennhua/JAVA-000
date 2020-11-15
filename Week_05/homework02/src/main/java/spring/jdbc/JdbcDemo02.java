package spring.jdbc;

import spring.jdbc.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description 使用事务，PrepareStatement 方式，批处理方式
 * @Version:1.0
 **/
public class JdbcDemo02 {

    private static final String url = "jdbc:mysql://localhost:3306/test?" + "useUnicode=true&characterEncoding=UTF8";
    private static final String db_username = "root";
    private static final String db_password = "123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //PreparedStatement查询
        final String querySql = "select * from t_student where id=?";
        try (final Connection connection = DriverManager.getConnection(url, db_username, db_password);
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

//        //事务、批处理
//        final String insertSql = "insert into t_student values (?,?,?)";
//        try (final Connection connection = DriverManager.getConnection(url, user, password);
//             final PreparedStatement psmt = connection.prepareStatement(insertSql);) {
//            connection.setAutoCommit(false);//关闭事务自动提交
//            psmt.setInt(1,6);
//            psmt.setString(2,"121544050");
//            psmt.setString(3,"xiaoliu");
//            psmt.addBatch();//添加到批处理命令
//            psmt.setInt(1,7);
//            psmt.setString(2,"121544051");
//            psmt.setString(3,"daliu");
//            psmt.addBatch();
//            psmt.executeBatch();//执行批处理
//            connection.commit();//手动提交事务
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
