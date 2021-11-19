package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @ManyToMany
    private Student student;

    @ManyToOne
    private Teacher teacher;

    public Subject(String name, Student student, Teacher teacher) {
        this.name = name;
        this.student = student;
        this.teacher = teacher;
    }

    public Subject() {
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
