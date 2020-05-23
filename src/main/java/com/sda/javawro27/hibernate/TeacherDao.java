package com.sda.javawro27.hibernate;

import com.sda.javawro27.hibernate.model.Student;
import com.sda.javawro27.hibernate.model.Teacher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TeacherDao {
    public Set<Student> findStudents(Long identifier){
        Set<Student> students = new HashSet<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // istnieje prawdopodobieństwo, że rekord nie zostanie odnaleziony
            Teacher teacher = session.get(Teacher.class, identifier);
//            teacher.getStudentSet().forEach(System.out::println);

            // ważne, by wywołać zapytanie "gettera" na set studentów wewnątrz bloku sesji
            students.addAll(teacher.getStudentSet());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return students;
    }
}
