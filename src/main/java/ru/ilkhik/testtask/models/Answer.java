package ru.ilkhik.testtask.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@IdClass(AnswerId.class)
public class Answer {
    @Id
    private Integer number;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(referencedColumnName = "test_id"),
            @JoinColumn(referencedColumnName = "number", name = "question_number")
    })
    private Question question;

    private Boolean isCorrect;

    private String text;
}
