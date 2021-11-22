package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher findTeacherById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    public List<Teacher> getTeacherByLastName(String lastName) {
        TypedQuery<Teacher> query = entityManager.createQuery("SELECT t from Teacher t WHERE t.lastName = :lastName", Teacher.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("SELECT t from Teacher t", Teacher.class).getResultList();
    }

    public List<Student> getSpecifiedStudentsPerSubjectandTeacher(String subject, String teacher){
            Subject givenSubject = (Subject) entityManager
                    .createQuery("SELECT DISTINCT s FROM Subject s INNER JOIN  s.teacher t INNER JOIN  s.students e WHERE t.firstName = :teacher AND s.name = :subject")
                    .setParameter("teacher", teacher)
                    .setParameter("subject", subject)
                    .getSingleResult();
            List<Student> foundStudents = givenSubject.getStudents();
            return foundStudents;
        }

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }
}
