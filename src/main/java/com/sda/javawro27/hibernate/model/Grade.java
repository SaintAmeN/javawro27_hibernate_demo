package com.sda.javawro27.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double gradeValue; // wartość 1-6

    // kiedy ocena została wstawiona
    @CreationTimestamp
    private LocalDateTime created;

    // aktualizowane w momencie aktualizacji wpisu w bazie
    @UpdateTimestamp
    private LocalDateTime updated;

    @Enumerated(value = EnumType.STRING)
    private GradeSubject subject;

    // - stworzy się dodatkowa kolumna z identyfikatorem studenta
    @ManyToOne
    private Student studentRef;
}
