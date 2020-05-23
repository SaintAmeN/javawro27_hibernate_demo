package com.sda.javawro27.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//    POJO - Plain old java object
//      - pola muszą mieć gettery i settery
//      - pusty konstruktor

@Data
@Entity // jest to klasa bazodanowa (UWAGA! NIE ZAPOMNIJ O PLIKU CFG.XML)
@Table()
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    // STUDENT 1 2 5 7 8
    // GRADE 3 4 6 9

    @Id
    // zmiana z AUTO na IDENTITY oraz usunięcie bazy danych i wygenerowanie jej ponownie
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    identity - identyfikator pochodzi z bazy danych
//    sequence - licznik identyfikatorów pochodzi z hibernate, wszystkie klasy posiadają wspólny licznik
//    table - licznik identyfikatorów pochodzi z hibernate i posiada oddzielny dla każdej tabeli
    private Long id;

//    @Column(name = "first_name")
    private String firstName;
    private String lastName;

    private double height;
    private int age;

//    @Column(columnDefinition = "TINYINT default 0")
    private boolean alive; // nie isAlive

    @Enumerated(value = EnumType.STRING)
    private Behaviour behaviour;

    // lombok jeśli wygeneruje metodę na podstawie pola isAlive, to nazywać się będzie "isIsAlive"
    // isAlive

    // uczeń może mieć wiele ocen.
    //
    // baza danych domyślnie traktuje symetryczne odwołania (ManyToOne, OneToMany)
    // jako niezależne powiązania
    @OneToMany(mappedBy = "studentRef")
    private List<Grade> gradeList;


    // 1:1 (OneToOne) - osoba ma tylko jeden adres - oneToOne - lub złączyć obiekty
    // 1:n (OneToMany) -
    // n:1 (ManyToOne)
    // n:n (ManyToMany)
}

