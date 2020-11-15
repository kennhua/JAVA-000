package spring.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.bean.Klass;
import spring.bean.Student;

/**
 * @author liugenghua
 * @date：2020/11/15
 * @Description 测试自定义starter
 * @Version:1.0
 **/
@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class StarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }

    @Autowired
    private Student student;
    @Autowired
    private Klass class1;

    @RequestMapping("/student")
    public String getStudent() {
        if (null != student) {
            student.init();
            return student.toString();
        }
        return null;
    }

    @RequestMapping("/klass")
    public String getKlass() {
        if (null != class1) {
            class1.dong();
            return class1.getStudents().toString();
        }
        return null;
    }
}
