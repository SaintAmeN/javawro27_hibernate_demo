package com.sda.javawro27.hibernate;

import com.sda.javawro27.hibernate.model.Behaviour;
import com.sda.javawro27.hibernate.model.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // trzeba mieć stworzoną bazę i tabelę
        StudentDao dao = new StudentDao();
        Scanner scanner = new Scanner(System.in);

        String komenda;

        do{
            System.out.println("Podaj komendę [add/list/delete/update/quit]");
            komenda = scanner.nextLine();

            if(komenda.equalsIgnoreCase("add")){
                addStudents(dao, scanner);
            }else if(komenda.equalsIgnoreCase("list")){
                listStudents(dao);
            }else if(komenda.equalsIgnoreCase("delete")){

            }else if(komenda.equalsIgnoreCase("update")){
            }

        }while (!komenda.equalsIgnoreCase("quit"));
    }

    private static void listStudents(StudentDao dao) {
        System.out.println("Lista studentów:");
        dao.getAll().stream().forEach(System.out::println);
    }

    private static void addStudents(StudentDao dao, Scanner scanner) {
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

}
