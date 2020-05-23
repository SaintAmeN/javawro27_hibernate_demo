package com.sda.javawro27.hibernate.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

// @Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor  // konstruktor z "wymaganymi" parametrami. (jeśli nie ma pól finalnych, to mówimy o domyślnym)

@Entity
@AllArgsConstructor
//@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1)
    @Max(value = 6)
    private double gradeValue; // wartość 1-6

    // kiedy ocena została wstawiona
    @CreationTimestamp // on_insert = now()
    private LocalDateTime created;

    // aktualizowane w momencie aktualizacji wpisu w bazie
    @UpdateTimestamp // on_update = now()
    private LocalDateTime updated;

    @Enumerated(value = EnumType.STRING)
    private GradeSubject subject;

    // - stworzy się dodatkowa kolumna z identyfikatorem studenta
    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Student studentRef;

    public Grade(double gradeValue, GradeSubject subject) {
        this.gradeValue = gradeValue;
        this.subject = subject;
    }
}
