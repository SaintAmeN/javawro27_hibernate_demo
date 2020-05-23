package com.sda.javawro27.hibernate;

import com.sda.javawro27.hibernate.model.Behaviour;
import com.sda.javawro27.hibernate.model.Grade;
import com.sda.javawro27.hibernate.model.GradeSubject;
import com.sda.javawro27.hibernate.model.Student;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // trzeba mieć stworzoną bazę i tabelę
        Scanner scanner = new Scanner(System.in);

        String komenda;

        do {
            System.out.println("Podaj komendę [add/list/delete/update/addgrade/quit]");
            komenda = scanner.nextLine();

            if (komenda.equalsIgnoreCase("add")) {
                addStudents(scanner);
            } else if (komenda.equalsIgnoreCase("list")) {
                listStudents();
            } else if (komenda.equalsIgnoreCase("delete")) {
                deleteStudent(scanner);
            } else if (komenda.equalsIgnoreCase("update")) {
                updateStudent(scanner);
            } else if (komenda.equalsIgnoreCase("byAge")) {
                findByAge(new StudentDao(), scanner);
            } else if (komenda.equalsIgnoreCase("bybeh")) {
                findByBehaviourAndAlive(new StudentDao(), scanner);
            } else if (komenda.equalsIgnoreCase("addgrade")) {
                addGradeToStudent(scanner);
            }

        } while (!komenda.equalsIgnoreCase("quit"));
    }

    private static void addGradeToStudent(Scanner scanner) {
        EntityDao<Grade> gradeDao = new EntityDao<>();
        EntityDao<Student> studentDao = new EntityDao<>();

        System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());

        Optional<Student> studentOptional = studentDao.findById(Student.class, id);
        if(studentOptional.isPresent()) {
            System.out.println("Podaj parametry: GradeValue, Subject[J_POLSKI, J_ANGIELSKI, MATEMATYKA,INFORMATYKA]");
            String linia = scanner.nextLine();
            double gValue = Double.valueOf(linia.split(" ")[0]);
            GradeSubject subject = GradeSubject.valueOf(linia.split(" ")[1].toUpperCase());

            // tworzymy ocenę
            Grade grade = new Grade(gValue, subject);

            // 1. stworzenie obiektu w bazie (niepowiązanego)
            gradeDao.saveOrUpdate(grade); // zapis do bazy można pominąć

            // 2. powiązanie obiektów
            Student student = studentOptional.get();
            grade.setStudentRef(student);

            // 3. zapis obiektów
            gradeDao.saveOrUpdate(grade);
        }
    }

    private static void findByAge(StudentDao dao, Scanner scanner) {
        System.out.println("Podaj parametry: AgeFrom, AgeTo");
        String linia = scanner.nextLine();
        int ageFrom = Integer.valueOf(linia.split(" ")[0]);
        int ageTo = Integer.valueOf(linia.split(" ")[1]);

        System.out.println("Znalezione rekordy: ");
        dao.findByAgeBetween(ageFrom, ageTo).forEach(System.out::println);
    }

    private static void findByBehaviourAndAlive(StudentDao dao, Scanner scanner) {
        System.out.println("Podaj parametry: Behaviour, Alive");
        String linia = scanner.nextLine();
        Behaviour behaviour = Behaviour.valueOf(linia.split(" ")[0]);
        boolean alive = Boolean.parseBoolean(linia.split(" ")[1]);

        System.out.println("Znalezione rekordy: ");
        dao.findByBehaviourAndAlive(behaviour, alive).forEach(System.out::println);
    }

    private static void deleteStudent(Scanner scanner) {
        EntityDao<Student> dao = new EntityDao<>();

        // nie da się usunąć rekordu po id (bezpośrednio z sesji)
        System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());

        Optional<Student> studentOptional = dao.findById(Student.class, id);   // szukamy studenta
        if (studentOptional.isPresent()) {                       // jeśli uda się go odnaleźć
            Student student = studentOptional.get();            // wyciągamy studenta z Optional (Box, opakowanie)
            new StudentDao().delete(student);                                // przekazujemy do usunięcia
        }
    }

    private static void listStudents() {
        System.out.println("Lista studentów:");
        new StudentDao().getAll().stream().forEach(System.out::println);
    }

    private static void addStudents(Scanner scanner) {
        EntityDao<Student> dao = new EntityDao<>();

        System.out.println("Podaj parametry: IMIE NAZWISKO WZROST WIEK ŻYWY ZACHOWANIE");
        String linia = scanner.nextLine();
        String[] slowa = linia.split(" ");

        Student student = Student.builder()
                .firstName(slowa[0])
                .lastName(slowa[1])
                .height(Double.parseDouble(slowa[2]))
                .age(Integer.parseInt(slowa[3]))
                .alive(Boolean.parseBoolean(slowa[4]))
                .behaviour(Behaviour.valueOf(slowa[5].toUpperCase()))
                .build();

        dao.saveOrUpdate(student);
    }

    private static void updateStudent(Scanner scanner) {
        EntityDao<Student> dao = new EntityDao<>();

        System.out.println("Podaj parametry: Identyfikator");
        Long id = Long.valueOf(scanner.nextLine());

        Optional<Student> studentOptional = dao.findById(Student.class, id);   // szukamy studenta
        if (studentOptional.isPresent()) {                       // jeśli uda się go odnaleźć
            Student student = studentOptional.get();            // wyciągamy studenta z Optional (Box, opakowanie)
            System.out.println("Próbujesz edytować rekord: " + student);

            System.out.println("Podaj parametry: IMIE NAZWISKO WZROST WIEK ŻYWY ZACHOWANIE");
            String linia = scanner.nextLine();
            String[] slowa = linia.split(" ");

            student = Student.builder()
                    .firstName(slowa[0])
                    .lastName(slowa[1])
                    .height(Double.parseDouble(slowa[2]))
                    .age(Integer.parseInt(slowa[3]))
                    .alive(Boolean.parseBoolean(slowa[4]))
                    .id(id)
                    .behaviour(Behaviour.valueOf(slowa[5].toUpperCase()))
                    .build();

            dao.saveOrUpdate(student);
        } else {
            System.err.println("Error, student z takim id nie istnieje.");
        }
    }

}
