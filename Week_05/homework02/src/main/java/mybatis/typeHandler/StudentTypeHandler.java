package mybatis.typeHandler;

import com.sun.deploy.util.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description 把数组格式转换成以","分隔的字符串存到数据库，查询的时候反过来
 * @Version:1.0
 **/
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class StudentTypeHandler implements TypeHandler<List<String>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        String students = StringUtils.join(strings, ",");
        try {
            preparedStatement.setString(i, students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getResult(ResultSet resultSet, String s) throws SQLException {
        return Arrays.asList(resultSet.getString(s).split(","));
    }

    @Override
    public List<String> getResult(ResultSet resultSet, int i) throws SQLException {
        return Arrays.asList(resultSet.getString(i).split(","));
    }

    @Override
    public List<String> getResult(CallableStatement callableStatement, int i) throws SQLException {
        return Arrays.asList(callableStatement.getString(i).split(","));
    }
}
