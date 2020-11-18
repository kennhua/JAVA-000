package mybatis.service.impl;

import mybatis.dao.StudentMapper;
import mybatis.entity.Student;
import mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description TODO
 * @Version:1.0
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student select(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(Student student) {
        this.studentMapper.insert(student);
    }
}
