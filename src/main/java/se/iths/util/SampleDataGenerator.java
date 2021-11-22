package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData(){

        Student student1 = new Student("Super","Mario","super.mario@mail.com","010123456");
        Student student2 = new Student("Luigi","Mario","luigi@mail.com","010987654");
        Student student3 = new Student("Donkey","Kong","donkey.kong@mail.com","010456789");
        Student student4 = new Student("Shigeru","Miyamoto","mr.miyamoto@mail.com","010147258");

        Teacher teacher1 = new Teacher("Link", "Zelda", "link.zelda@mail.com", "070134567");
        Teacher teacher2 = new Teacher("Warren", "Buffett", "warren.e.buffett@mail.com", "99999999");

        Subject subject1 = new Subject("Java",teacher1);
        Subject subject2 = new Subject("Python", teacher1);
        Subject subject3 = new Subject("C#",teacher2);

        student1.addSubject(subject1);
        student1.addSubject(subject2);
        student2.addSubject(subject1);
        student3.addSubject(subject2);
        student4.addSubject(subject2);


        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);

        entityManager.persist(teacher1);
        entityManager.persist(teacher2);
    }
}
