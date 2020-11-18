package mybatis.entity;

import java.util.List;

public class Student {
    private Integer id;

    private String number;

    private String name;

    private List<String> skill;

    public Student(Integer id, String number, String name, List<String> skill) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.skill = skill;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }
}