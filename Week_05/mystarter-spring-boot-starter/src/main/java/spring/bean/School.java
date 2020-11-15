package spring.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {

    @Autowired
    Klass class1;

    @Resource
    Student student;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
    }

}
