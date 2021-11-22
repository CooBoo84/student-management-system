package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public Subject createNewSubject(Subject subject) {
        entityManager.persist(subject);
        return subject;
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT i FROM Subject i", Subject.class).getResultList();
    }

    public List<Student> findStudentsBySubject(String subject){
        Subject s =  entityManager.createQuery("SELECT s FROM Subject s  WHERE s.name = :sub", Subject.class).setParameter("sub",subject).getSingleResult();
        Long id = s.getId();
        return entityManager.find(Subject.class, id).getStudents();
    }

    public List<Subject> findSubjectByName(String subject) {
        return entityManager
                .createQuery("SELECT s FROM Subject s WHERE s.name =\'" + subject + "\'", Subject.class)
                .getResultList();
    }
}
