package mybatis.service;

import mybatis.entity.Student;

import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description TODO
 * @Version:1.0
 **/
public interface StudentService {
    void insert(Student student);
    Student select(int id);
}
