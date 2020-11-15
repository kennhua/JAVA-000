package spring.jdbc.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description TODO
 * @Version:1.0
 **/
@Data
@Table("t_student")
public class Student {

    public int id;
    public String number;
    public String name;
}
