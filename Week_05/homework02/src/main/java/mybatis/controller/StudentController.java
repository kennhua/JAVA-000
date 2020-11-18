package mybatis.controller;

import mybatis.entity.Student;
import mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description TODO
 * @Version:1.0
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insert")
    public void insert(@RequestBody Student student) {
        this.studentService.insert(student);
    }

    @GetMapping("/select/{id}")
    public Student select(@PathVariable("id") int id){
        return studentService.select(id);
    }
}
