package com.sda.javawro27.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor

@Entity
@AllArgsConstructor
public class Teacher implements LastNameSearchable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ManyToMany(mappedBy = "teacherSet")
    private Set<Student> studentSet;
    // zakładam, że dużo częściej będę dodawał studenta nauczycielowi
}
