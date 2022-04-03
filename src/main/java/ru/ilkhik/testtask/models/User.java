package ru.ilkhik.testtask.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 32, unique = true)
    private String login;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Role role;

    private LocalDate signupDate;

    private Integer testPassedNumber;

    private Integer scoreSum;

    private Integer scoreMaxSum;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<TakedTest> takedTests;

}
