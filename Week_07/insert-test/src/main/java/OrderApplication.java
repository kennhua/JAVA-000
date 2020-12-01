import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author liugenghua
 * @date：2020/11/29
 * @Description 用不同方式测试插入100万订单数据
 * @Version:1.0
 **/
public class OrderApplication {

    private static final String db_url = "jdbc:mysql://localhost:3307/shop?" + "useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai";
    private static final String db_username = "root";
    private static final String db_password = "";

    public static void main(String[] args) {
        //循环单条插入，数据插入完成时一次性提交事务，100万数据耗时：248s
        //singleInsert();
        //批量插入，全部插入之后提交事务，100万数据耗时：243s
        batchAllInsert();
        //批量分页插入，每页1000条,100万数据耗时：392s
        //batchInsert();

    }

    private static void singleInsert(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement psmt = null;
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        try {
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            final String sql = "insert into tb_order (user_id,user_name,order_number,receiver,receiver_phone,receiver_address,order_mount) values (?,?,?,?,?,?,?)";
            psmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; i < 1000000; i++) {
                psmt.setInt(1, 1);
                psmt.setString(2, "zhangsan");
                psmt.setInt(3, 123456);
                psmt.setString(4, "lisi");
                psmt.setString(5, "13500000000");
                psmt.setString(6, "China");
                psmt.setInt(7, 1);
                psmt.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);
        System.out.println("消耗时间：" + String.valueOf(end - start));
    }

    private static void batchAllInsert(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement psmt = null;
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        try {
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            final String sql = "insert into tb_order (user_id,user_name,order_number,receiver,receiver_phone,receiver_address,order_mount) values (?,?,?,?,?,?,?)";
            psmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; i < 1000000; i++) {
                psmt.setInt(1, 1);
                psmt.setString(2, "zhangsan");
                psmt.setInt(3, 123456);
                psmt.setString(4, "lisi");
                psmt.setString(5, "13500000000");
                psmt.setString(6, "China");
                psmt.setInt(7, 1);
                psmt.addBatch();
            }
            psmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);
        System.out.println("消耗时间：" + String.valueOf(end - start));
    }

    private static void batchInsert(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement psmt = null;
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        try {
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            final String sql = "insert into tb_order (user_id,user_name,order_number,receiver,receiver_phone,receiver_address,order_mount) values (?,?,?,?,?,?,?)";
            psmt = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int j = 0; j < 1000; j++){
                for (int i = 0; i < 1000; i++) {
                    psmt.setInt(1, 1);
                    psmt.setString(2, "zhangsan");
                    psmt.setInt(3, 123456);
                    psmt.setString(4, "lisi");
                    psmt.setString(5, "13500000000");
                    psmt.setString(6, "China");
                    psmt.setInt(7, 1);
                    psmt.addBatch();
                }
                psmt.executeBatch();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);
        System.out.println("消耗时间：" + String.valueOf(end - start));
    }
}
