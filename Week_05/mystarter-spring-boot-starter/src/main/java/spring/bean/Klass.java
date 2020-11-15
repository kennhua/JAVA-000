package spring.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Klass implements Serializable {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}
