package com.sda.javawro27.hibernate;

import com.sda.javawro27.hibernate.model.Behaviour;
import com.sda.javawro27.hibernate.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// umożliwia wykonywanie operacji CRUD na modelu Student
//           GradeDao
//           GenericDao<>
public class StudentDao {

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            // h q l = hibernate query language
            Query<Student> studentQuery = session.createQuery("SELECT a from Student a", Student.class);

            list.addAll(studentQuery.getResultList());
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    //###############################################################################
    //###############################################################################
    //###############################################################################
    //###############################################################################

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<Student> rootTable = criteriaQuery.from(Student.class);

            // wykonanie zapytania
            criteriaQuery.select(rootTable);

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Student> findByLastName(String lastName) {
        List<Student> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<Student> rootTable = criteriaQuery.from(Student.class);

            // wykonanie zapytania
            criteriaQuery.select(rootTable)
                    .where(
                            // *lastName*
                            // czy wartośćkolumny 'lastName' jest równa wartości zmiennej lastName
                            cb.equal(rootTable.get("lastName"), lastName)
                    );

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Student> findByAgeBetween(int ageFrom, int ageTo) {
        List<Student> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<Student> rootTable = criteriaQuery.from(Student.class);

            // wykonanie zapytania
            criteriaQuery.select(rootTable)
                    .where(
                            // *lastName*
                            // czy wartośćkolumny 'lastName' jest równa wartości zmiennej lastName
                            cb.between(rootTable.get("age"), ageFrom, ageTo)
                    );

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }

    public List<Student> findByBehaviourAndAlive(Behaviour behaviour, boolean alive) {
        List<Student> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<Student> rootTable = criteriaQuery.from(Student.class);

            // wykonanie zapytania
            criteriaQuery.select(rootTable)
                    .where(
                            // *lastName*
                            // czy wartośćkolumny 'lastName' jest równa wartości zmiennej lastName
                            cb.and(
                                    cb.equal(rootTable.get("behaviour"), behaviour),
                                    cb.equal(rootTable.get("alive"), alive ? 1 : 0) // zamiast true/false będzie 1/0
                            )
                    );

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return list;
    }
}
