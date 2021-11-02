package ru.ilkhik.testtask.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TakedTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Test test;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    private Integer score;
}
